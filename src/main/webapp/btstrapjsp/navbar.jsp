
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
<style type="text/css">

</style>
</head>
<body style="padding: 20px">

<%--
  如果 class 中追加上 navbar-fixed-top 格式，则工具栏固定在顶部，
  如果 class 中追加上 navbar-fixed-bottom 格式，则工具栏固定在底部。
--%>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">目录按钮（下面是按钮图案）</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">右侧</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">链接<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div style="height: 5px"></div>

<%-- navbar-inverse 样式，是采用反向颜色 --%>
<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-4">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-4">
      <p class="navbar-text">Signed in as Mark Otto</p>
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
      </ul>
    </div>

  </div><!-- /.container-fluid -->
</nav>

<nav>
  <ul class="pager">
    <li class="previous"><a href="#">&larr; Older</a></li>
    <li class="next"><a href="#">Newer &rarr;</a></li>
  </ul>
</nav>

<nav>
  <%-- 可以追加 pagination-lg 样式，设置大号的分页 --%>
  <ul class="pagination">
    <li class="disabled"><a href="#">«</a></li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li><a href="#">&raquo;</a></li>
  </ul>
</nav>
<div style="height: 5px"></div>

<nav id="navbar-example2" class="navbar navbar-default navbar-static">
  <div class="container-fluid">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-example-js-navbar-scrollspy">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Project Name</a>
    </div>
    <div class="collapse navbar-collapse bs-example-js-navbar-scrollspy">
      <ul class="nav navbar-nav">
        <li class=""><a href="#fat">@fat</a></li>
        <li class=""><a href="#mdo">@mdo</a></li>
        <li class="dropdown active">
          <a href="#" id="navbarDrop1" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu" aria-labelledby="navbarDrop1">
            <li class=""><a href="#one" tabindex="-1">one</a></li>
            <li class="divider"></li>
            <li class=""><a href="#three" tabindex="-1">three</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div data-spy="scroll" data-target="#navbar-example2" data-offset="0" style="height:200px;overflow:auto; position: relative;">
  <h4 id="fat">@fat</h4>
  <p>Ad leggings keytar, brunch id art party dolor labore.</p>
  <h4 id="mdo">@mdo</h4>
  <p>Veniam marfa mustache skateboard, adipisicing fugiat </p>
  <h4 id="one">one</h4>
  <p>Occaecat commodo aliqua delectus. Fap craft beer des</p>
  <h4 id="two">two</h4>
  <p>In incididunt echo park, officia deserunt mcsweeney's </p>
  <h4 id="three">three</h4>
  <p>Ad leggings keytar, brunch id art party dolor labore.</p>
  <p>Keytar twee blog, culpa messenger bag marfa whatever dele</p>
</div>
<div style="height: 5px"></div>


<div style="height: 5px"></div>

<div style="height: 5px"></div>
</body>
</html>