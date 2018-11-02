
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="base" scope="request" value="${pageContext.request.contextPath}/"></c:set>

<html>
<body>
<h2>Hello World!</h2>

<div>
    <h2>
        <a href="${base}edit/showEdit">去编辑器</a>
    </h2>
    <h2>
        <a href="${base}edit/introduce">编辑器说明</a>
    </h2>
    <h2>
        <a href="${base}edit/layui">Layui说明</a>
    </h2>
    <h2>
        <a href="${base}edit/layuijs">Layui简化 js说明</a>
    </h2>
    <h2>
        <a href="${base}laysource/index.html">Layui 模板</a>
    </h2>
    <h2>
        <a href="${base}laydemo/index.jsp">Layui Demo</a>
    </h2>
</div>
</body>
</html>
