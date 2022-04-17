package hyman.utils.datascope;

import hyman.utils2.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.InterfaceExtension;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.security.Signature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// 数据过滤处理
@Aspect
@Component
public class DataScopeAspect {
    /**
     * 全部数据权限
     */
    public static final int DATA_SCOPE_ALL = 1;
    /**
     * 自定数据权限
     */
    public static final int DATA_SCOPE_CUSTOM = 2;
    /**
     * 部门数据权限
     */
    public static final int DATA_SCOPE_COMPANY = 3;
    /**
     * 部门及以下数据权限
     */
    public static final int DATA_SCOPE_OWER = 4;
    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    // 配置织入点
    @Pointcut("@annotation(xxx.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        // 获得注解
        DataScope controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null) {
            return;
        }
        // 前端传入
        Integer menuId = 0;
        // 获取当前的用户，进行数据权限的判断
        LoginUser loginUser = SpringSecurity.getLoginUser("假代码");
        SysUser currentUser = loginUser.getUser();
        if (currentUser != null) {
            // 如果是超级管理员，则不过滤数据
            if (!currentUser.isSuperAdmin()) {
                if (StringUtils.isNotBlank(controllerDataScope.columnName())) {
                    dataScopeFilter(currentUser,
                            controllerDataScope.withObjTree(),
                            controllerDataScope.withObj(),
                            controllerDataScope.withUser(),
                            controllerDataScope.withCompany(),
                            controllerDataScope.simpleSql(),
                            controllerDataScope.columnName(),
                            menuId,
                            flush);
                }
            }
        }
    }

}

    /**
     * 数据范围过滤，根据当前用户所属的用户组或角色查询数据库具有的菜单权限，并对此菜单中的数据做过滤，使用业务代码改写 sql 实现
     *
     * @param user       当前用户
     * @param withObj    筛选对象
     * @param withUser   筛选人员
     * @param columnName 字段名
     * @param menuId     菜单id
     */
    public void dataScopeFilter(SysUser user, boolean withObjTree, boolean withObj, boolean withUser, boolean withCompany, boolean simpleSql, String columnName, Integer menuId, String flush) {
        List<SysGroup> groups = user.getGroups();//获得用户组
        if ("1".equals(flush)) {
            groups = sysGroupMapper.selectGroupsByUserId(user.getUserId(), SecurityUtils.getProjectId());
        }
        if (groups == null) {
            return;
        }
        List<String> subsql = new ArrayList();
        List<String> subsql1 = new ArrayList();
        List<String> subsql2 = new ArrayList();
        String sql = null;
        List<SysDataScope> checkedDatascope = null;
        boolean hasAllDataScope = false;
        for (SysGroup group : groups) {

            List<SysGroupDatascope> menusDatascopes = group.getMenusDatascopes();
            List<SysGroupDatascope> collect = menusDatascopes.stream().filter(e -> e.getMenuId().intValue() == menuId.intValue()).collect(Collectors.toList());
            for (SysGroupDatascope groupDatascope : collect) {
                //1:全部数据权限 、2:自定义数据权限 、3:本公司数据权限、4:仅本人数据权限
                if (groupDatascope.getGroupType().intValue() == DATA_SCOPE_ALL) {
                    subsql.add("(1=1)");
                    hasAllDataScope = true;
                    break;
                } else if (groupDatascope.getGroupType().intValue() == DATA_SCOPE_CUSTOM) {
                    /*rootId->checkedNode*/
                    checkedDatascope = (List<SysDataScope>) sysGroupService.getCheckedDatascope(groupDatascope.getDataScopeId(), false);
                    sql = checkedDatascope.stream().filter(e -> StringUtils.isNotBlank(e.getGroupId().toString().trim())).map(e -> e.getGroupId().toString()).collect(Collectors.joining(","));
                    if (sql != null && sql.length() > 0) {
                        if (withObj) {
                            //需要根据勾选的节点找到继承的节点
                            HeatSysGroup sysGroup = new HeatSysGroup();
                            sysGroup.setParams(new HashMap<>());
                            sysGroup.getParams().put("ids", sql.split(","));
                            String sql1 = heatSysGroupMapper.selectRecursiveList(sysGroup).stream().map(e -> e.getId().toString()).collect(Collectors.toSet()).stream().collect(Collectors.joining(","));
                            if (simpleSql) {
                                String s;
                                if (StringUtils.isBlank(sql1)) {
                                    s = heatSysGroupMapper.selectObjPidsByGroupIds(sql);
                                } else {
                                    s = heatSysGroupMapper.selectObjPidsByGroupIds(sql1 + "," + sql);
                                }
                                if (StringUtils.isBlank(s)) {
                                    subsql.add("(1=0)");
                                } else {
                                    subsql.add(StringUtils.format("({} in ({}))", columnName, s));
                                }
                            } else {
                                if (StringUtils.isBlank(sql1)) {
                                    subsql.add(StringUtils.format("({} in (select obj_pid from manage.heat_group_obj where group_id in({})))", columnName, sql));
                                } else {
                                    subsql.add(StringUtils.format("({} in (select obj_pid from manage.heat_group_obj where group_id in({})))", columnName, sql1 + "," + sql));
                                }
                            }
                        } else if (withUser) {
                            subsql.add(StringUtils.format("({} in (select user_id from sys.sys_user where group_id in ({}) and project_id={}))", columnName, sql, SecurityUtils.getProjectId()));
                        } else if (withObjTree) {
                            if (columnName.indexOf(",") != -1) {
                                //需要根据勾选的节点找到继承的节点
                                HeatSysGroup sysGroup = new HeatSysGroup();
                                sysGroup.setParams(new HashMap<>());
                                sysGroup.getParams().put("ids", sql.split(","));
                                String sql1 = heatSysGroupMapper.selectRecursiveList(sysGroup).stream().map(e -> e.getId().toString()).collect(Collectors.toSet()).stream().collect(Collectors.joining(","));
                                if (StringUtils.isBlank(sql1)) {
                                    subsql1.add(StringUtils.format("({} in (select obj_pid from manage.heat_group_obj where group_id in({})))", columnName.split(",")[1], sql));
                                    subsql2.add(StringUtils.format("({} in ({}))", columnName.split(",")[0], sql));
                                } else {
                                    subsql1.add(StringUtils.format("({} in (select obj_pid from manage.heat_group_obj where group_id in({})))", columnName.split(",")[1], sql1 + "," + sql));
                                    subsql2.add(StringUtils.format("({} in ({}))", columnName.split(",")[0], sql1 + "," + sql));
                                }
                            }
                        } else if (withCompany) {
                            subsql.add(StringUtils.format("({} in (select distinct com_id from manage.heat_sys_group where id in ({})))", columnName, sql));
                        } else {
                            subsql.add(StringUtils.format("({} in ({}))", columnName, sql));
                        }
                    }
                } else if (groupDatascope.getGroupType().intValue() == DATA_SCOPE_COMPANY) {
                    if (withUser) {
                        subsql.add(StringUtils.format("({} in (select user_id from sys.sys_user where group_id={} and project_id={}))", columnName, SecurityUtils.getCompanyId(), SecurityUtils.getProjectId()));
                    } else {
                        subsql.add(StringUtils.format("({}={})", columnName, SecurityUtils.getCompanyId()));
                    }
                } else if (groupDatascope.getGroupType().intValue() == DATA_SCOPE_OWER) {
                    subsql.add(StringUtils.format("({}={})", columnName, SecurityUtils.getUserId()));
                }
            }
            if (hasAllDataScope) {
                break;
            }
        }

        if (withObjTree) {
            if (!hasAllDataScope && subsql1.size() > 0) {
                ServletUtils.getRequest().setAttribute(DATA_SCOPE + 1, " AND (" + subsql1.stream().collect(Collectors.joining(" or ")) + ")");
                ServletUtils.getRequest().setAttribute(DATA_SCOPE + 2, " AND (" + subsql2.stream().collect(Collectors.joining(" or ")) + ")");
            }
        }
        if (!hasAllDataScope && subsql.size() > 0) {
            ServletUtils.getRequest().setAttribute(DATA_SCOPE, " AND (" + subsql.stream().collect(Collectors.joining(" or ")) + ")");
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }

    /**
     * 该注解是 springsecurity 提供的，代表进入方法前的权限验证，参数为调用此方法所需要的权限表达式，
     * 例如 @PreAuthorize("hasAuthority('sys:dept:delete')")
     *
     * hasAuthority 方法是源码实现，可直接调用。程序会根据这个注解所需要的权限，再和当前登录的用户角色所拥有的权限对比，如果
     * 用户的角色权限集Set中有这个权限，则放行；没有，拒绝。
     *
     * 也可指定自己的校验权限，等同于调用某个 service 的某个方法
     * @PreAuthorize("@servicebean.method('update')")
     */
}
