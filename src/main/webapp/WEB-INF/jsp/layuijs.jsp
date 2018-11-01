<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/6/006
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/" />

<html>
<head>
    <title>Title</title>
    <meta http-equiv=Content-Type content=text/html; charset=UTF-8>
    <meta http-equiv=Content-Type content=application/json; charset=UTF-8>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="${base}layui/css/layui.css">

    <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${base}layui/layui.js"></script>
    <script type="text/javascript" src="${base}js/old/index.js"></script>

    <%--
        正常情况下，自适应的列宽不会导致 table 容器出现滚动条。如果你的出现了，请按照下述方案解决：
1. 如果 table 是在 layui 的后台布局容器中（注意：不是在 iframe 中），在你的页面加上这段 CSS：
.layui-body{overflow-y: scroll;}
2. 如果 table 是在独立的页面，在你的页面加上这段 CSS：
body{overflow-y: scroll;}
总体而言，table 列宽自适应出现横向滚动条的几率一般是比较小的，主要原因是 table 的渲染有时会在浏览器纵向滚动条出现之前渲染完毕，这时 table 容器会被强制减少滚动条宽度的差（一般是 17px），导致 table 的横向滚动条出现。建议强制给你的页面显示出纵向滚动条。
    --%>
    <style type="text/css">
        .layui-body{overflow-y: hidden;}
    </style>
