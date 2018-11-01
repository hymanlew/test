<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/25/025
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="base" scope="request" value="${pageContext.request.contextPath}/"></c:set>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${base}ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${base}ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${base}ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="${base}ueditor/ueditor.parse.js"></script>
    <style>
        input { margin: 13px 15px; height: 34px;}
    </style>
</head>
<body>

<div id="editModule" style=" margin: 4% 4%;">
    <div style="    width: 50%; position: absolute;">
    <form action="">
        <div>
            <label >标题</label>
            <div >
                <input type="text" name="title" id="title" autocomplete="off" placeholder="请输入标题" style="width: 800px;">
            </div>
        </div>
        <div style="width: 85%;">
            <label >显示数字</label>
            <div>
                <input type="text" placeholder="请输入标题" value="正常的数： ${lnum}">
                <input type="text" placeholder="请输入标题" value="被科学计算：${wnum}" />
                <input type="text" placeholder="请输入标题" value="用 DecimalFormat 转换：${snum}" style="width: 260px;">
                <input type="text" placeholder="请输入标题" value="用 jstl 标签：<fmt:formatNumber value="${wnum}"
                                                minFractionDigits="2" type="number"></fmt:formatNumber>" style="width: 210px;">
            </div>
        </div>
        <div>
            <p >内容</p>
            <div>
                <script id="context"  name="content" style="width:800px;height:400px;" type="text/plain"></script>
                <script type="text/javascript">
                    // 其详情配置，在 ueditor.config.js 文件中修改
                    var ue = UE.getEditor('context');

                    // 对编辑器的操作最好在编辑器ready之后再做
                    ue.ready(function () {
                        ue.setContent("hi,everyone!           显示一个比较大的数： " + ${num} + ",     显示一个大的小数：" +
                            ${dnum} + "，      显示科学计算法的数：  " + ${wnum} + "，      显示被转换的数：  " + ${snum});
                        var html = ue.getContent();

                        // 注意不是，getContentText();
                        var text = ue.getContentTxt();
                        console.log("html :"+html);
                        console.log("text :"+text);
                    });

                </script>
            </div>
            <div>
                <div>
                    <a href="javascript:;" onclick="publishNews()">立即提交</a>
                    <button type="reset">重置</button>
                </div>
            </div>
        </div>
    </form>
    </div>
    <div style="  width: 49%;  margin-left: 43%; position: absolute;    margin-top: 7%;">
        <p style=" background-color: antiquewhite;    padding: 19px 19px;">
            在百度编辑器文件jsp下有一个lib包，里面的jar包虽然已经构建到项目了，但是要把这个lib包放到WEB-INF下，虽然不知道为什么，
            可能解决这个错误，关于图片上传功能也可以使用了（配置完config.json后显示后端配置项没有正常加载，上传插件不能正常使用！）
        </p>
        <div id="result"></div>
    </div>
</div>
<script type="text/javascript">

    function publishNews() {
        // var data = ue.getContent();
        var data = UE.getEditor('context').getContent();
        $.post("${base}edit/saveData",{"data":data},function (result) {
            console.log(result);
            $("#result").html(result);
        });
    }
    uParse("#context",{
        rootPath: '../'
    });
</script>

</body>
</html>
