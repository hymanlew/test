<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/"/>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link type="text/css" rel="stylesheet" href="${base}bootstrap/css/bootstrap.css">
  <link type="text/css" rel="stylesheet" href="${base}bootstrap/css/bootstrap-theme.css">
  <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="${base}bootstrap/js/bootstrap.min.js"></script>

  <%--
    sr-only 样式，为隐藏当前元素
    divider 样式，为分隔线
    <hr>    标签，为分隔线
  --%>
</head>
<body style="padding: 20px">
<div class="bs-example">
    <div class="btn-group">
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">单按钮下拉菜单<span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div><!-- /btn-group -->
    <div class="btn-group">
      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">禁用的菜单项<span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
      </ul>
    </div><!-- /btn-group -->
    <div class="btn-group">
      <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Success <span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div><!-- /btn-group -->
    <div class="btn-group">
      <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Info <span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div><!-- /btn-group -->
    <div class="btn-group">
      <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">Warning <span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div><!-- /btn-group -->
    <div class="btn-group">
      <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">Danger <span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div><!-- /btn-group -->
</div>
<div style="margin-bottom: 30px;"></div>

<style type="text/css">
  .dropdown-toggle{
    height: 34px;
  }
</style>
<div class="bs-example">
  <div class="btn-group">
    <button type="button" class="btn btn-default">分裂式按钮下拉菜单</button>
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
      <span class="caret"></span>
      <span class="sr-only">Toggle Dropdown</span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="#">Action</a></li>
      <li class="divider"></li>
      <li><a href="#">Separated link</a></li>
    </ul>
  </div><!-- /btn-group -->
  <div class="btn-group">
    <button type="button" class="btn btn-primary">Primary</button>
    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="#">Action</a></li>
      <li class="divider"></li>
      <li><a href="#">Separated link</a></li>
    </ul>
  </div><!-- /btn-group -->
  <div class="btn-group">
    <button type="button" class="btn btn-success">Success</button>
    <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="#">Action</a></li>
      <li class="divider"></li>
      <li><a href="#">Separated link</a></li>
    </ul>
  </div><!-- /btn-group -->
  <div class="btn-group">
    <button type="button" class="btn btn-info">Info</button>
    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="#">Action</a></li>
      <li class="divider"></li>
      <li><a href="#">Separated link</a></li>
    </ul>
  </div><!-- /btn-group -->
  <div class="btn-group">
    <button type="button" class="btn btn-warning">Warning</button>
    <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="#">Action</a></li>
      <li class="divider"></li>
      <li><a href="#">Separated link</a></li>
    </ul>
  </div><!-- /btn-group -->
  <div class="btn-group">
    <button type="button" class="btn btn-danger">Danger</button>
    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="#">Action</a></li>
      <li class="divider"></li>
      <li><a href="#">Separated link</a></li>
    </ul>
  </div><!-- /btn-group -->
</div>
<div style="margin-bottom: 30px;"></div>

<div class="bs-example">
  <div class="btn-toolbar" role="toolbar">
      <div class="btn-group">
        <button class="btn btn-default btn-lg dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
          Large button <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="#">Action</a></li>
          <li class="divider"></li>
          <li><a href="#">Separated link</a></li>
        </ul>
      </div><!-- /btn-group -->

      <div class="btn-group">
          <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
              Small button <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" role="menu">
              <li><a href="#">Action</a></li>
              <li class="divider"></li>
              <li><a href="#">Separated link</a></li>
          </ul>
      </div><!-- /btn-group -->

      <div class="btn-group">
          <button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">
              Extra small button <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" role="menu">
              <li><a href="#">Action</a></li>
              <li class="divider"></li>
              <li><a href="#">Separated link</a></li>
          </ul>
      </div><!-- /btn-group -->
  </div><!-- /btn-toolbar -->
  <div class="btn-toolbar" role="toolbar">
  </div><!-- /btn-toolbar -->
</div>
<div style="margin-bottom: 30px;"></div>

