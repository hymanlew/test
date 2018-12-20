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
    <title>Title</title>

    <style type="text/css">
        .c {
            border: 1px solid gray;
        }
    </style>
</head>
<body style="padding: 20px">

<div>
    <button type="button" class="btn btn-danger" onclick="last()">上一页</button>
    <button type="button" class="btn btn-danger" onclick="next()">下一页</button>
</div>
<div style="margin-bottom: 30px;"></div>


<%--
    将任何 .table 类型包裹在 .table-responsive 类型内，即可创建响应式表格，其会在小屏幕设备上（小于768px）水平滚动。当屏幕
    大于 768px 宽度时，水平滚动条消失。
    FireFox 浏览器对 fieldset 元素设置了一些影响 with 属性的样式，导致响应式表格出现问题。只有使用下面针对 FireFox 的 hack
    代码，否则无解。
    @-moz-document url-prefix{
        fieldset { display: table-cell; }
    }

    同样的，如果为图片添加 .img-responsive 样式，则可以让图片支持响应式布局。
--%>
<table class="table table-hover">
    <tr>
        <td>鼠标悬停</td>
        <td>222</td>
        <td>333</td>
    </tr>
    <tr>
        <td>改变颜色</td>
        <td>222</td>
        <td>333</td>
    </tr>
    <tr>
        <td>111</td>
        <td>222</td>
        <td>333</td>
    </tr>
</table>
<div style="margin-bottom: 30px;"></div>


<%--
    bootstrap 框架可以创建具有响应式特性的嵌入内容，即它会根据被嵌入内容的外部容器的宽度，自动调整显示比例。
    外部容器使用 embed-responsive embed-responsive-16by9（16比9）、或者 4by3（4比3）的样式
    内部嵌入内容使用 embed-responsive-item 的样式
--%>
<%--
    well 样式用在元素上，能有嵌入（insert）的效果，
--%>

<%--
    模态框：
        fade 是弹出的效果，
        data-toggle 用于关联到指定类型的元素，
        data-target 用于指向模态框的 id，
        dismiss 是解散，解除弹窗，即关闭

   也可以使用 js 调用弹窗，$("#modelStrong").modal(options);  options 代表设置具体的属性，但是一般都使用默认值，不需设置。
--%>
<div>
    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modalNomal">普通弹窗</button>
    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modelStrong">增强弹窗</button>
</div>
<div class="modal fade" id="modalNomal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="lablenomal">自定义弹窗</h4>
            </div>
            <div class="modal-body">
                <p>111122223333</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">保存退出</button>
            </div>
        </div>
    </div>
</div>

<%--
    增强模态框：
        role="dialog" （对话属性）该属性是必须有的，它带有其他的功能，如果没有则其他功能无效，
        aria-labelledby="myModallable" 用于指向模态框的标题栏，
        aria-hidden="true" 用于默认隐藏，
        aria-describedby 用于给模态框添加描述信息
--%>
<div class="modal fade" id="modelStrong" tabindex="1" role="dialog" aria-labelledby="lableStrong" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="lableStrong">自定义弹窗</h4>
            </div>
            <div class="modal-body">
                <p>111122223333</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">保存退出</button>
            </div>
        </div>
    </div>
</div>
<div style="margin-bottom: 30px;"></div>


<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Open modal for @mdo</button>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@fat">Open modal for @fat</button>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@twbootstrap">Open modal for @twbootstrap</button>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">New message</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">Recipient:</label>
                        <input type="text" class="form-control" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">Message:</label>
                        <textarea class="form-control" id="message-text"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Send message</button>
            </div>
        </div>
    </div>
</div>
<div style="margin-bottom: 30px;"></div>




<div style="margin-bottom: 30px;"></div>
<%-- 在工具提示，或是弹出框中的事件，show = toggle，hidde = destroy，显示和隐藏的方法要匹配起来。 --%>
</body>
<script type="text/javascript">
    function last() {
        location.href = "${base}edit/bootstrap";
    }
    function next() {
        location.href = "${base}edit/bootstrap1";
    }

    // 定义弹窗在各种状态下行为
    // $('#modelStrong').on('show.bs.modal', function (e) {
    //     alert("打开");
    // });
    // $('#modelStrong').on('shown.bs.modal', function (e) {
    //     alert("已显示");
    // });
    // $('#modelStrong').on('hide.bs.modal', function (e) {
    //     alert("隐藏");
    // });
    // $('#modelStrong').on('hidden.bs.modal', function (e) {
    //     alert("已隐藏");
    // });

    // function openDialog(){
    //     // 默认是true
    //     $('#modelStrong').modal({
    //         backdrop: false
    //     });
    // }
    // function openDialog2(){
    //     // 默认是true
    //     $('#modelStrong').modal({
    //         keyboard: true
    //     });
    // }
    // function openDialog3(){
    //     // 默认是true
    //     $('#modelStrong').modal({
    //         show: false
    //     });
    // }

    // 在触发弹窗打开的事件时：
    $('#exampleModal').on('show.bs.modal', function (event) {
        // 获取按钮关联的模态框
        var button = $(event.relatedTarget);
        var recipient = button.data('whatever');

        // 拿到当前的模态框
        var modal = $(this);
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
    })
</script>
</html>
