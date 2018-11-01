<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/7/007
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/" />

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${base}layui/css/layui.css">

    <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
    <%--<script type="text/javascript" src="${base}layui/layui.js"></script>--%>
</head>
<body style="padding: 30px;">
<h1>hello!</h1>
<h3>
    这是一个新的页面，您可以通过父窗口得到本页面的 DOM 对象，从而操作这里的一切。
    当然，也可以在这里操作改变父窗口内的值。
</h3>
<div>
    <strong>要传递的值</strong>
    <input id="name" class="layui-input">
</div>
<div>
    <button class="layui-btn layui-btn-primary" id="adjust">让页面自适应窗口</button>
    <button class="layui-btn layui-btn-primary" id="tip">弹出一个提示</button>
    <button class="layui-btn layui-btn-primary" id="change">改变父窗口的值</button>
    <button class="layui-btn layui-btn-primary" id="close">关闭该窗口</button>
</div>
</body>

<script>
    <%--
        注意：parent 是 JS 自带的全局对象，可用于操作父页面。并且这样两个页面可以共用一个 layer 对象。

        parent：是指包含当前分割窗口的父窗口。如果在一个窗口内有分割窗口，而在其中一个分割窗口中又包含着分割窗口，则第2层的
                分割窗口可以用parent变量引用包含它的父分割窗口。 window.parent 是iframe页面调用父页面对象。

        opener：是指用 WINDOW.OPEN 等方式创建的新窗口对应的原窗口。对打开当前窗口的window对象的引用，如果当前窗口被用户打开，
                则它的值为null。

                self：自引用属性，是对当前window对象的应用，与window属性同义。
                self 代表自身窗口，opener 代表打开自身的那个窗口，比如窗口A打开窗口B。
                如果靠window.open方法，则对于窗口B，self代表B自己，而opener代表窗口A。

        附：Window对象、Parent对象、Frame对象、Document对象和Form对象的阶层关系：
        Windows对象 → Parent对象 → Frame对象 → Document对象 → Form对象。
    --%>

    // 获取窗口索引，当前为 iframe 页面，所以索引为 2
    // 并且只要当前页面中，无论父页面，子页面，只要有弹出窗口，或是提示窗口，index 都会自动加 1.
    var index = parent.layer.getFrameIndex(window.name);
    console.log(index);

    $("#adjust").on("click",function () {
        $("body").append("在 body 的末尾加入内容！");
        parent.layer.iframeAuto(index);
    });

    $("#tip").on("click",function () {
        parent.layer.msg("我又弹出来了！shade 设置透明度",{shade:0.3});
    });

    $("#change").on("click",function () {
        parent.$("#iframevalue").text("被 iframe 页面改了！");
        parent.layer.tips("瞧这里！并设置时间秒数！",'#iframevalue',{time:3000});
        // 自动关闭指定索引的页面
        parent.layer.close(index);
    });

    $("#close").on("click",function () {
        var name = $("#name").val();
        if(name==""){
            parent.layer.msg("没有填东西！");
            return;
        }
        parent.layer.msg("你在子页面填了："+name);
        // 自动关闭指定索引的页面
        parent.layer.close(index);
    });

    <!--
    jQuery、js调用iframe父窗口与子窗口元素的方法：

        子页面获取父页面的id=care的子页面， parent.care.location.reload();

        父页面获取id=imp的子页面， imp.location.reload();

        1. jquery在 iframe 子页面获取父页面元素和方法代码如下:

            parent.$("selector");
            parent.method();

        3.js在iframe子页面获取父页面元素代码如下:

            window.parent.document.getElementById("元素id");
            var fa = window.parent.document.getElementById("addCompany");    $(fa).hide();

        4.js在父页面获取iframe子页面元素代码如下:

            window.frames["iframe_ID"].document.getElementById("元素id");

        5.方法调用：
            子页面调用父页面方法：parent.window.parentMethod();

        6.DOM元素访问：
            获取到页面的window.document对象后，即可访问DOM元素
     -->


    // 获取到当前页面内 iframe 窗口内的方法，并实现调用
    var doIFrameFunc = function (v_mymethod, v_params, v_frmName) {

        if (document.getElementById(v_frmName)) {
            var fn = document.getElementById(v_frmName).contentWindow[v_mymethod];
            if (fn) {
                if (v_params == null){
                    return fn();
                }else {
                    return fn.apply(this, v_params);
                }
            }
            return null;
        }
    }

    function submit() {
        doIFrameFunc('save','','fra');
    }

    // iframe 子页面操作其父页面内的元素
    function closeMain() {
        var fa = window.parent.document.getElementById("addCompany");
        $(fa).hide();
    }

    <%-- js 遍历 --%>
    var statusValue = {
        "001":"成功",
        "002":"借方账户余额不足",
        "003":"该账户不存在",
        "004":"该卡号不存在",
        "005":"卡片状态为已换卡未启用",
        "006":"该账户代码或子账户序号不存在",
        "007":"该卡为作废卡",
        "008":"借方卡号状态非正常",
        "009":"您的账户已被停止非柜面服务，请您携带有效证件到我行网点办理",
        "010":"您的当前账户为6个月无交易新账户,已停止非柜面服务,请联系我行",
        "011":"账户为长期不动户，拒绝交易",
        "012":"贷方账户日累计交易金额超限",
        "013":"户名身份证号不符",
        "014":"该身份证号不存在",
        "015":"没有此工人信息",
        "016":"该工人名称不匹配",
        "017":"该工人身份证号码不匹配"
    };

    function jsbianli(code) {
        var html = "";
        for(var num in statusValue){
            if(code!=null && code==num){
                html+='<td>'+statusValue[num]+'</td>';
            }
        }
    }

    // onclick="selectqy('+row.id+'\,\''+row.name+'\',\''+row.type+'\')"，在js 方法中传递中文参数，必须加转义符

</script>
</html>
