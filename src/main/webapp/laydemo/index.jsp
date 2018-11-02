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
          <ul class="kit-menu">
            <li class="kit-menu-item">
              <a href="#/">
                <i class="layui-icon"></i>
                <span>首页</span>
              </a>
            </li>
            <li class="kit-menu-item layui-show">
              <a href="javascript:;">
                <i class="layui-icon"></i>
                <span>Layui组件</span>
              </a>
              <ul class="kit-menu-child layui-anim layui-anim-upbit">
                <li class="kit-menu-item">
                  <a href="#/layui/grid">
                    <i class="layui-icon"></i>
                    <span>Grid</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="javascript:;">
                    <i class="layui-icon"></i>
                    <span>基本元素</span>
                  </a>
                  <ul class="kit-menu-child layui-anim layui-anim-upbit">
                    <li class="kit-menu-item">
                      <a href="#/layui/button">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>按钮</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/form">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>表单</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/nav">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>导航/面包屑</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/tab">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>选项卡</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/progress">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>进度条</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/panel">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>面板</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/badge">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>徽章</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/timeline">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>时间线</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/table-element">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>静态表格</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/anim">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>动画</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/other">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>其他元素说明</span>
                      </a>
                    </li>
                  </ul>
                </li>
                <li class="kit-menu-item layui-show">
                  <a href="javascript:;">
                    <i class="layui-icon"></i>
                    <span>组件</span>
                  </a>
                  <ul class="kit-menu-child layui-anim layui-anim-upbit">
                    <li class="kit-menu-item">
                      <a href="#/layui/layer">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>弹出层</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/laydate">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>日期与时间</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/table">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>数据表格</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/laypage">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>分页</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/upload">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>文件上传</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/carousel">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>轮训</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/laytpl">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>模板引擎</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/flow">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>流加载</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/util">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>工具集</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="#/layui/code">
                        <i class="layui-icon">&#xe62e;</i>
                        <span>代码修饰器</span>
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
            <li class="kit-menu-item">
              <a href="javascript:;" onclick="dynamicMENU()">
                <i class="layui-icon"></i>
                <span>动态渲染页面</span>
              </a>
            </li>
            <li class="kit-menu-item">
              <a href="javascript:;">
                <i class="layui-icon"></i>
                <span>异常页</span>
              </a>
              <ul class="kit-menu-child layui-anim layui-anim-upbit">
                <li class="kit-menu-item">
                  <a href="#/exception/403">
                    <i class="layui-icon">&#xe69c;</i>
                    <span>403</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="#/exception/404">
                    <i class="layui-icon">&#xe61c;</i>
                    <span>404</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="#/exception/500">
                    <i class="layui-icon">&#xe64d;</i>
                    <span>500</span>
                  </a>
                </li>
              </ul>
            </li>
            <li class="kit-menu-item">
              <a href="javascript:;" class="child">
                <i class="layui-icon"></i>
                <span>多级菜单</span>
              </a>
              <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                <li class="kit-menu-item">
                  <a href="#/user/form">
                    <i class="layui-icon"></i>
                    <span>二级菜单1</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="#/user/as" class="child">
                    <i class="layui-icon"></i>
                    <span>二级菜单2</span>
                  </a>
                  <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                    <li class="kit-menu-item">
                      <a href="#" target="_blank">
                        <i class="layui-icon"></i>
                        <span>三级菜单1</span>
                      </a>
                    </li>
                    <li class="kit-menu-item">
                      <a href="javascript:;" target="_blank">
                        <i class="layui-icon"></i>
                        <span>三级菜单2</span>
                      </a>
                      <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                        <li class="kit-menu-item">
                          <a href="https://www.baidu.com" target="_blank">
                            <i class="layui-icon"></i>
                            <span>四级菜单-百度</span>
                          </a>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li class="kit-menu-item">
                  <a href="#/user/aa">
                    <i class="layui-icon"></i>
                    <span>二级菜单3</span>
                  </a>
                </li>
              </ul>
            </li>
            <li class="kit-menu-item">
              <a href="javascript:;" class="child">
                <i class="layui-icon"></i>
                <span>API文档</span>
              </a>
              <ul class="kit-menu-child layui-anim layui-anim-upbit">
                <li class="kit-menu-item">
                  <a href="#/docs/mockjs">
                    <i class="layui-icon"></i>
                    <span>拦截器(Mockjs)</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="#/docs/menu">
                    <i class="layui-icon"></i>
                    <span>左侧菜单(Menu)</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="#/docs/route">
                    <i class="layui-icon"></i>
                    <span>路由配置(Route)</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="#/docs/tabs">
                    <i class="layui-icon"></i>
                    <span>选项卡(Tabs)</span>
                  </a>
                </li>
                <li class="kit-menu-item">
                  <a href="#/docs/utils">
                    <i class="layui-icon"></i>
                    <span>工具包(Utils)</span>
                  </a>
                </li>
                <li class="kit-menu-item layui-show">
                  <a href="javascript:;" class="child">
                    <i class="layui-icon"></i>
                    <span>组件(Component)</span>
                  </a>
                  <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                    <li class="kit-menu-item">
                      <a href="#/docs/component/nav">
                        <i class="layui-icon"></i>
                        <span>导航栏(Nav)</span>
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
            <li class="kit-menu-item">
              <a href="https://www.layui.com/admin/std/dist/views/" target="_blank">
                <i class="layui-icon"></i>
                <span>layui后台模板</span>
              </a>
            </li>
            <li class="kit-menu-item">
              <a href="#/inputnumber">
                <i class="layui-icon"></i>
                <span>InputNumber</span>
              </a>
            </li>
          </ul>
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
      var flag = false;
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
    function dynamicMENU() {
        location.href = baseU+"laydemo/views/dynamicpage.jsp";
    }
  </script>
  <!-- endbuild -->
</body>

</html>