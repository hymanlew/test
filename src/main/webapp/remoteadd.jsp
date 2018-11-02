<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
Created by IntelliJ IDEA.
User: WeiJinTechnology
Date: 2018/8/31
Time: 10:43
To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String sendId = request.getParameter("sendId");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加项目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/checkLedger.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/Inspectionplan.css"/>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js" charset="utf-8"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=TQl4EN7zBCoRG7nQXf27vtHgEcAjnitN"></script>
</head>
<body style="background: #d2d2d2; height: calc(100% - 5px * 2); width:calc(100%  - 5px * 2 - 2px); padding: 5px;">
<div class="detailsBody">
    <div class="detailsTab">
        <ul>
            <li class="on">工程基本信息</li>
            <li>相关机构</li>
            <li>项目成员</li>
            <li>分包单位</li>
        </ul>
    </div>
    <div class="Statistical_report_hr" style="margin-top: 10px;"></div>
    <div class="detailsContain">
        <div class="details active">
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>项目名称：</span>
                    <div class="base-info-input">
                        <input type="text" id="name" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <span><i>*</i>项目类别：</span>
                    <div class="base-info-input">
                        <select id="siteCategory">
                        </select>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>开工日期：</span>
                    <div class="base-info-input">
                        <input type="text" id="contractStartTime" placeholder="请输入" class="time"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <span><i>*</i>竣工日期：</span>
                    <div class="base-info-input">
                        <input type="text" id="contractEndTime" placeholder="请输入" class="time"/>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span style="width: 142px;"><i>*</i>建筑总面积（平方米）：</span>
                    <div class="base-info-input">
                        <input type="text" id="areaSize" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <span>投资类别：</span>
                    <div class="base-info-input">
                        <select id="siteInvestment">
                        </select>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>承包类型：</span>
                    <div class="base-info-input">
                        <select id="siteContract">
                        </select>
                    </div>
                </div>
                <div class="base-info-50">
                    <span>工程投资金额（万元）：</span>
                    <div class="base-info-input">
                        <input type="text" id="money" placeholder="请输入"/>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>合同编号：</span>
                    <div class="base-info-input">
                        <input type="text" id="contractNum" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <span>施工单位：</span>
                    <div class="base-info-input">
                        <input type="text" id="constructionName" placeholder="请输入"/>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>项目经理：</span>
                    <div class="base-info-input">
                        <input type="text" id="principal" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <span>联系电话：</span>
                    <div class="base-info-input">
                        <input type="text" id="principalPhone" placeholder="请输入"/>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>企业安全分管领导：</span>
                    <div class="base-info-input">
                        <input type="text" id="safetyMan" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <span>联系电话：</span>
                    <div class="base-info-input">
                        <input type="text" id="safetyPhone" placeholder="请输入"/>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>工资支付开户行：</span>
                    <div class="base-info-input">
                        <select id="bank">
                        </select>
                    </div>
                </div>
                <div class="base-info-50">
                    <span>工资支付账号：</span>
                    <div class="base-info-input">
                        <input type="text" id="bankCard" placeholder="请输入"/>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap">
                <div class="base-info-50 layui-form">
                    <span><i>*</i>施工许可证：</span>
                    <div class="base-info-input">
                        <div class="business-settings-file" onclick="$('#doc').click()">
                            <%--<input type="button" value="上传">--%>
                            <button type="button" class="set-btn" id="file">上传</button>
                        </div>
                        <input type="file" style="display: none" id="doc" name="doc" onchange="img_upload(this,'doc')">
                        <input type="hidden" id="save_doc" value="">
                    </div>
                </div>
                <div class="base-info-50">
                    <span>是否申报评优：</span>
                    <div class="base-info-input">
                        <input type="radio" name="Whetherornot" id="yes" value="1" class="checkbox" checked=""/>
                        <label for="yes">是</label>
                        <input type="radio" name="Whetherornot" id="no" value="0" class="checkbox"/>
                        <label for="no">否</label>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap layui-form">
                <div class="base-info-50">
                    <span><i>*</i>项目所在位置：</span>
                    <div class="base-info-input">
                        <input type="text" id="address" placeholder="请输入"/>
                    </div>
                </div>
                <button type="button" class="set-btn">设置工程坐标</button>
            </div>
            <div class="base-info-wrap">
                <div class="map-wrap" id="allmap">
                </div>
            </div>
            <div class="formFootBtnBox">
                <button type="button" onclick="submit()">保存</button>
                <button type="button" onclick="x_admin_close()">取消</button>
            </div>
        </div>
        <div class="details">

            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>建设单位：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>勘察单位：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>设计单位：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>总包单位：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>分包单位：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>监理单位：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>施工图审查机构：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="base-info-wrap2 layui-form">
                <div class="base-info-50">
                    <span>桩基检测机构：</span>
                    <div class="base-info-input">
                        <input type="text" placeholder="请输入"/>
                    </div>
                </div>
                <div class="base-info-50">
                    <div class="base-info-50">
                        <span>项目负责人：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="base-info-50">
                        <span>联系电话：</span>
                        <div class="base-info-input">
                            <input type="text" placeholder="请输入"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="formFootBtnBox">
                <button type="button">保存</button>
                <button type="button" onclick="x_admin_close()">取消</button>
            </div>

        </div>
        <div class="details">

            <table class="layui-table">
                <thead>
                <tr>
                    <th>
                        <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i>
                        </div>
                    </th>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>部门</th>
                    <th></th>
                    <th>职务</th>
                    <th>联系电话</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>1</td>
                    <td>刘旭</td>
                    <td>工程部</td>
                    <td>
                        <div class="hr"></div>
                    </td>
                    <td>项目经理</td>
                    <td>13012345678</td>
                    <td class="td-manage">
                        <a title="删除" href="javascript:;" class="del"> <img src="../../../images/delBtn.png"/> </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>1</td>
                    <td>刘旭</td>
                    <td>工程部</td>
                    <td>
                        <div class="hr"></div>
                    </td>
                    <td>项目经理</td>
                    <td>13012345678</td>
                    <td class="td-manage">
                        <a title="删除" href="javascript:;" class="del"> <img src="../../../images/delBtn.png"/> </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>1</td>
                    <td>刘旭</td>
                    <td>工程部</td>
                    <td>
                        <div class="hr"></div>
                    </td>
                    <td>项目经理</td>
                    <td>13012345678</td>
                    <td class="td-manage">
                        <a title="删除" href="javascript:;" class="del"> <img src="../../../images/delBtn.png"/> </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>1</td>
                    <td>刘旭</td>
                    <td>工程部</td>
                    <td>
                        <div class="hr"></div>
                    </td>
                    <td>项目经理</td>
                    <td>13012345678</td>
                    <td class="td-manage">
                        <a title="删除" href="javascript:;" class="del"> <img src="../../../images/delBtn.png"/> </a>
                    </td>
                </tr>
                </tbody>
            </table>
            <button type="button" class="set-btn">添加项目成员</button>
            <div class="formFootBtnBox">
                <button type="button">保存</button>
                <button type="button" onclick="x_admin_close()">取消</button>
            </div>
        </div>
        <div class="details">
            <div class="sub-unit-wrap layui-form">
                <div id="subList">
                <div class="sub-unit-line" id="sub_1">
                    <div class="sub-unit-a" style="width: 22.5%;">
                        <span>分包单位：</span>
                        <div class="sub-unit-input">
                            <input type="text" id="companyName_1" value="" lay-verify="required"
                                   onchange="searchBaseCompany(1)">
                        </div>
                    </div>
                    <div class="sub-unit-a" style="width: 15%;">
                        <span>类型：</span>
                        <div class="sub-unit-input">
                            <select id="type_1">
                                <option value="1">劳务分包</option>
                                <option value="2">施工分包</option>
                                <option value="3">建筑分包</option>
                            </select>
                        </div>
                    </div>
                    <div class="sub-unit-a" style="width: 15%;">
                        <span>项目负责人：</span>
                        <div class="sub-unit-input">
                            <input type="text" id="subprincipal_1" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="sub-unit-a" style="width: 15%;">
                        <span>联系电话：</span>
                        <div class="sub-unit-input">
                            <input type="text" id="subPhone_1" placeholder="请输入"/>
                        </div>
                    </div>
                    <div class="sub-unit-a" style="width: 15%;">
                        <span>开始时间：</span>
                        <div class="sub-unit-input">
                            <input type="text" id="subStart_1" placeholder="请输入" class="time"/>
                        </div>
                    </div>
                    <div class="sub-unit-a" style="width: 15%;">
                        <span>结束时间：</span>
                        <div class="sub-unit-input">
                            <input type="text" id="subEnd_1" placeholder="请输入" class="time"/>
                        </div>
                    </div>
                    <button type="button" class="del-btn" onclick="removeSubsite(1)">删除1</button>
                    <div class="clear"></div>
                </div>
                </div>
                <button type="button" class="add-btn" onclick="addSubsite()">新增分包单位</button>
                <div style="position: absolute;">
                    <ul id="company_list_add"
                        style="border: 2px solid darkgray; padding: 10px; display: none;margin-top: 10%;">
                    </ul>
                </div>
                <div class="formFootBtnBox">
                    <button type="button" onclick="saveSubsite()">保存</button>
                    <button type="button" onclick="x_admin_close()">取消</button>
                </div>

            </div>

        </div>

    </div>
