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
</head>
<body style="padding: 20px">
<div class="bs-example bs-example-standalone" data-example-id="dismissible-alert-js">
    <div id="myAlert" class="alert alert-warning alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong>Holy guacamole!</strong> Best check yo self, you're not looking too good.
    </div>

    <div class="alert alert-danger alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <h4>Oh snap! You got an error!</h4>
      <p>Change this and that and try again. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum.</p>
      <p>
        <button type="button" class="btn btn-danger">静态按钮</button>
        <button type="button" class="btn btn-default">静态按钮</button>
      </p>
    </div>
</div>
<div class="bs-example">
    <div class="alert alert-success" role="alert">
        <strong>Well done!</strong> You successfully read this important alert message.
    </div>
    <div class="alert alert-info" role="alert">
        <strong>Heads up!</strong> This alert needs your attention, but it's not super important.
    </div>
    <div class="alert alert-warning" role="alert">
        <strong>Warning!</strong> Better check yourself, you're not looking too good.
    </div>
    <div class="alert alert-danger" role="alert">
        <strong>Oh snap!</strong> Change a few things up and try submitting again.
    </div>
</div>

<a href="#">Inbox <span class="badge">42</span></a>
<button class="btn btn-primary" type="button">
    Messages <span class="badge">4</span>
</button>
<hr/>
<ul class="nav nav-pills" role="tablist">
    <li role="presentation" class="active"><a href="#">Home <span class="badge">42</span></a></li>
    <li role="presentation"><a href="#">Profile</a></li>
    <li role="presentation"><a href="#">Messages <span class="badge">3</span></a></li>
</ul>
<div style="margin-bottom: 30px;"></div>

<div class="bs-example">
    <ol class="breadcrumb">
        <li class="active">Home</li>
    </ol>
    <ol class="breadcrumb">
        <li><a href="#">Home</a></li>
        <li class="active">Library</li>
    </ol>
    <ol class="breadcrumb" style="margin-bottom: 5px;">
        <li><a href="#">Home</a></li>
        <li><a href="#">Library</a></li>
        <li class="active">Data</li>
    </ol>
</div>
<div style="margin-bottom: 30px;"></div>

<!-- Standard button -->
<button type="button" class="btn btn-default">Default</button>

<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
<button type="button" class="btn btn-primary">Primary</button>

<!-- Indicates a successful or positive action -->
<button type="button" class="btn btn-success">Success</button>

<!-- Contextual button for informational alert messages -->
<button type="button" class="btn btn-info">Info</button>

<!-- Indicates caution should be taken with this action -->
<button type="button" class="btn btn-warning">Warning</button>

<!-- Indicates a dangerous or potentially negative action -->
<button type="button" class="btn btn-danger">Danger</button>

<!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
<button type="button" class="btn btn-link">Link</button>
<div style="margin-bottom: 30px;"></div>

<button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Popover title" data-content="And here's some amazing content.">Click to toggle popover</button>
<a tabindex="0" class="btn btn-lg btn-danger" role="button" data-toggle="popover" data-trigger="focus" title="Dismissible popover" data-content="And here's some amazing content.">Dismissible popover</a>
<hr/>
<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="left" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
    Popover on left
</button>

<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="top" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
    Popover on top
</button>

<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="bottom" data-content="Vivamus">
    Popover on bottom
</button>

<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
    Popover on right
</button>
<hr/>
<script type="text/javascript">
    $(function () {
        $('[data-toggle="popover"]').popover();
    });
</script>

<button id="element" type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?">Click to toggle popover</button>
<hr/>
<input type="button" value="show" onclick="show()"/>
<input type="button" value="hide" onclick="hide()"/>
<input type="button" value="toggle" onclick="toggle()"/>
<input type="button" value="destroy" onclick="destroy()"/>
<script type="text/javascript">
    $(function () {
        $('[data-toggle="popover"]').popover();
    });

    function show(){
        $('#element').popover('show');
    }

    function hide(){
        $('#element').popover('hide');
    }

    function toggle(){
        $('#element').popover('toggle');
    }

    function destroy(){
        $('#element').popover('destroy');
    }
</script>
<div style="margin-bottom: 30px;"></div>

<div class="bs-example tooltip-demo">
    <div class="bs-example-tooltips">
        <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" title="" data-original-title="Tooltip on left">Tooltip on left</button>
        <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="top" title="" data-original-title="Tooltip on top">Tooltip on top</button>
        <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Tooltip on bottom">Tooltip on bottom</button>
        <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="Tooltip on right">Tooltip on right</button>
    </div>
</div>
<hr/>
<div class="bs-example tooltip-demo">
    <p>Tight pants next level <a href="#" data-toggle="tooltip" title="" data-original-title="Default tooltip">you probably</a> haven't heard of them.<a href="#" data-toggle="tooltip" title="" data-original-title="Another tooltip">
        have a</a> terry richardson vinyl chambray.<a href="#" data-toggle="tooltip" title="" data-original-title="Another one here too">whatever keytar</a>, scenester farm-to-table
        <a href="#" data-toggle="tooltip" title="" data-original-title="The last tip!">twitter handle</a> coffee viral.
    </p>
</div>
<script type="text/javascript">
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
</script>
<div style="margin-bottom: 30px;"></div>

<div class="bs-example tooltip-demo">
    <p>Tight pants next<a id="element1" href="#" data-toggle="tooltip" title="" data-original-title="Default tooltip">you probably</a> haven't heard of them. <a href="#" data-toggle="tooltip" title="" data-original-title="Another tooltip">
        have a</a> terry richardson vinyl chambray.<a href="#" data-toggle="tooltip" title="" data-original-title="Another one here too">whatever keytar</a>, scenester farm-to-table banksy Austin
        <a href="#" data-toggle="tooltip" title="" data-original-title="The last tip!">twitter handle</a> coffee viral.</p>
</div>
<hr/>
<input type="button" value="show" onclick="show1()"/>
<input type="button" value="hide" onclick="hide1()"/>
<input type="button" value="toggle" onclick="toggle1()"/>
<input type="button" value="destroy" onclick="destroy1()"/>
<script type="text/javascript">
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });

    function show1(){
        $('#element1').tooltip('show');
    }

    function hide1(){
        $('#element1').tooltip('hide');
    }

    function toggle1(){
        $('#element1').tooltip('toggle');
    }

    function destroy1(){
        $('#element1').tooltip('destroy');
    }
</script>
</body>
</html>