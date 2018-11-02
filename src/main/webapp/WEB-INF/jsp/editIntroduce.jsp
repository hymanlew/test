
<<<<<<< HEAD
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
=======
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
>>>>>>> a577d1ec65e51a687a6470d87c9351cccf139485
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="base" scope="request" value="${pageContext.request.contextPath}/"></c:set>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>
    <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
    <title>Title</title>
</head>
<body>
<h3>
${data}
</h3>
<div style="margin: 15px;">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>劳务公司信息</legend>
    </fieldset>
    <form class="layui-form" action="" style="width: 50%;    position: absolute;">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:120px;"><span style="color: red">*</span>公司名称</label>
            <div class="layui-input-inline">
                <input type="tel" id="construction_name"  autocomplete="off" class="layui-input" value="" placeholder="" lay-verify="required"
                       onchange="searchBaseCompany()">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:120px;">营业执照注册号</label>
            <div class="layui-input-inline">
                <input type="tel"  autocomplete="off" id="license" value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:120px;"><span style="color: red">*</span>负责人</label>
            <div class="layui-input-inline">
                <input type="tel"  autocomplete="off" id="principal" value="" class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:120px;">地址</label>
            <div class="layui-input-inline">
                <input type="tel"  autocomplete="off" id="address" value="" class="layui-input">
            </div>
        </div>

        <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label" style="width:120px;">登录名</label>--%>
        <%--<div class="layui-input-inline">--%>
        <%--<input type="tel" id="username"  autocomplete="off" class="layui-input" value="${construction.user.name}" lay-verify="required">--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label" style="width:120px;">密码</label>--%>
        <%--<div class="layui-input-inline">--%>
        <%--<input type="tel" id="password" autocomplete="off" class="layui-input" value="" lay-verify="required">--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <a class="layui-btn" lay-submit="" id="submit" onclick="submit()">立即提交</a>
                <a href="<%=basePath%>"  class="layui-btn">返回</a>
            </div>
        </div>
    </form>
    <div style="position: absolute;margin-left: 21%;">
        <ul id="company_list_add" style="border: 2px solid darkgray; padding: 10px; display:none">
        </ul>
    </div>
</div>

<%-- 显示百度地图 --%>
<iframe id="map" src="<%=basePath%>edit/map" frameborder="0" scrolling="no" style="width:920px;height:800px;border:#ccc solid 1px;margin-top: 205px;position: absolute;"></iframe>
<div style="    position: absolute;margin-top: 30%;margin-left: 76%;">
    <input type="button" onclick='addMarker()' value="add111"></input>
    <input type="button" onclick="remove()" value="delete111"></input>
</div>

</body>
<script>
    function addMarker() {
        var n = document.getElementById("map").contentWindow["add_overlay"];
        return n();
    }
    function remove() {
        var n = document.getElementById("map").contentWindow["remove_overlay"];
        return n();
    }
</script>
</html>
