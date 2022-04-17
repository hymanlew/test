package hyman.utils.datascope;

import java.lang.annotation.*;

// 数据权限过滤注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    public boolean withObjTree() default false;

    public boolean withObj() default false;

    public boolean withUser() default false;

    public boolean withCompany() default false;

    public String columnName() default "";

    public boolean simpleSql() default false;
}