<div class="bs-example">
  <div class="btn-toolbar" role="toolbar">
    <div class="btn-group dropup">
      <button type="button" class="btn btn-default">向上弹出式菜单</button>
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
        <span class="caret"></span>
        <span class="sr-only">Toggle Dropdown</span>
      </button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div><!-- /btn-group -->
    <div class="btn-group dropup">
      <button type="button" class="btn btn-primary">Right dropup</button>
      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
        <span class="caret"></span>
      </button>
      <ul class="dropdown-menu dropdown-menu-right" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div><!-- /btn-group -->
  </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div class="btn-toolbar" role="toolbar" style="margin: 0;">
  <div class="btn-group">
    <button type="button" class="btn btn-default">按钮工具栏</button>
    <button type="button" class="btn btn-default">2</button>
    <button type="button" class="btn btn-default">3</button>
    <button type="button" class="btn btn-default">4</button>
  </div>
  <div class="btn-group">
    <button type="button" class="btn btn-default">5</button>
    <button type="button" class="btn btn-default">6</button>
    <button type="button" class="btn btn-default">7</button>
  </div>
  <div class="btn-group">
    <button type="button" class="btn btn-default">8</button>
  </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div>
  <div class="btn-toolbar" role="toolbar">
    <div class="btn-group btn-group-lg">
      <button type="button" class="btn btn-default">尺寸-Left</button>
      <button type="button" class="btn btn-default">Middle</button>
      <button type="button" class="btn btn-default">Right</button>
    </div>

      <div class="btn-group">
          <button type="button" class="btn btn-default">Left</button>
          <button type="button" class="btn btn-default">Middle</button>
          <button type="button" class="btn btn-default">Right</button>
      </div>

      <div class="btn-group btn-group-sm">
          <button type="button" class="btn btn-default">Left</button>
          <button type="button" class="btn btn-default">Middle</button>
          <button type="button" class="btn btn-default">Right</button>
      </div>

      <div class="btn-group btn-group-xs">
          <button type="button" class="btn btn-default">Left</button>
          <button type="button" class="btn btn-default">Middle</button>
          <button type="button" class="btn btn-default">Right</button>
      </div>
  </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div class="btn-group">
  <button type="button" class="btn btn-default">1</button>
  <button type="button" class="btn btn-default">2</button>

  <div class="btn-group">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
      嵌套
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="#">Dropdown link</a></li>
      <li><a href="#">Dropdown link</a></li>
    </ul>
  </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div class="btn-group-vertical">
  <button type="button" class="btn btn-default">垂直排列</button>
  <div class="btn-group">
    <button id="btnGroupVerticalDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
      Dropdown
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="btnGroupVerticalDrop1">
      <li><a href="#">Dropdown link</a></li>
      <li><a href="#">Dropdown link</a></li>
    </ul>
  </div>
  <button type="button" class="btn btn-default">Button</button>
  <div class="btn-group">
    <button id="btnGroupVerticalDrop2" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
      Dropdown
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="btnGroupVerticalDrop2">
      <li><a href="#">Dropdown link</a></li>
      <li><a href="#">Dropdown link</a></li>
    </ul>
  </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div>
  <div class="btn-group btn-group-justified">
    <a href="#" class="btn btn-default" role="button">两端对齐排列的按钮组-Left</a>
    <a href="#" class="btn btn-default" role="button">Middle</a>
    <div class="btn-group">
      <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
        Dropdown <span class="caret"></span>
      </a>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">Action</a></li>
        <li class="divider"></li>
        <li><a href="#">Separated link</a></li>
      </ul>
    </div>
  </div>
  <hr/>
</div>

<div class="dropdown" style="width: 250px">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
    右下角弹出
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
  </ul>
</div>

<div class="dropdown">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown">
    标题
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
    <li role="presentation" class="dropdown-header">Dropdown header</li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
    <li role="presentation" class="dropdown-header">Dropdown header</li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
  </ul>
</div>
<hr/>

<div class="bs-example">
  <div class="progress">
    <div id="p1" class="progress-bar progress-bar-striped " role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%"><span class="sr-only">45% Complete</span></div>
  </div>
  <button type="button" class="btn btn-default bs-docs-activate-animated-progressbar" data-toggle="button" aria-pressed="false" autocomplete="off" onclick="toggle()">Toggle animation</button>
</div>
<hr/>

<div class="progress">
  <%-- 还可以追加其他样式：progress-bar-success，progress-bar-info，progress-bar-warning，progress-bar-danger --%>
  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
    <span class="sr-only">60% Complete</span>
  </div>
</div>

<div class="progress">
  <div class="progress-bar progress-bar-success" style="width: 35%">
    <span class="sr-only">35% Complete (success)</span>
  </div>
  <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 20%">
    <span class="sr-only">20% Complete (warning)</span>
  </div>
  <div class="progress-bar progress-bar-danger" style="width: 10%">
    <span class="sr-only">10% Complete (danger)</span>
  </div>
</div>
<script type="text/javascript">
    function toggle(){
        if($("#p1").hasClass("active")){
            $("#p1").removeClass("active");
        }else{
            $("#p1").addClass("active");
        }
    }
</script>


</body>
</html>