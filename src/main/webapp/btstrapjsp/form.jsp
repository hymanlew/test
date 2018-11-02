
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
<div style="border: 1px solid brown; padding: 20px;">
<form role="form">
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <div class="form-group">
    <label for="exampleInputFile">File input</label>
    <input type="file" id="exampleInputFile">
    <p class="help-block">帮助说明信息.</p>
  </div>
  <div class="checkbox">
    <label>
      <input type="checkbox"> Check me out
    </label>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>
</div>
<div style="margin-bottom: 30px;"></div>

<div style="border: 1px solid brown; padding: 20px;">被禁用的输入框
  <form role="form">
    <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled>
    <fieldset disabled>
      <div class="form-group">
        <label for="disabledTextInput">Disabled input</label>
        <input type="text" id="disabledTextInput" class="form-control" placeholder="Disabled input">
      </div>
      <div class="form-group">
        <label for="disabledSelect">Disabled select menu</label>
        <select id="disabledSelect" class="form-control">
          <option>Disabled select</option>
        </select>
      </div>
      <div class="checkbox">
        <label>
          <input type="checkbox"> Can't check this
        </label>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </fieldset>
  </form>
</div>
<div style="margin-bottom: 30px;"></div>

<div style="border: 1px solid brown; padding: 20px;">水平排列的表单,内联表单
  <form class="form-inline" role="form">
    <div class="form-group">
      <label class="sr-only" for="exampleInputEmail2">Email address</label>
      <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
    </div>
    <div class="form-group">
      <div class="input-group">
        <div class="input-group-addon">@</div>
        <input class="form-control" type="email" placeholder="Enter email">
      </div>
    </div>
    <div class="form-group">
      <label class="sr-only" for="exampleInputPassword2">Password</label>
      <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
    </div>
    <div class="checkbox">
      <label>
        <input type="checkbox"> Remember me
      </label>
    </div>
    <button type="submit" class="btn btn-default">Sign in</button>
  </form>
</div>
<div style="margin-bottom: 30px;"></div>