</head>
<body class="layui-layout-body">
    <%--
    layui 主目录：

      ├─css             //css目录
      │  │─modules      //模块css目录（一般如果模块相对较大，我们会单独提取，比如下面三个：）
      │  │  ├─laydate
      │  │  ├─layer
      │  │  └─layim
      │  └─layui.css    //核心样式文件
      ├─font            //字体图标目录
      ├─images          //图片资源目录（目前只有layim和编辑器用到的GIF表情）
      │─lay             //模块核心目录
      │  └─modules      //各模块组件
      │─layui.js        //基础核心库
      └─layui.all.js    //包含layui.js和所有模块的合并文件
    --%>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">后台布局</div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">控制台</a></li>
                <li class="layui-nav-item"><a href="">商品管理</a></li>
                <li class="layui-nav-item"><a href="">用户</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其它系统</a>
                    <%--
                        ul 无序列表，
                        ol 有序列表，
                        < dl>< /dl>用来创建一个普通的列表,
                        < dt>< /dt>用来创建列表中的上层项目，
                        <dd>< /dd>用来创建列表中最下层项目，
                        < dt>< /dt>和< dd>< /dd>都必须放在< dl>< /dl>标志对之间。
                    --%>
                    <dl class="layui-nav-child">
                        <dd><a href="">邮件管理</a></dd>
                        <dd><a href="">消息管理</a></dd>
                        <dd><a href="">授权管理</a></dd>
                    </dl>
                </li>
            </ul>

            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;"><img src="http://t.cn/RCzsdCq" class="layui-nav-img">Hyman</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">基本资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">退了</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航）,使用遍历来根据权限显示 -->
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">所有商品</a>
                        <dl class="layui-nav-child">
                            <c:forEach var="nav" varStatus="i" items="${navs}"></c:forEach>

                        </dl>
                    </li>
                    <li class="layui-nav-item" id="leftSite"></li>
                    <li class="layui-nav-item"><a href="">云市场</a></li>
                    <li class="layui-nav-item"><a href="">发布商品</a></li>
                    <li class="layui-nav-item"><a href="/index.jsp">去首页</a></li>
                </ul>
            </div>
        </div>

        <!-- 内容主体区域，需要自定义宽度 -->
        <div class="layui-body">
            <div style="padding: 15px;">
                内容主体区域，并使用 br 连续换行。
                <br>
                <br>
                <blockquote class="layui-elem-quote">
                    layui 之所以赢得如此多人的青睐，更多是在于它“前后台系统通吃”的能力。既可编织出绚丽的前台页面，又可满足繁杂的后台功能需求。
                    <br>layui 后台布局， 致力于让每一位开发者都能轻松搭建自己的后台模板。
                </blockquote>
            </div>
            <%-- 没有工作，不可用 --%>
            <ul class="layui-tab-title"></ul>
            <div class="layui-tab-content"></div>

            <table class="layui-hide" id="test"></table>

            <table class="layui-hide" id="table1" lay-filter="test"></table>
        </div>

        <!-- 底部固定区域 -->
        <div class="layui-footer">© www.hyman.com - 底部固定区域</div>
    </div>

    <script>

        // 为表格分页，必须要在此处先声明全局变量
        layui.use(['table','layer'], function(){
            var table = layui.table;
            var layer = layui.layer;

            // dopage(table);
            test1(table);
            // test2();
            // test3();
        });

        function dopage(table) {
            /**
             *  layer 是 layui 自动加载的全局变量，所以可以直接拿来使用。
             *  但如果是自定义的变量，只能在 layui.use 里面使用，或者传递给 function 函数，或者定义为全局变量。
             */
            layer.alert('${test}');

            /**
             * 方法渲染：是指用 js 方法实现表格生成的形式。自动渲染：是指在 table html 中声明表头设置，HTML配置，自动渲染。
             * 下述 lay-data 里面的内容即为基础参数项，切记：值要用单引号
             * <table lay-data="{height:300, url:'/api/data'}" lay-filter="demo"> …… </table>
             *
             * cols，设置表头。值是一个二维数组。方法渲染方式必填
             * field，表头字段名，并且名字必须与接收的实体类字段名称相同。
             * sort，是否排序
             * minWidth，width
             * templet，值是模板元素的选择器，指向自定义的 <script id="demo">。
             *
             * request，response，如果没有自定义的请求和响应参数，可不加该参数，但是接收时默认还是有 response 格式的。
             * request，并且如果也没有自定义参数，分页参数传递时，默认会发送 Integer page（页数）, Integer limit（每页数量） 两个参数，所以后台一定要接收。
             *          并且在它里面只能定义分页相关的参数。
             */

            // render 执行，指定函数：
            table.render({
                elem: '#test'
                ,method: 'post'
                ,url:'${base}layui/table'
                // ,request:{
                //     // 请求参数列表，只能定义分页相关的参数。
                // }
                ,where: {
                    // 定义其他参数，如果无需传递额外参数，可不加该参数
                    name:"测试中文传递"
                }
                // ,response: {
                //     // statusName: 'status' //数据状态的字段名称，默认：code
                //     // ,statusCode: 200 //成功的状态码，默认：0
                //     // ,msgName: 'hint' //状态信息的字段名称，默认：msg
                //     // ,countName: 'total' //数据总数的字段名称，默认：count
                //     // ,dataName: 'rows' //数据列表的字段名称，默认：data
                // }
                ,cols: [[
                    {field:'id', width:80, title: 'ID', sort: true}
                    ,{field:'name', width:80, title: '用户名'}
                    ,{field:'sex', width:80, title: '性别', sort: true}
                    ,{field:'city', width:100, title: '城市'}
                    ,{field:'sign', title: '签名',minWidth:100}
                    // {field:'title', title: '文章标题', width: 200, templet: '#titleTpl'} //这里的值是模板元素的选择器
                ]]
                ,page: true // 开启分页
                ,done:function (res, curr, count) {
                    /**
                     * 无论是异步请求数据，还是直接赋值数据，都会触发该回调。你可以利用该回调做一些表格以外元素的渲染。
                     *
                     * 如果是异步请求数据方式，res 即为你接口返回的信息。
                     * 如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                     *
                     * curr（当前页码），count（数据总量）
                     */
                    layer.alert(res.msg);
                }
            });

        }

        function test1(table) {
            /**
             * cellMinWidth，定义全局的宽度，使表格自动分配宽度
             * height，自定义总高度
             *
             * page，自定义分页，支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem）
             *
             * limit，每页默认显示的数量（默认：10），值一定要对应 limits 参数的选项（即可选的数量），优先级低于 page 参数
             * 中的 limit 参数。需要注意，此两个参数只适合于静态生成的表格，不适用此方法的接口。
             */
            table.render({
                elem: "#table1"
                ,method:"post"
                // ,height: 315
                // ,cellWidth: 80
                ,url:'${base}layui/table1'
                ,cols: [[
                {field:'id', title: 'ID', sort: true}
                ,{field:'name', title: '用户名'}
                ,{field:'sex', title: '性别', sort: true}
                ,{field:'city', title: '城市'}
                ,{field:'sign', title: '签名'}
                ]]
                // 此两个参数只适于静态生成的 table
                ,limit: 3
                ,limits: [3,6,10]
                ,page: {
                    // 自定义分页布局
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                    // 设定初始在第 1 页
                    ,curr: 1
                    //只显示 3 个连续页码
                    ,groups: 3
                    // 显示首页和尾页
                    ,first: true
                    ,last: true

                }
                ,done : function(map) {
                    var list=map.msg;
                   console.log(list);
                }
            });
        }

        function test2() {
            $.ajax({
                type:"post",
                async:false,
                data:{
                },
                url:'${base}layui/table2',
                dataType : "json",
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                },
                success : function(map) {
                    console.log(map);
                }
            });
        }

        function test3() {
            $.ajax({
                type:"post",
                async:false,
                data:{
                },
                url:'${base}layui/table3',
                dataType : "json",
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                },
                success : function(map) {
                    console.log(map);
                }
            });
        }

        /**
         * 事件监听：table.on('event(filter)', callback); event为内置事件名，filter为容器 lay-filter设定的值。
         *
         * table模块在 Layui 事件机制中注册了专属事件 checkbox(filter)复选框，edit(filter)单元格编辑，sort(filter)表头排序，
         *
         * table.on('checkbox(test)', function(obj){
  console.log(obj.checked); //当前是否选中状态
  console.log(obj.data); //选中行的相关数据
  console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
});
         * 如果你使用layui.onevent()自定义模块事件，请勿占用table名。目前所支持的所有事件见下文

         默认情况下，事件所监听的是全部的table模块容器，但如果你只想监听某一个容器，使用事件过滤器即可。
         假设原始容器为：<table class="layui-table" lay-filter="test"></table> 那么你的事件监听写法如下：
         */

    </script>

</body>
</html>
