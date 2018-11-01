
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
    <meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
    <title>百度地图API自定义地图</title>

    <!-- 引用百度地图API，并定义格式 -->
    <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
    <style type="text/css">
        html,body{margin:0;padding:0;}
        .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
        .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
    </style>

    <%-- v 为版本号，现只支持2.0以上版本；key 为申请的密钥；但2.0版本必须申请密钥才能使用，所以先用低版本的 --%>
    <%--<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>--%>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TQl4EN7zBCoRG7nQXf27vtHgEcAjnitN"></script>

    <%-- 引入鼠标绘制地图工具--%>
    <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>

</head>

<body>
<!--百度地图容器-->
<div style="width:920px;height:800px;border:#ccc solid 1px;" id="dituContent"></div>

</body>

<script type="text/javascript">

    // 在百度地图容器中创建一个地图
    var map = new BMap.Map("dituContent");

    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker();// 不自动向地图中添加marker
    }

    // 创建地图函数：
    function createMap(){
        // 定义一个中心点坐标，经度，纬度
        var point = new BMap.Point(113.647758,34.75933);

        // 设定地图的中心点和坐标并将地图显示在地图容器中，第二个参数为显示的级别（省，市，区）
        map.centerAndZoom(point,10);

        // 鼠标单击获取点击的经纬度
        map.addEventListener("click",function(e){
            // console.log(e.point.lng + "," + e.point.lat);
            changeMark(e.point.lng , e.point.lat);
        });
        window.map = map;//将map变量存储在全局
    }

    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }

    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
        var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,type:BMAP_NAVIGATION_CONTROL_SMALL});
        map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
        var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:0});
        map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
        var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        map.addControl(ctrl_sca);
    }

    //标注点数组
    var markerArr = [];
    //创建marker
    function addMarker(){
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];

            // 定义标注坐标
            var p0 = json.point.split(",")[0];
            var p1 = json.point.split(",")[1];
            var point = new BMap.Point(p0,p1);

            // 创建图标对象
            var iconImg = createIcon(json.icon);

            // 创建标注对象并添加到地图
            var marker = new BMap.Marker(point,{icon:iconImg});
            var iw = createInfoWindow(i);
            var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
            marker.setLabel(label);

            // 设置标注可拖拽
            marker.enableDragging();
            // 设置标注不可拖拽
            // marker.disableDragging();

            //跳动的动画
            marker.setAnimation(BMAP_ANIMATION_BOUNCE);

            console.log(num);
            map.addOverlay(marker);
            label.setStyle({
                borderColor:"#808080",
                color:"#333",
                cursor:"pointer"
            });

            (function(){
                var index = i;
                var _iw = createInfoWindow(i);
                var _marker = marker;

                // 设置事件监控，更改标注的坐标
                _marker.addEventListener("click",function(e){
                    // 设置坐标经纬度
                    changeMark(e.point.lng,e.point.lat);
                });

                // 设置事件监控，打开信息窗口
                // _marker.addEventListener("click",function(){
                //     this.openInfoWindow(_iw);
                // });

                _iw.addEventListener("open",function(){
                    _marker.getLabel().hide();
                })
                _iw.addEventListener("close",function(){
                    _marker.getLabel().show();
                })
                label.addEventListener("click",function(){
                    _marker.openInfoWindow(_iw);
                })
                if(!!json.isOpen){
                    label.hide();
                    _marker.openInfoWindow(_iw);
                }
            })()
        }
    }

    function changeMark(lng,lat) {
        // map.clearOverlays();
        console.log(lng+"=="+lat);
        var json = {w:50,h:50,l:0,t:0,x:6,lb:5};
        var point = new BMap.Point(lng,lat);
        var iconImg = createIcon(json);
        var marker = new BMap.Marker(point,{icon:iconImg});

        map.addOverlay(marker);
        marker.setAnimation(BMAP_ANIMATION_BOUNCE);//跳动的动画
    }

    //创建InfoWindow
    function createInfoWindow(i){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
    }

    /**
     * 创建一个Icon：
     *  imageOffset，设置图片偏移，当需要从一幅较大的图片中截取某部分作为标注图标时，就需要指定大图的偏移位置，此做法与css sprites技术类似。
     *  anchor: new BMap.Size(10, 25)，指定定位位置。当标注显示在地图上时，其所指向的地理位置距离图标左上角各偏移10像素和25像素。
     */
    function createIcon(json){
        var icon = new BMap.Icon("${base}image/red.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    }

    //实例化鼠标绘制样式
    var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }

    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: false, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
        },
        circleOptions: styleOptions, //圆的样式
        polylineOptions: styleOptions, //线的样式
        polygonOptions: styleOptions, //多边形的样式
        rectangleOptions: styleOptions //矩形的样式

    });

    //添加覆盖物
    var num = 0;
    function add_overlay(){

        // {title:"我的标记",content:"我的备注",point:"113.647471,34.729668",isOpen:0,icon:{w:50,h:50,l:0,t:0,x:6,lb:5}}
        markerArr.push({id:num,title:"我的标记",content:"我的备注",point:"113.662994,34.787557",isOpen:0,icon:{w:50,h:50,l:0,t:0,x:6,lb:5}});
        addMarker();
        num++;

        // map.addOverlay(marker);            //增加点
        // map.addOverlay(polyline);          //增加折线
        // map.addOverlay(circle);            //增加圆
        // map.addOverlay(polygon);           //增加多边形
        // map.addOverlay(rectangle);         //增加矩形
    }

    //清除覆盖物
    function remove_overlay(){
        map.clearOverlays();
    }

    initMap();//创建和初始化地图
</script>
</html>