</div>
<input type="hidden" id="longitude" value="">
<input type="hidden" id="latitude" value="">
<input type="hidden" id="siteIdSaved" value="8">

<script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
<script type="text/javascript" src="<%=basePath%>js/base.js"></script>
<script type="text/javascript">
    changeTab(".detailsTab ul li", ".detailsContain .details");

    function changeTab(ele1, ele2) {
        $(ele1).click(function () {
            var status = $("#siteIdSaved").val();
            if (status) {
                var index = $(this).index();
                $(this).addClass("on").siblings().removeClass("on");
                $(ele2).eq(index).addClass("active").siblings().removeClass("active");
            }
        })
    }

    var form;

    function callback() {
        form.render('select');
    }

    layui.use(['form', 'layedit', 'laydate'], function () {
        form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;

        form.on('select(province)', function (data) {
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            findSelectCity(data.value);
        });
        form.on('select(city)', function (data) {
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            findSelectDistinct(data.value);
        });

        findSiteCategory();
        findSiteContract();
        findSiteInvestment();
        findBank();
    });

    //查询项目类别
    function findSiteCategory() {
        $.post("<%=basePath%>site/findAllSiteCategory", {}, function (data) {
            var code = "";
            code += "<option value=''>选择项目类别</option>";
            var list = data;
            for (var i = 0; i < list.length; i++) {
                code += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
            }
            $("#siteCategory").html(code);
            callback();
        })
    }

    //承包类型
    function findSiteContract() {
        $.post("<%=basePath%>site/findAllSiteContract", {}, function (data) {
            var code = "";
            code += "<option value=''>选择承包类型</option>";
            var list = data;
            for (var i = 0; i < list.length; i++) {
                code += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
            }
            $("#siteContract").html(code);
            callback();
        })
    }

    //投资类别
    function findSiteInvestment() {
        $.post("<%=basePath%>site/findAllSiteInvestment", {}, function (data) {
            var code = "";
            code += "<option value=''>选择承包类型</option>";
            var list = data;
            for (var i = 0; i < list.length; i++) {
                code += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
            }
            $("#siteInvestment").html(code);
            callback();
        })
    }

    //银行
    function findBank() {
        $.post("<%=basePath%>site/findAllBank", {}, function (data) {
            var code = "";
            code += "<option value=''>选择开户银行</option>";
            var list = data;
            for (var i = 0; i < list.length; i++) {
                code += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
            }
            $("#bank").html(code);
            callback();
        })
    }

    function img_upload(fileObj, sign) {

        var allowExtention = ".jpg,.bmp,.gif,.png";
        var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
        if (allowExtention.indexOf(extention) > -1) {
            $.ajaxFileUpload({
                url: '<%=basePath%>site/upload',
                type: 'post',
                secureuri: false,
                fileElementId: sign,
                dataType: 'json',
                success: function (data, status) {
                    $("#file").html(fileObj.value.substring(12));
                    $("#save_doc").val(data);
                },
                error: function (data, status, e) {
                }
            });
        } else {
            fileObj.value = ""; //清空选中文件
        }
    }

    function submit() {
        var name = $("#name").val();
        var license = $("#save_doc").val();
        var principal = $("#principal").val();
        var principalPhone = $("#principalPhone").val();
        var constructionId = ${construction.id};
        var address = $("#address").val();
        var siteCategoryId = $("#siteCategory").val();
        var siteInvestmentId = $("#siteInvestment").val();
        var contractNum = $("#contractNum").val().trim();
        var contractStartTime = $("#contractStartTime").val();
        var contractEndTime = $("#contractEndTime").val();
        var siteContractId = $("#siteContract").val();
        var money = $("#money").val();
        var areaSize = $("#areaSize").val();
        var safetyMan = $("#safetyMan").val();
        var safetyPhone = $("#safetyPhone").val();
        var appraising = $("input[type=radio][name=Whetherornot]:checked").val();
        var bankId = $("#bank").val();
        var bankCard = $("#bankCard").val();
        var constructionName = $("#constructionName").val();
        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();
        var creator = '${construction.name}';

        if (name == "" || siteCategoryId == "" || contractStartTime == "" || contractEndTime == "" || areaSize == "" || siteContractId == "" || contractNum == ""
            || principal == "" || safetyMan == "" || bankId == "" || license == "" || address == "") {
            layer.alert("信息不完整，* 选项为必填项！");
            return;
        }
        if (bankCard == null || bankCard == "") {
            bankId = null;
        }
        var checkContract = "320506";
        var contract = contractNum.substr(0, 6);
        if (!(checkContract == contract)) {
            layer.alert("合同备案编号必须以 320506 开头！");
            return;
        }
        // var re = /0?(13|14|15|17|18|19)[0-9]{9}/;
        // if(!re.test(principalPhone) || !re.test(safetyPhone)){
        //     layer.alert("联系方式格式不正确！");
        //     return;
        // }

        $.post("<%=basePath%>site/addSite", {
            "name": name,
            "license": license,
            "principal": principal,
            "principalPhone": principalPhone
            ,
            "constructionId": constructionId,
            "address": address,
            "siteCategoryId": siteCategoryId,
            "siteInvestmentId": siteInvestmentId
            ,
            "contractNum": contractNum,
            "contractStartTime": contractStartTime,
            "contractEndTime": contractEndTime,
            "siteContractId": siteContractId
            ,
            "money": money,
            "areaSize": areaSize,
            "safetyMan": safetyMan,
            "safetyPhone": safetyPhone,
            "appraising": appraising
            ,
            "bankId": bankId,
            "bankCard": bankCard,
            "longitude": longitude,
            "latitude": latitude,
            "creator": creator
        }, function (data) {
            var statu = data.indexOf('success');
            if (statu >= 0) {
                var s = data.substring(statu + 7);
                $("#siteIdSaved").val(s);
                layer.alert('添加成功!');
                console.log($("#siteIdSaved").val());
            } else {
                layer.alert('添加失败!');
            }
        })
    }
