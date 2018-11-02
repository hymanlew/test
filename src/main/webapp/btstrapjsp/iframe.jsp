
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/" />

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link type="text/css" rel="stylesheet" href="${base}bootstrap/css/bootstrap.css">
  <link type="text/css" rel="stylesheet" href="${base}bootstrap/css/bootstrap-theme.css">
  <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="${base}bootstrap/js/bootstrap.min.js"></script>
  <title>Title</title>
</head>
<body style="padding: 20px">
<!-- 16:9 aspect ratio -->
<div class="embed-responsive embed-responsive-16by9">
  <iframe class="embed-responsive-item" src="../image/pic.jpg"></iframe>
</div>
<hr/>
<!-- 4:3 aspect ratio -->
<div class="embed-responsive embed-responsive-4by3">
  <iframe class="embed-responsive-item" src="../image/pic.jpg"></iframe>
</div>
</body>
</html>