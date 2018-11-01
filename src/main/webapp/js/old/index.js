
// 利用 layui 的模块规范建立一个入口文件，即基础文件，并通过 layui.use() 方式来加载该入口文件
// 下面的 index 即为你 webapp/js/ 目录下的 index.js
// layui.config({base:'../js/'}).use('index');

// 也可以直接写入 js 代码，而调用代码，不调用文件
// layui很多组件依赖element模块，所以记住不能忘记加载。
layui.config(
    // 存放新模块的目录，注意不是layui的模块目录
    {base:'../js/old/'})
    .use(['element', 'layer','navbar','tab'],function () {
        var $ = layui.jquery,
            element = layui.element,
            layer = layui.layer,
            navbar = layui.navbar(),
            tab = layui.tab({
                elem:'.admin-nav-card' //设置选项卡容器
    });

    //iframe自适应
    $(window).on('resize', function() {
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - 85);
        $content.find('iframe').each(function() {
            $(this).height($content.height());
        });
    }).resize();

    // 如果要进行设置 navbar，则必须要在声明中将其定义为方法（在 use 时，上面）
    navbar.set({
        spreadOne: true,
        elem: '#leftSite',
        cached: true,
        // data 为菜单项，事先定义
        data: navs
        /*cached:true,
        url: 'datas/nav.json'*/
    });
    //渲染navbar
    navbar.render();
    //监听点击事件
    navbar.on('click(side)', function(data) {
        tab.tabAdd(data.field);
    });

    $('.admin-side-toggle').click(function() {
        var sideWidth = $('#admin-side').width();
        if(sideWidth === 250) {
            $('#admin-body').animate({
                left: '0'
            }); //admin-footer
            $('#admin-footer').animate({
                left: '0'
            });
            $('#admin-side').animate({
                width: '0'
            });
        } else {
            $('#admin-body').animate({
                left: '250px'
            });
            $('#admin-footer').animate({
                left: '250px'
            });
            $('#admin-side').animate({
                width: '250px'
            });
        }
    });

    //锁屏
    $(document).on('keydown', function() {
        var e = window.event;
        if(e.keyCode === 76 && e.altKey) {
            //alert("你按下了alt+l");
            lock($, layer);
        }
    });
    $('#lock').on('click', function() {
        lock($, layer);
    });

    //手机设备的简单适配
    var treeMobile = $('.site-tree-mobile'),
        shadeMobile = $('.site-mobile-shade');
    treeMobile.on('click', function() {
        $('body').addClass('site-mobile');
    });
    shadeMobile.on('click', function() {
        $('body').removeClass('site-mobile');
    });
});

var navs = [{
        "title": "运营后台",
        "icon": "fa-user",
        "spread": true,
        "children": [{
            "title": "企业信息管理",
            "href": "../edit/showEdit"
        }, {
            "title":"设备信息管理",
            "href":"../edit/introduce"
        },{
            "title":"设备心跳管理",
            "href":""
        }]
}];