</script>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());
    map.addControl(new BMap.MapTypeControl());

    var longitude = $("#longitude").val();
    var latitude = $("#latitude").val();
    if (longitude == "" && latitude == "") {
        <!-- 苏州-->
        longitude = 120.62191;
        latitude = 31.310001;
        <!-- 苏州-->
        /*<!-- 吴中区-->
        longitude=120.895011;
        latitude=31.986533;
        <!-- 吴中区-->*/
    }
    var point = new BMap.Point(longitude, latitude);
    map.centerAndZoom(point, 15);
    var marker = new BMap.Marker(point);
    map.addOverlay(marker);
    marker.setAnimation(BMAP_ANIMATION_BOUNCE);//跳动的动画
    map.addEventListener("click", function (e) {
        $("#longitude").val(e.point.lng);
        $("#latitude").val(e.point.lat);
        addMark();
    });

    function addMark() {
        map.clearOverlays();
        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();
        var point = new BMap.Point(longitude, latitude);
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        marker.setAnimation(BMAP_ANIMATION_BOUNCE);//跳动的动画
    }
</script>
<script type="text/javascript">
    var jsonData = [];
    var nums = [];
    var num = 1;
    // Array.prototype.remove = function(val) {
    //     var index = this.indexOf(val);
    //     if (index > -1) {
    //         this.splice(index, 1);
    //     }
    // };

    $(function () {
        // getnum();
        nums.push(num);
    });
    function getnum() {
        var divlist = $("#subList").find("div").find("div");
        var tagid = (divlist.find("input"))[0].id;
        num = tagid.substring(12);
    }

    function addSubsite() {

        num++;
        var html = '';
        html += '<div class="sub-unit-line" id="sub_'+num+'">\n' +
            '                    <div class="sub-unit-a" style="width: 22.5%;">\n' +
            '                        <span>分包单位：</span>\n' +
            '                        <div class="sub-unit-input">\n' +
            '                            <input type="text" id="companyName_'+num+'" value="" lay-verify="required"\n' +
            '                                   onchange="searchBaseCompany('+num+')">\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="sub-unit-a" style="width: 15%;">\n' +
            '                        <span>类型：</span>\n' +
            '                        <div class="sub-unit-input">\n' +
            '                            <select id="type_'+num+'">\n' +
            '                                <option value="1">劳务分包</option>\n' +
            '                                <option value="2">施工分包</option>\n' +
            '                                <option value="3">建筑分包</option>\n' +
            '                            </select>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="sub-unit-a" style="width: 15%;">\n' +
            '                        <span>项目负责人：</span>\n' +
            '                        <div class="sub-unit-input">\n' +
            '                            <input type="text" id="subprincipal_'+num+'" placeholder="请输入"/>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="sub-unit-a" style="width: 15%;">\n' +
            '                        <span>联系电话：</span>\n' +
            '                        <div class="sub-unit-input">\n' +
            '                            <input type="text" id="subPhone_'+num+'" placeholder="请输入"/>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="sub-unit-a" style="width: 15%;">\n' +
            '                        <span>开始时间：</span>\n' +
            '                        <div class="sub-unit-input">\n' +
            '                            <input type="text" id="subStart_'+num+'" placeholder="请输入" class="time"/>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="sub-unit-a" style="width: 15%;">\n' +
            '                        <span>结束时间：</span>\n' +
            '                        <div class="sub-unit-input">\n' +
            '                            <input type="text" id="subEnd_'+num+'" placeholder="请输入" class="time"/>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <button type="button" class="del-btn" onclick="removeSubsite('+num+')">删除'+num+'</button>\n' +
            '                    <div class="clear"></div>\n' +
            '                </div>';
        $("#subList").append(html);
        layui.use(['form','laydate'], function(){
            var form = layui.form,
                laydate = layui.laydate;
            form.render('select'); //刷新select选择框渲染
            laydate.render({
                elem: '#subStart_'+num //指定元素
            });
            laydate.render({
                elem: '#subEnd_'+num //指定元素
            });
        });
        nums.push(num);
        console.log(nums);
    }

    function removeSubsite(num) {
        if(num>1){
            console.log("=== "+num);
            $("#sub_"+num).remove();
            // nums.remove(num);
            var index = nums.indexOf(num);
            if (index > -1) {
                nums.splice(index, 1);
            }
            console.log(nums);
        }
    }

    function searchBaseCompany(num) {
        var name = $("#companyName_"+num).val();//公司名称
        if (name.trim() != "" && name != null) {
            $.ajax({
                type: "post",
                async: "false",
                data: {
                    "name": name.trim()
                },
                url: "<%=basePath%>construction/findBaseCompany",
                dataType: "json",
                success: function (data) {
                    var list = data.list;
                    var html = "";
                    if (list != null && list != undefined && list != "") {
                        for (var i = 0; i < list.length; i++) {
                            var company = list[i];
                            html += '<li class="choose" value="' + company.num + '" style="padding-bottom: 10px;">' + company.name + '</li>';
                        }
                    } else {
                        html += '<li>基础库中没有此企业数据，可以添加</li>';
                    }
                    $("#company_list_add").html(html);
                    $("#company_list_add").show();
                    $(".choose").click(function () {
                        var cname = $(this).text();
                        var clicense = $(this).attr("value");
                        console.log(clicense);
                        $("#companyName_"+num).val(cname);
                        $("#company_list_add").hide();
                    });
                }
            });
        } else {
            $("#company_list_add").hide();
        }
    }

    function saveSubsite() {
        for(var k in nums){
            var i = nums[k];
            var subRow = {
                    // ocument.getElementById("fieldName_" + number).value,
                companyName: $("#companyName_"+i+"").val(),
                type: $("#type_"+i+"").val(),
                principal: $("#subprincipal_"+i+"").val(),
                phone: $("#subPhone_"+i+"").val(),
                start: $("#subStart_"+i+"").val(),
                end: $("#subEnd_"+i+"").val()
            };
            console.log(i);
            if(subRow.companyName=="" || subRow.principal=="" || subRow.phone=="" || subRow.start=="" || subRow.end==""){
                layer.alert("第 "+i+"条信息不完整！");
                return;
            }
            var re = /0?(13|14|15|17|18|19)[0-9]{9}/;
            if(!re.test(subRow.phone)){
                console.log(subRow.phone);
                layer.alert("第 "+i+"条联系方式格式不正确！");
                return;
            }
            jsonData.push(subRow);
        }
        // var subRow_1 = {
        //     companyName: $("#companyName_1").val(),
        //     type: $("#type_1").val(),
        //     principal: $("#subprincipal_1").val(),
        //     phone: $("#subPhone_1").val(),
        //     start: $("#subStart_1").val(),
        //     end: $("#subEnd_1").val()
        // };
        // jsonData.push(subRow_1);

        console.log(jsonData.length);
        console.log(JSON.stringify(jsonData));
       $.ajax({
           type: "post",
           async: false,
           data: JSON.stringify(jsonData),
           url: "<%=basePath%>department/add",
           dataType: "json",
           success: function (data) {

           },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
               console.log(XMLHttpRequest.status);
               console.log(XMLHttpRequest.readyState);
               console.log(textStatus);
           }
       });
    }
</script>
</body>
</html>