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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="${base}layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${base}css/sonfram.css">

    <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${base}layui/layui.js"></script>
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
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">基础功能1</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" id="module">Module-弹窗</a></dd>
                            <dd><a href="javascript:;" id="showFrame">Iframe-层</a></dd>
                            <dd><a href="javascript:;" id="load">load-效果</a></dd>
                        </dl>
                    </li>

                    <!-- 左侧导航区域（可配合layui已有的垂直导航）,使用 foreach 遍历来根据权限显示 -->
                    <li class="layui-nav-item">
                        <a href="javascript:;">基础功能2</a>
                        <dl class="layui-nav-child">
                            <c:forEach var="nav" varStatus="i" items="${navs}"></c:forEach>

                            <dd><a href="javascript:;" class="activeTab" data-type="tabAdd">列表一</a></dd>
                            <dd><a href="javascript:;" class="activeTab" data-type="tabAdd">列表二</a></dd>
                            <dd><a href="" class="activeTab" data-type="tabAdd">超链接</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a href="">云市场</a></li>
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
                <%--
                    blockquote 标签之间的所有文本都会从常规文本中分离出来，经常会在左、右两边进行缩进（增加外边距），而且有时会使用斜体。
                    也就是说，块引用拥有它们自己的空间。
                --%>
                <blockquote class="layui-elem-quote">
                    layui 之所以赢得如此多人的青睐，更多是在于它“前后台系统通吃”的能力。既可编织出绚丽的前台页面，又可满足繁杂的后台功能需求。
                    <br>layui 后台布局， 致力于让每一位开发者都能轻松搭建自己的后台模板。
                </blockquote>
            </div>
            <div>
                <button id="iframevalue" class="layui-btn">没改变之前</button>
            </div>

            <div class="layui-tab" lay-filter="demoTab" lay-allowclose="true">
                <ul class="layui-tab-title">
                    <li class="layui-this" lay-id="1">网站设置</li>
                    <li lay-id="2">用户管理</li>
                    <li lay-id="3">权限分配</li>
                    <li lay-id="4">订单管理</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">内容1</div>
                    <div class="layui-tab-item">内容2</div>
                    <div class="layui-tab-item">内容3</div>
                    <div class="layui-tab-item">内容5</div>
                </div>
            </div>
            <div class="site-demo-button" style="margin-bottom: 0;">
                <button class="layui-btn activeTab" data-type="tabDelete">删除第一个 Tab</button>
                <button class="layui-btn activeTab" data-type="tabChange">切换到第二个</button>
            </div>
        </div>


        <!-- 底部固定区域 -->
        <div class="layui-footer">© www.hyman.com - 底部固定区域</div>
    </div>

    <%-- 基础功能1 --%>
    <script>
        //注意：导航 navbar 依赖于 element 模块，否则无法进行功能性操作
        layui.use(['layer','form','element'],function () {
            var layer = layui.layer,
                form = layui.form,
                element = layui.element;

            //弹出一个提示层
            layer.msg("暂时弹出的窗口，会自动消失！");
        });

        //弹出一个页面层
        $("#module").on('click',function () {
            layer.open({
                type:1,
                area:['600px','360px'],
                // 点击旁边区域，自动关闭遮罩
                shadeClose:true,
                content:'\<\div style="padding:20px;">显示自定义内容，或功能\<\/div>'
            });
        });

        //弹出一个iframe层
        $("#showFrame").on('click',function () {
            layer.open({
                type:2,
                title:'Iframe 示例',
                // 可最大最小化
                maxmin:true,
                shadeClose:true,
                area:['800px','800px'],
                // 其路径是跟随当前路径同级的，即是一个父目录，并且是同类的。
                // 即要么是全是请求，要么全是页面路径
                content:'layuiframe'
            });
        });

        //弹出一个 loading 层
        $("#load").on('click',function () {
            var load1 = layer.load();
            // 此处用 setTimeout演示ajax的回调，只运行- 次
            setTimeout(function () {
                layer.close(load1);
            },1000);
        });
    </script>

    <%-- 基础功能2 --%>
    <script>
        <%-- 事先定义 tab-id --%>
        var lid = 5;

        //Tab的切换功能，切换事件监听等，需要依赖element模块
        layui.use('element', function(){
            var $ = layui.jquery
                ,element = layui.element;

            //触发事件，必须声明在 layui 块中
            var active = {
                tabAdd: function(){
                    //新增一个Tab项，实际使用时一般是规定好的id，这里以时间戳模拟下
                    element.tabAdd('demoTab', {
                        title: $(this).text(),
                        content: '<iframe src="${base}edit/introduce" ></iframe>',
                        // id: new Date().getTime(),
                        id:lid
                    });
                    lid++;
                }
                ,tabDelete: function(othis){
                    //删除指定Tab项
                    element.tabDelete('demoTab', '1');


                    othis.addClass('layui-btn-disabled');
                }
                ,tabChange: function(){
                    //切换到指定Tab项
                    element.tabChange('demoTab', '2');
                }
            };

            $('.activeTab').on('click', function(){
                // 对应当前对象的 data-type，并执行事先声明好的事件
                var othis = $(this), type = othis.data('type');
                active[type] ? active[type].call(this, othis) : '';
                if(type=='tabAdd'){
                    console.log(othis.text()+"："+(lid-1));
                    element.tabChange('demoTab', lid-1);
                }
            });

            // Hash地址的定位
            var layid = location.hash.replace(/^#test=/, '');
            element.tabChange('test', layid);

            element.on('tab(test)', function(elem){
                location.hash = 'test='+ $(this).attr('lay-id');
            });
        });

    </script>
</body>
</html>
