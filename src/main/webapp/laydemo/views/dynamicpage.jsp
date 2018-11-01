<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/" />

<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>后台管理模板</title>
  <!-- build:css -->
  <%--<link rel="stylesheet" href="lib/layui/css/layui.css">--%>
  <link rel="stylesheet" href="${base}css/theme/default.css" id="theme">
  <link rel="stylesheet" href="${base}css/nprogress.css">

  <link rel="stylesheet" type="text/css" href="${base}layui/css/layui.css">
  <!-- endbuild -->
</head>

<body class="layui-layout-body kit-theme-default">
  <div class="layui-layout layui-layout-admin">
    <!-- header -->
    <div class="layui-header">
      <div class="layui-logo">
        <div class="layui-logo-toggle" kit-toggle="side" data-toggle="on">
          <i class="layui-icon">&#xe65a;</i>
        </div>
        <div class="layui-logo-brand">
          <a href="#/">Hyman 1.0</a>
        </div>
      </div>
      <div class="layui-layout-left">
        <!-- <div class="kit-search">
          <form action="/">
            <input type="text" name="keyword" class="kit-search-input" placeholder="关键字..." />
            <button class="kit-search-btn" title="搜索" type="submit">
              <i class="layui-icon">&#xe615;</i>
            </button>
          </form>
        </div> -->
      </div>
      <div class="layui-layout-right">
        <ul class="kit-nav" lay-filter="header_right">
          <li class="kit-item" kit-target="help">
            <a href="javascript:;">
              <i class="layui-icon">&#xe607;</i>
              <span>帮助</span>
            </a>
          </li>
          <li class="kit-item" id="ccleft">
            <a href="javascript:;">
              <i class="layui-icon">&#xe60e;</i>
            </a>
          </li>
          <li class="kit-item" id="cc">
            <a href="javascript:;">
              <i class="layui-icon">&#xe64c;</i>
            </a>
          </li>
          <li class="kit-item">
            <a href="javascript:;">
              <span>
                <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img">Van
              </span>
            </a>
            <ul class="kit-nav-child kit-nav-right">
              <li class="kit-item">
                <a href="#/user/my">
                  <i class="layui-icon">&#xe612;</i>
                  <span>个人中心</span>
                </a>
              </li>
              <li class="kit-item" kit-target="setting">
                <a href="javascript:;">
                  <i class="layui-icon">&#xe614;</i>
                  <span>设置</span>
                </a>
              </li>
              <li class="kit-nav-line"></li>
              <li class="kit-item">
                <a href="temp/login.html">
                  <i class="layui-icon">&#x1006;</i>
                  <span>注销</span>
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
    <!-- silds -->
    <div class="layui-side" kit-side="true">
      <div class="layui-side-scroll">
        <div id="menu-box">

        </div>
      </div>
    </div>
    <!-- main -->
    <div class="layui-body" kit-body="true">
      <router-view></router-view>
    </div>
    <!-- footer -->
    <div class="layui-footer" kit-footer="true">
      2017 © kit.zhengjinfan.cn MIT license
      <div style="width:400px; height:400px;" class="load-container load1">
        <div class="loader">Loading...</div>
      </div>
    </div>
  </div>

  <!-- build:js -->
  <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
  <script>
      <%-- 定义基础路径，在 js 中使用 --%>
      var baseU = '${base}';
      <%-- 定义全局开关，是否动态渲染 --%>
      var flag = true;
  </script>

  <script type="text/javascript" src="${base}layui/layui.js"></script>

  <%--
    Polyfill 是一个js库，主要抚平不同浏览器之间对js实现的差异。比如 html5的storage(session,local), 不同浏览器不同版本，
    有些支持，有些不支持。

    Polyfill（Polyfill有很多，在GitHub上https://github.com/Modernizr/Modernizr/wiki/HTML5-Cross-Browser-Polyfills），帮
    你把这些差异化抹平，不支持的变得支持了（典型做法是在IE浏览器中增加 window.XMLHttpRequest ，内部实现使用 ActiveXObject。）

    提到Polyfill，不得不提shim,polyfill 是 shim的一种。shim是将不同 api封装成一种，比如 jQuery的 $.ajax 封装了 XMLHttpRequest
    和 IE用 ActiveXObject方式创建xhr对象。它将一个新的API引入到一个旧的环境中,而且仅靠旧环境中已有的手段实现。
  --%>
  <script src="${base}js/polyfill.min.js"></script>
  <%-- 菜单栏 js 配置 --%>
  <script src="${base}laydemo/layjs/mainmenu.js"></script>
  <%-- 图表 js --%>
  <script src="${base}js/echarts.min.js"></script>
  <!-- endbuild -->

  <script>
      <%--
        全局化配置一些参数，遵循 layui 的模块规范建立一个入口文件，并通过 layui.use() （加载模块并应用）方式来加载该入口文件，
      --%>
      layui.config({
          // 存放新模块的目录，即自定义的模块，不是layui的模块目录
          base: '${base}laydemo/layjs/'
          // 加载入口，即 admin.js 文件
      }).use('admin');

      console.log(flag);
  </script>
  <!-- endbuild -->
</body>

</html>