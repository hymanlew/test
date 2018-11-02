
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
        .c{
            border: 1px solid gray;
        }
    </style>
</head>
<body style="padding: 20px">
<%--
    Bootstrap 是一个用于快速开发 Web 应用程序和网站的前端框架。Bootstrap 是基于 HTML、CSS、JAVASCRIPT 的。
    Bootstrap 包的内容：
        基本结构：Bootstrap 提供了一个带有网格系统、链接样式、背景的基本结构。

        CSS：Bootstrap 自带以下特性：全局的 CSS 设置、定义基本的 HTML 元素样式、可扩展的 class，以及一个先进的网格系统。

        组件：Bootstrap 包含了十几个可重用的组件，用于创建图像、下拉菜单、导航、警告框、弹出框等等。

        JavaScript 插件：Bootstrap 包含了十几个自定义的 jQuery 插件。您可以直接包含所有的插件，也可以逐个包含这些插件。

        定制：您可以定制 Bootstrap 的组件、LESS 变量和 jQuery 插件来得到您自己的版本。

    Bootstrap 需要为页面内容和栅格系统（行列布局的结构 class="row"）包裹一个 .container 容器，它提供了两个实现此功能的类。
        但要注意，由于 padding 等属性的原因，这两个类不能互相嵌套。<div class="container/container-fluid">
        .container 类用于固定宽度并支持响应式布局的容器。
        .container-fluid 类用于 100% 宽度，是占据全部窗口（viewport）的容器。
--%>

<%-- Bootstrap 的栅格系统规定最多只能是 12 列 --%>
<div class="container">
    <p>==================== 栅格系统 ================</p>
    <div class="row">
        <div class="col-md-8 c">栅格系统</div>
        <div class="col-md-4 c">test1</div>
    </div>
    <div class="row">
        <div class="col-md-4 c">栅格系统</div>
        <div class="col-md-4 c">test2</div>
        <div class="col-md-4 c">test2</div>
    </div>
    <div class="row">
        <div class="col-md-6 c">栅格系统</div>
        <div class="col-md-6 c">test3</div>
    </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div class="container">
    <p>==================== 列偏移 ================</p>
    <div class="row">
        <div class="col-md-4 c">列偏移</div>
        <div class="col-md-4 col-md-offset-4 c">test</div>
    </div>
    <div class="row">
        <div class="col-md-3 col-md-offset-3 c">列偏移</div>
        <div class="col-md-3 col-md-offset-3 c">test</div>
    </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div class="container">
    <p>==================== 列嵌套 ================</p>
    <div class="row">
        <div class="col-md-8 c">
            <div class="row">
                <div class="col-md-6 c">列嵌套</div>
                <div class="col-md-6 c">列嵌套</div>
            </div>
        </div>
        <div class="col-md-4 c">test</div>
    </div>

</div>
<div style="margin-bottom: 30px;"></div>

<div class="container-fluid">
    <p>==================== 全屏容器 ================</p>
    <div class="row">
        <div class="col-md-6 c">栅格系统</div>
        <div class="col-md-6 c">test3</div>
    </div>
</div>
<div style="margin-bottom: 30px;"></div>

<div>
    <button type="button" class="btn btn-danger">红色按钮</button>
    <button type="button" class="btn btn-danger" onclick="next()">下一页</button>
</div>
<div style="margin-bottom: 30px;"></div>

<div class="container">
    <p>==================== 改变大小写 ================</p>
    <p class="text-lowercase">TEST WORD</p>
    <p class="text-uppercase">test word</p>
    <p class="text-capitalize"><abbr title="首字母大写">test word</abbr></p>
</div>
<div style="margin-bottom: 30px;"></div>

<div class="container">
    <p>==================== 页脚并靠右排列 ================</p>
    <blockquote class="blockquote-reverse">
        <p>页脚并靠右排列</p>
        <footer>setting by hyman</footer>
    </blockquote>
</div>

<address>
    <strong>Twitter, Inc.</strong><br>
    795 Folsom Ave, Suite 600<br>
    San Francisco, CA 94107<br>
    <abbr title="Phone">P：</abbr> (123) 456-7890
</address>
<address>
    <strong>Full Name</strong><br>
    <a href="mailto:#">first.last@example.com</a>
</address>
</div>
<div style="height: 5px"></div>

<p class="text-left">Left aligned text.</p>
<p class="text-center">Center aligned text.</p>
<p class="text-right">Right aligned text.</p>
<p class="text-justify">Justified text.</p>
<p class="text-nowrap">No wrap text.No wrap text.</p>
<hr/>

<div class="well">
    Look, I'm in a well!
</div>
<hr/>
<div class="well well-lg">
    Look, I'm in a large well!
</div>
<hr/>
<div class="well well-sm">
    Look, I'm in a small well!
</div>
<hr/>

<pre>代码块：<var>y</var>=<var>x</var>+<var>m</var></pre>
<div style="margin-bottom: 30px;"></div>

内联代码, <code>&lt;section&gt;</code> should be wrapped as inline.
<hr/>

用户输入, type <kbd>cd</kbd> followed by the name of the directory.<br>
To edit settings, press <kbd><kbd>ctrl</kbd> + <kbd>,</kbd></kbd>
<hr/>

<pre>&lt;p&gt;代码块...&lt;/p&gt;</pre>
<hr/>
</body>
<script type="text/javascript">
    function next() {
        location.href= "${base}edit/bootstrap1";
    }
</script>
</html>