<div style="border: 1px solid brown; padding: 20px;">校验状态
  <div class="form-group has-success">
    <label class="control-label" for="inputSuccess1">Input with success</label>
    <input type="text" class="form-control" id="inputSuccess1">
  </div>
  <div class="form-group has-warning">
    <label class="control-label" for="inputWarning1">Input with warning</label>
    <input type="text" class="form-control" id="inputWarning1">
  </div>
  <div class="form-group has-error">
    <label class="control-label" for="inputError1">Input with error</label>
    <input type="text" class="form-control" id="inputError1">
  </div>
  <div class="has-success">
    <div class="checkbox">
      <label>
        <input type="checkbox" id="checkboxSuccess" value="option1">
        Checkbox with success
      </label>
    </div>
  </div>
  <div class="has-warning">
    <div class="checkbox">
      <label>
        <input type="checkbox" id="checkboxWarning" value="option1">
        Checkbox with warning
      </label>
    </div>
  </div>
  <div class="has-error">
    <div class="checkbox">
      <label>
        <input type="checkbox" id="checkboxError" value="option1">
        Checkbox with error
      </label>
    </div>
  </div>
  <hr/>
  <div class="form-group has-success has-feedback">
    <label class="control-label" for="inputSuccess2">Input with success</label>
    <input type="text" class="form-control" id="inputSuccess2">
    <span class="glyphicon glyphicon-ok form-control-feedback"></span>
  </div>
  <div class="form-group has-warning has-feedback">
    <label class="control-label" for="inputWarning2">Input with warning</label>
    <input type="text" class="form-control" id="inputWarning2">
    <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
  </div>
  <div class="form-group has-error has-feedback">
    <label class="control-label" for="inputError2">Input with error</label>
    <input type="text" class="form-control" id="inputError2">
    <span class="glyphicon glyphicon-remove form-control-feedback"></span>
  </div>
  <hr/>
  <form class="form-horizontal" role="form">
    <div class="form-group has-success has-feedback">
      <label class="control-label col-sm-3" for="inputSuccess3">Input with success</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="inputSuccess3">
        <span class="glyphicon glyphicon-ok form-control-feedback"></span>
      </div>
    </div>
  </form>
  <hr/>
  <form class="form-inline" role="form">
    <div class="form-group has-success has-feedback">
      <label class="control-label" for="inputSuccess4">Input with success</label>
      <input type="text" class="form-control" id="inputSuccess4">
      <span class="glyphicon glyphicon-ok form-control-feedback"></span>
    </div>
  </form>
  <hr/>
  <div class="form-group has-success has-feedback">
    <label class="control-label sr-only" for="inputSuccess5">Hidden label</label>
    <input type="text" class="form-control" id="inputSuccess5">
    <span class="glyphicon glyphicon-ok form-control-feedback"></span>
  </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div style="border: 1px solid brown; padding: 20px;">
  <%-- 静态控件：如果需要在表单中将一行纯文本和 label 元素放置在同一行，则为 p 元素添加 form-control-static 格式即可 --%>
  <form class="form-inline">
    <div class="form-group">
      <%-- sr-only 样式，可以让当前元素不可见 --%>
      <label class="sr-only">Email</label>
      <p class="form-control-static">test@hyman.com</p>
    </div>
    <div class="form-group">
      <label class="sr-only" for="inputPassword">Passowrd</label>
      <input type="password" class="form-control" id="inputPassword" placeholder="password">
    </div>
    <div class="form-group">
      <input type="password" class="form-control" id="inputPassword1" placeholder="password">
      <span class="help-block">辅助文本，即对当前输入框的说明信息</span>
    </div>
    <%-- class=form-horizontal --%>
    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label">Email</label>--%>
    <%--<div class="col-sm-10">--%>
    <%--<p class="form-control-static">test@hyman.com</p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label" for="inputPassword">Passowrd</label>--%>
    <%--<div class="col-sm-10">--%>
    <%--<input type="password" class="form-control" id="inputPassword" placeholder="password">--%>
    <%--</div>--%>
    <%--</div>--%>

    <div class="input-group">
      <span class="input-group-addon">@</span>
      <input type="text" class="form-control" placeholder="Username">
    </div>
    <br/>
    <div class="input-group">
      <input type="text" class="form-control">
      <span class="input-group-addon">.00</span>
    </div>
    <br/>
    <div class="input-group">
      <span class="input-group-addon">$</span>
      <input type="text" class="form-control">
      <span class="input-group-addon">.00</span>
    </div>
    <br/>

    <div class="row">
      <div class="col-lg-6">
        <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
          <input type="text" class="form-control">
        </div><!-- /input-group -->
      </div><!-- /.col-lg-6 -->
      <div class="col-lg-6">
        <div class="input-group">
          <input type="text" class="form-control">
          <span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
        </div><!-- /input-group -->
      </div><!-- /.col-lg-6 -->
    </div><!-- /.row -->

    <div class="row">
      <div class="col-lg-6">
        <div class="input-group">
      <span class="input-group-addon">
        <input type="checkbox">
      </span>
          <input type="text" class="form-control">
        </div><!-- /input-group -->
      </div><!-- /.col-lg-6 -->
      <div class="col-lg-6">
        <div class="input-group">
      <span class="input-group-addon">
        <input type="radio">
      </span>
          <input type="text" class="form-control">
        </div><!-- /input-group -->
      </div><!-- /.col-lg-6 -->
    </div><!-- /.row -->

    <div class="input-group input-group-lg">
      <span class="input-group-addon">@</span>
      <input type="text" class="form-control" placeholder="Username">
    </div>
    <br/>
    <div class="input-group">
      <span class="input-group-addon">@</span>
      <input type="text" class="form-control" placeholder="Username">
    </div>
    <br/>
    <div class="input-group input-group-sm">
      <span class="input-group-addon">@</span>
      <input type="text" class="form-control" placeholder="Username">
    </div>
    <br/>
  </form>
</div>
<div style="margin-bottom: 30px;"></div>

<div style="border: 1px solid brown; padding: 20px;">

</div>
<div style="margin-bottom: 30px;"></div>
</body>
</html>