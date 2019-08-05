
/**
 * 自定义右键菜单
 * 标准表格页面配置文件
 * 小娄创建于2019-06-29
 * */

var currCol=0,currRow=0;
var tableClass = {
    windowType:'undefined'
    ,gtNum:0//表头默认行数-1
    ,windowWidth:0//当前表格宽度
    ,windowLen:10000 //表格显示列数
    ,settings:{
        btnId: "#create",        //筛选按钮默认id
        contentPosi: "#main-content",   //配置列表相对添加的盒子id
    }
    ,fieldJson:{}
    //重置表格序号
    ,resetOrder:function(){
        $(".department-box table tr").each(function (index,item){
            if(index > 0){
                $(item).find("td").eq(0).find("div").html(index);
            }
        })
    }
    //表头固定(冻结)
    ,copyTableHead:function(){//复制table表格及其表头
        $("#copyTableHead").remove();
        var tableCopy = $(".department-box table").clone(true);
        if($(tableCopy).find("tr:eq(1) td:eq(0)").attr('field')){
            $(tableCopy).find("tr:gt(1)").remove();
        }else{
            $(tableCopy).find("tr:gt(0)").remove();
        }
        $(tableCopy).attr("id","copyTableHead");
        $(".marginbox").css({
            "position": "relative"
        })
        $(tableCopy).css({
            "position": "absolute",
            "left":"1px",
            "top": "1px",
            "width": $(".department-box").outerWidth()-1
        })
        $(".marginbox").append(tableCopy);
    }
    //表格列冻结
    ,copyTableCol:function(leftIndex){
        $(".department-box table tr").each(function(i, item) {
            var tmpHtml = '';
            for (var j = 0; j <= leftIndex; j++) {
                tmpHtml += '<td style="height:' + $(this).find('td:eq(' + j + ')').height() + 'px;width:' +
                    $(this).find('td:eq(' + j + ')').innerWidth() + 'px">' + $(this).find('td:eq(' + j + ')').html() + '</td>';
            }
            leftHtml2 += '<tr>' + tmpHtml + '</tr>';
        });

    }
    //跟随鼠标定位方法
    ,offsetFix:function(obj,dom){
        var winWid = $(window).width();
        var winHei = $(window).height();
        var lef = $(obj).offset().left;
        var top = $(obj).offset().top;
        var wid = $(obj).outerWidth();
        var hei = $(obj).outerHeight();
        var rightClickWid = $(dom).width();
        var rightClickHei = $(dom).height();
        if((lef+wid/2+rightClickWid) > winWid){
            $(dom).css({
                "left":(winWid - rightClickWid -10)
            }).show();
        }else {
            $(dom).css({
                "left":lef+wid/2
            }).show();
        }
        if((top+hei/2+rightClickHei) > winHei){
            $(dom).css({
                "top": (winHei -rightClickHei - 10)
            })
        }else {
            $(dom).css({
                "top": top+hei/2-11//调整定时精算弹框定位添加的-11
            })
        }
        $(".right_click_window_box").getNiceScroll().resize();
    }
    //获取访问连接携带的参数
    ,getQueryString:function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if ( r != null ){
            return unescape(r[2]);
        }else{
            return null;
        }
    }
    //配置列重置表格
    ,resetTable:function(field,isCurr){
        var theadtdIndex = $(".marginbox .department-box table").find("tr").eq(0).find("td[field='"+field+"']").index();
        var theadtdCosapn = $(".marginbox .department-box table").find("tr").eq(0).find("td[field='"+field+"']").attr("colspan");
        if(theadtdCosapn){
            theadtdCosapn = Number(theadtdCosapn);
        }else {
            theadtdCosapn = 1;
        }
        var startTdIndex = 0,endTdIndex = 0;
        $(".marginbox .department-box table").find("tr").eq(0).find("td").each(function (index,item){
            if(index < theadtdIndex){
                var num = $(item).attr("colspan");
                if(num){
                    num = Number(num);
                }else {
                    num = 1;
                }
                console.log("num"+num);
                startTdIndex += num;
            }
        })
        endTdIndex = startTdIndex + theadtdCosapn;

        if(isCurr){
            $(".marginbox .department-box table").find("td[field='"+field+"']").show();
            $(".marginbox .department-box table tr:gt("+tableClass.gtNum+")").each(function (index,item){
                $(item).find("td").each(function (ind,ite){
                    if((startTdIndex <= ind) && (ind<endTdIndex)){
                        $(ite).show();
                    }
                })
            })
        }else {
            $(".marginbox .department-box table").find("td[field='"+field+"']").hide();
            $(".marginbox .department-box table tr:gt("+tableClass.gtNum+")").each(function (index,item){
                $(item).find("td").each(function (ind,ite){
                    if((startTdIndex <= ind) && (ind<endTdIndex)){
                        $(ite).hide();
                    }
                })
            })
        }

    }
    //表格顶部搜索条件盒子设置最小宽度
    ,setBtnboxMin:function(){
        var tableWidth = $('.content-right-info').width();
        var originWidth = 100;
        $(".search-box > div:not('.clear')").each(function (index,item){
            var wid  = $(this).width()+10;
            if(wid > 10){
                originWidth+=wid;
            }
        })
        $(".btnbox").css("min-width",originWidth);
        if($('.content > div').eq(0).hasClass('second-title-box')){
            var processWidth = 100;
            $(".second-title-box .process-area").children('div,span,a').each(function (index,item){
                var wid  = $(this).outerWidth();
                if(wid > 10){
                    processWidth+=wid;
                }
            })
            var finalWidth = 0;
            if(processWidth > tableWidth && processWidth > originWidth){
                finalWidth = processWidth;
            }else if(processWidth < tableWidth && originWidth < tableWidth){
                finalWidth = tableWidth;
            }else{
                finalWidth = originWidth;
            }
            $(".second-title-box").css("min-width",finalWidth);
        }
    }
    //tab切换重置表格高度
    ,resetTableH:function(num){
        var hei = $(".content-right-info").height()-2;
        if($('#main-content .pages').children().length > 0){
            hei -= 70;
        }
        $(".department-box").css({"height":(hei+num)+'px'});
    }
    //列操作重置表格宽度
    ,resetHeight:function(windowWidth,tdW,addF){
        var hei = $(".content-right-info").height()-2;
        if($('#main-content .pages').children().length > 0){
            hei -= 70;
        }
        if(addF != null){
            if(addF){
                windowWidth += tdW;
            }else{
                windowWidth -= tdW;
            }
        }
        var mainContentWidth = $("#main-content").width()-20;
        if(tableClass.windowType=="small"){
            if((windowWidth+13) >= mainContentWidth){
                $(".department-box").css({"height":hei+'px','min-width':(windowWidth+13)+'px'});
            }
            if($(".content-right-info")){
                $(".content-right-info").css({'width':(windowWidth+2) +'px'});
            }
            $(".department-box").css({"height":hei+'px','width':(windowWidth+1) +'px'});
        }else{
            $(".department-box").css({"height":hei+'px','width':(windowWidth+1) +'px'});
            $(".department-box table").css({'width':windowWidth +'px'});
            //var btnBoxWidth = $(".btn-box").width()-20;
            /*if((windowWidth+13) >= mainContentWidth){
                $(".content").css({'width':(windowWidth+13) +'px'});//中看板是.content宽度不够
            }*/
            if($(".content-right-info")){
                $(".content-right-info").css({'width':(windowWidth+2) +'px'});
            }
        }



        $(".department-box,.content").niceScroll({
            cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
            cursorwidth: "8px", // 滚动条的宽度，单位：便素
            cursorborder: "0px solid #e0e0e0", // CSS方式定义滚动条边框
            cursorborderradius: "3px", // 滚动条圆角（像素）
            horizrailenabled: true,
            autohidemode: 'false', // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
            background: "#e0e0e0", // 轨道的背景颜色
        });
        tableClass.setBtnboxMin();
        tableClass.copyTableHead();
        $('.content').getNiceScroll().resize();
        $('.department-box').getNiceScroll().resize();
    }
    //列小于3列分页只显示当前页和上下页
    ,colHide:function(that){
        $("#fieldListConfig li").each(function (index,item){
            var field = $(this).find('input').val();
            if($(this).find('input').attr("checked") == undefined){
                tableClass.fieldJson[field] = false;
            }else{
                tableClass.fieldJson[field] = true;
            }
        })
        /*根据页面大小判断显示列*/
        var checkedNum=0;
        $.each(tableClass.fieldJson,function(indx,item){
            if(item){
                checkedNum++;
            }
        })
        if(checkedNum >= tableClass.windowLen){
            $("#fieldListConfig li").each(function (index,item){
                if($(this).find('input').attr("checked") == undefined){
                    $(this).find('input').attr("disabled","disabled");
                }
            })
        }else{
            $("#fieldListConfig li").each(function (index,item){
                if($(this).find('input').attr("disabled")=="disabled"){
                    $(this).find('input').removeAttr("disabled");
                }

            })
        }
        /*根据页面大小判断显示列*/

        /*小于等于3列只显示当前页和上一页下一页*/
        if(checkedNum <= 3){
            //分页隐藏
            var pageCount = $('.pages .splitpage> a').length;
            $('.pages .splitpage> a').each(function(index){
                if(index == 1 || index == (pageCount-3)){
                    $(this).show();
                }else{
                    $(this).hide();
                    if($(this).hasClass('curr')){
                        $(this).show();
                    }
                }
            })
            //$('.pages > span,.pages > input').hide();
            $('.pages .splitpage > span,.pages .splitpage > input,.pages .splitpage > .choose').hide();
        }else{
            $('.pages .splitpage> a').each(function(index){
                $(this).show();
            })
            //$('.pages > span,.pages > input').show();
            $('.pages .splitpage > span,.pages .splitpage > input,.pages .splitpage > .choose').show();
        }
        /*小于等于3列只显示当前页和上一页下一页*/

        /*中看板和大看板需要列不能少于3列*/
        if(tableClass.windowType=="center" || tableClass.windowType=="big"){
            //判断至少选中三个
            if(checkedNum < 3){
                $(that).attr('checked','checked');
                return false;
            }
        }
        return true;
        /*中看板和大看板需要列不能少于3列*/
    }
    /*表格title重写
     *width盒子宽度
     *num规定显示的字数
     * */
    ,tdForTitle:function(num,width){
        var titleW = "<div class='title-wang' style='display: none;position: fixed;border:1px solid #ccc;width: 100px;overflow: hidden;white-space:normal;word-wrap: break-word;background: #ffffff;padding:0 4px 0 4px;text-align: center;line-height: 20px;'></div>";
        $('body').append(titleW);
        $('.grade').find('td').removeAttr('title');
        $('.grade').find('td').hover(function(event) {
            var wahtml=$(this).text().trim();
            var left = event.clientX + 10;
            var top = event.clientY + 10;
            var wHeight = $(window).height();
            var wWidth = $(window).width();
            $('.title-wang').text(wahtml).show();

            if(wHeight < (top + width)){
                $('.title-wang').css({
                    "bottom": (wHeight-top) +'px',
                    'top':'auto'
                })
            }else{
                $('.title-wang').css({
                    "top": top,
                    'bottom':'auto'
                })
            }
            if(wWidth < (left + width)){
                $('.title-wang').css({
                    "right": (wWidth-left) +'px',
                    'left':'auto'
                })
            }else{
                $('.title-wang').css({
                    "left": left,
                    'right':'auto'
                })
            }

            if(wahtml.length > num){
                $('.title-wang').css({
                    "width": width + "px",
                    "text-align": "left"
                })
            }else {
                $('.title-wang').css({
                    "width": "auto",
                    "text-align": "center"
                })
            }

        }, function(event) {
            $('.title-wang').hide()
        })
    }
    //计算表格宽度方法
    ,calcTableW:function(){
        var tw = 0;
        $('.department-box table tr:eq(0) td').each(function(){
            if(!$(this).is(':hidden')){
                tw += $(this).outerWidth();
            }
        })
        return tw;
    }
    //根据配置项显示表格
    ,getCol:function(){
        //获取列
        /*$.ajax({
            url:'http://'+hostUrl+ tableClass.settings.getConfigUrl,
            data:{
                funUrl:url+"?"+ tableClass.getQueryString(tableClass.settings.configData.boardType) +"&"+ tableClass.getQueryString(tableClass.settings.configData.type),
            },
            success:function(result){*/
        var checkedNum=0,checkedField=[];
        var isCurr = true;
        /*if(result.state == '1'){
            //初始化配置表头
            result.data = JSON.parse(result.data);
             var html = '<ul class="config-menu" id="fieldListConfig">';
            $(".department-box tr").eq(0).find("td").each(function (index,item){
                if($(item).attr("field") && $(item).attr("title")){
                    var title = $(item).attr("title");
                    var field = $(item).attr("field");
                    var ind = $(".department-box tr").eq(0).find("td[field='"+field+"']").index();
                    //if(result.data[field]){
                    if(checkedNum < tableClass.windowLen){
                        if(result.data[field]){
                            html += '<li>';
                             html += '	<input type="checkbox" name="field" value="'+field+'" checked="checked"/>';
                             html += '	<span>'+title+'</span>';
                             html += '</li>';
                             checkedNum++;
                             checkedField.push(field);
                             $(".department-box tr").each(function (){
                                $(this).find("td").eq(ind).show();
                            })
                        }else{
                            html += '<li>';
                             html += '	<input type="checkbox" name="field" value="'+field+'" disabled="disabled"/>';
                             html += '	<span>'+title+'</span>';
                             html += '</li>';

                            $(".department-box tr").each(function (index){
                                if(index != 1){
                                    $(this).find("td").eq(ind).hide();
                                }
                            })
                        }
                    }else{
                        html += '<li>';
                        html += '	<input type="checkbox" name="field" value="'+field+'"  disabled="disabled"/>';
                        html += '	<span>'+title+'</span>';
                         html += '</li>';
                         $(".department-box tr").each(function (index){
                            if(index != 1){
                                $(this).find("td").eq(ind).hide();
                            }
                        })
                    }
                }
            })
            html += '</ul>';
            $(tableClass.settings.contentPosi).before(html);
            if(checkedNum < tableClass.windowLen){
                $('#fieldListConfig').find('input').each(function(){
                    if($(this).attr('disabled') == 'disabled'){
                        $(this).removeAttr('disabled');
                    }
                })
            }
        }else{*/
        //初始化配置表头
        var html = '<ul class="config-menu" id="fieldListConfig">';

        $(".department-box tr").eq(0).find("td").each(function (index,item){
            if($(item).attr("field") && $(item).attr("name")){
                var title = $(item).attr("name");
                var field = $(item).attr("field");
                var ind = $(".department-box tr").eq(0).find("td[field='"+field+"']").index();
                if(checkedNum < tableClass.windowLen){
                    html += '<li>';
                    html += '	<input type="checkbox" name="field" value="'+field+'" checked="checked"/>';
                    html += '	<span>'+title+'</span>';
                    html += '</li>';
                    checkedNum++;
                    checkedField.push(field);

                    $(".department-box tr").each(function (){
                        $(this).find("td").eq(ind).show();
                    })
                }else{
                    html += '<li>';
                    html += '	<input type="checkbox" name="field" value="'+field+'" disabled="disabled"/>';
                    html += '	<span>'+title+'</span>';
                    html += '</li>';
                    $(".department-box tr").each(function (index){
                        if(index != 1){
                            $(this).find("td").eq(ind).hide();
                        }
                    })
                }
            }
        })
        html += '</ul>';
        $(tableClass.settings.contentPosi).prepend(html);
        /*}*/

        $('.pages').show();
        topWin.JLayer.closeLoading();

        $("#fieldListConfig input").each(function (){
            var field = $(this).val(),isCurr = true;
            if($(this).attr("checked") == undefined){
                isCurr = false;
            }else {
                isCurr = true;
            }
            tableClass.resetTable(field,isCurr);
        })

        //美化滚动条
        $("#fieldListConfig").niceScroll({
            cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
            cursorwidth: "8px", // 滚动条的宽度，单位：便素
            cursorborder: "0px solid #e0e0e0", // CSS方式定义滚动条边框
            cursorborderradius: "3px", // 滚动条圆角（像素）
            autohidemode: 'leave', // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
            background: "#e0e0e0", // 轨道的背景颜色
        });

        $.each(checkedField,function(index,field){
            var colspan= $('.department-box table tr:eq(0)').find("td[field='"+field+"']").attr('colspan');
            if(colspan){
                checkedNum += parseInt(colspan);
                checkedNum -= 1;
                $('.department-box table tr:eq(1)').find("td[field='"+field+"']").css('width','50px');
            }
        })

        if(tableClass.windowType=="small"){
            $('table.grade tr:eq(0) td').each(function(){
                if(typeof($(this).attr('colspan'))!="undefined" && parseInt($(this).attr('colspan')) > 1){
                    var w = (50 + 22) * parseInt($(this).attr('colspan'));
                    $(this).css('width',w+'px');
                }else{
                    if(!$(this).hasClass('operation') && !$(this).hasClass('thorder') && !$(this).hasClass('thcheck')){
                        $(this).css('width','50px');
                    }
                    if($(this).hasClass('thorder')){
                        $(this).css('width','40px');
                    }
                    if($(this).hasClass('thcheck')){
                        $(this).css('width','60px');
                    }
                }
            })
            $('#searchForm .rows').css({'marginLeft':'0px'});
            $('#searchForm .input input').css({'width':'58px'});
            $('#searchForm .btn').css({'width':'40px'});
            //				$('.content').css({'min-width':'300px'});
            tableClass.windowWidth = checkedNum*(50 + 22);
        }
        if(tableClass.windowType=="center"){
            $('table.grade tr:eq(0) td').each(function(){
                if(typeof($(this).attr('colspan'))!="undefined" && parseInt($(this).attr('colspan')) > 1){
                    var w = (100 + 22) * parseInt($(this).attr('colspan'));
                    $(this).css('width',w+'px');
                }else{
                    if(!$(this).hasClass('operation') && !$(this).hasClass('thorder') && !$(this).hasClass('thcheck')){
                        $(this).css('width','100px');
                    }
                    if($(this).hasClass('thorder') || $(this).hasClass('thcheck')){
                        $(this).css('width','60px');
                    }
                }
            })
            $('#searchForm .rows').css({'marginLeft':'10px'});
            $('#searchForm .input input').css({'width':'150px'});
            $('#searchForm .btn').css({'width':'75px'});
            //				$('.content').css({'min-width':'570px'});
            tableClass.windowWidth = checkedNum*(100 + 22);
        }
        if(tableClass.windowType=="big"){
            $('table.grade tr:eq(0) td').each(function(){
                if(typeof($(this).attr('colspan'))!="undefined" && parseInt($(this).attr('colspan')) > 1){
                    var w = (150 + 22) * parseInt($(this).attr('colspan'));
                    $(this).css('width',w+'px');
                }else{
                    if(!$(this).hasClass('operation') && !$(this).hasClass('thorder') && !$(this).hasClass('thcheck')){
                        $(this).css('width','150px');
                    }
                    if($(this).hasClass('thorder') || $(this).hasClass('thcheck')){
                        $(this).css('width','60px');
                    }
                }
            })
            $('#searchForm .rows').css({'marginLeft':'10px'});
            $('#searchForm .input input').css({'width':'150px'});
            $('#searchForm .btn').css({'width':'75px'});
            //				$('.content').css({'min-width':'1280px'});
            tableClass.windowWidth = checkedNum*(150 + 22);
        }


        //表格有操作按钮
        if($('td.operation').length > 0){
            tableClass.windowWidth += 172;
        }
        if(tableClass.windowType=="small"){
            //表格有序号
            if($('td.thorder').length > 0){
                tableClass.windowWidth += 40;
            }
            ///表格有复选
            if($('td.thcheck').length > 0){
                tableClass.windowWidth += 60;
            }
        }else{
            //表格有序号
            if($('td.thorder').length > 0){
                tableClass.windowWidth += 60;
            }
            ///表格有复选
            if($('td.thcheck').length > 0){
                tableClass.windowWidth += 60;
            }
        }
        tableClass.colHide();
        if($("table").length > 0){
            tableClass.resetHeight(tableClass.windowWidth,0,false);
        }

        /*	}
        })*/

        setTimeout(function(){
            $(window).trigger("resize");
        },100)
    }
    //页面初始化注册事件
    ,initPage:function(){
        //美化滚动条
        $('.shrink-box').niceScroll({
            cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
            cursorwidth: "6px", // 滚动条的宽度，单位：便素
            cursorborder: "0px solid #fff", // CSS方式定义滚动条边框
            cursorborderradius: "5px", // 滚动条圆角（像素）
            autohidemode: 'scroll', // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
            background: "#ddd", // 轨道的背景颜色
        });
        //表格顶部菜单收起
        $(".pack-up-img").click(function (){
            $(this).parents(".shrink-btn").removeClass("curr");
            $(this).parents(".shrink-btn").prev("ul").find("li.switch").removeClass("curr");
            $(this).parents(".switch_title").nextAll(".shrink-box").hide();
            $(this).parents(".btnbox").next(".content-right-info").css("top",'40px');
            $(this).hide().siblings(".unfold-img").show();

            tableClass.resetTableH(20);
        })
        //表格顶部菜单展开
        $(".unfold-img").click(function (){
            $(this).parents(".shrink-btn").addClass("curr");
            $(this).parents(".shrink-btn").prev("ul").find("li.switch").eq(0).addClass("curr");
            $(this).parents(".switch_title").next(".shrink-box").show();
            var switchHeight = $(this).parents(".switch_title").next(".shrink-box").height();

            $(this).parents(".btnbox").next(".content-right-info").css("top",(40+switchHeight)+'px');
            $(this).hide().siblings(".pack-up-img").show();

            tableClass.resetTableH(-10);
        })
        //表格顶部菜单切换
        $(".switch_title ul li").click(function (){
            //切换
            var ind = $(this).index();
            var currBox = $(this).parents(".switch_title").nextAll(".shrink-box").eq(ind);
            if($(this).hasClass('curr')){
                currBox.hide();
                $(this).parents(".btnbox").next(".content-right-info").css("top",'50px');
                $(".pack-up-img").hide().siblings(".unfold-img").show();
                $(this).removeClass('curr');
                return false;
            }
            $(this).addClass("curr").siblings().removeClass("curr");
            currBox.show().siblings(".shrink-box").hide();
            //宽度计算
            var shrinkW = $('#main-content .switch_title').width();
            var chooseFlowW = 0;
            if(ind == 0){//筛选
                currBox.find('.search-box > div:not(".clear")').each(function(){
                    chooseFlowW += $(this).outerWidth();
                })
                currBox.find('.search-box').css({'width':(chooseFlowW+50) + 'px'});
            }
            if(ind == 1 || ind == 2){//配置
                currBox.find('.flow-box > div:not(".clear")').each(function(){
                    chooseFlowW += $(this).outerWidth();
                })
                currBox.find('.flow-box').css({'width':chooseFlowW + 'px'});
            }
            //设置表格的top
            var switchHeight = $(this).parents(".switch_title").nextAll(".shrink-box").eq(ind).height();
            $(this).parents(".btnbox").next(".content-right-info").css("top",(40+switchHeight)+'px');

            //改变箭头状态
            $(".unfold-img").hide().siblings(".pack-up-img").show();
            tableClass.resetTableH();
            //美化滚动条
            $(".work-content").niceScroll({
                cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
                cursorwidth: "10px", // 滚动条的宽度，单位：便素
                cursorborder: "0px solid #ccc", // CSS方式定义滚动条边框
                cursorborderradius: "5px", // 滚动条圆角（像素）
                autohidemode: true, // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
                background: "#ddd", // 轨道的背景颜色
            });
        })
        //时间选择块显示
        $(document).on('click','.timeout',function(){
            if ($(".timing-actuarial").is(":hidden")) {
                tableClass.offsetFix(this,".timing-actuarial");
                $(".timing-actuarial").css("left","934px");
            } else{
                $(".timing-actuarial").css("display","none")
            }
            return false;
        })
        //时间选择
        $(document).on('click','.timing-list a',function(){
            if (!$(this).hasClass("curr")) {
                $(this).addClass("curr");
                $(this).siblings("a").removeClass("curr")
            }
        })
        $(document).on('click','.switch',function(){
            if ($(this).hasClass("curr")) {
                $(".timing-actuarial").css("display","none")
            }
        })
        //表格右键菜单
        $(document).on("contextmenu",".department-box table td.center",function (event){
            var ind = $(this).index();
            var tdLeng = $("tr").eq(0).find("td").length;
            if(ind != (tdLeng-1)){
                currCol = $(this).index();
                currRow = $(this).parent().index();
                //相对td定位
                $(".right_click_window").hide();
                tableClass.offsetFix(this,"#table-menu");
            }
        })
        //固定列头右键菜单
        $(document).on("contextmenu","#copyTable td",function (event){
            currCol = $(this).index();
            currRow = $(this).parent().index();
            //相对td定位
            $(".right_click_window").hide();
            tableClass.offsetFix(this,"#table-col");
        })
        //取消右键菜单
        $('.cancel').click(function(){
            $(".right_click_window").hide();
        })
        //点击任意位置隐藏右键菜单
        var ope = $(".right_click_window");
        var ope1 = $(".timing-actuarial");
        $(document).click(function(event){
            if(!ope.is(event.target) && ope.has(event.target).length === 0){
                $(".right_click_window").hide();
            }
            if(!ope1.is(event.target) && ope1.has(event.target).length === 0){
                $(".timing-actuarial").hide();
            }
            $(".right_click_window_box").getNiceScroll().resize();
        });
        //添加行
        $('.work-content .block:eq(0)').click(function(){
            var ind = $(".department-box table tr").length;
            var trClone = $('.department-box tr:last-child').clone(true);
            var tdLast = $('.department-box tr:last-child').find("td:last").html();
            $(trClone).find("td:last").html(tdLast);
            $('.department-box tbody').append(trClone);
            $('.department-box tr:last-child td:eq(0)').html(ind);

            $(".export-hover").niceScroll({
                cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
                cursorwidth: "8px", // 滚动条的宽度，单位：便素
                cursorborder: "0px solid #fff", // CSS方式定义滚动条边框
                cursorborderradius: "4px", // 滚动条圆角（像素）
                autohidemode: 'false', // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
                background: "#ddd", // 轨道的背景颜色
            });
        })
        //添加行（行号+内容）
        $(document).on('click','.add-row',function(){
            var ind = $(".department-box table tr").length;
            var trClone = $('.department-box tr:last-child').clone(true);
            var tdLast = $('.department-box tr:last-child').find("td:last").html();
            $(trClone).find("td:last").html(tdLast);
            $('.department-box tbody').append(trClone);
            $('.department-box tr:last-child td:eq(0)').find("td[tdheight]").html(ind);
            tableClass.resetOrder();
            $(".export-hover").niceScroll({
                cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
                cursorwidth: "8px", // 滚动条的宽度，单位：便素
                cursorborder: "0px solid #fff", // CSS方式定义滚动条边框
                cursorborderradius: "4px", // 滚动条圆角（像素）
                autohidemode: 'false', // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
                background: "#ddd", // 轨道的背景颜色
            });
        })
        //删除行
        $('.del-row').click(function(){
            $('.department-box tbody tr:eq("'+(currRow+1)+'")').remove();
            tableClass.resetOrder();
            return false;
        })
        //导出
        $(document).on('mouseenter','table tr td .export-btn',function(e){
            var offsetL = $(window).width() - e.clientX;
            if(offsetL < 90){
                $(this).find('.export-hover').css('left','-120px');
            }else{
                $(this).find('.export-hover').css('left','-72px');
            }
            $(this).find('.export-hover').show();
        })
        $(document).on('mouseleave','table tr td.operation .export-btn',function(){
            $(this).find('.export-hover').hide();
        })
        //表格搜索条件收起
        $(".pack-up-img").click(function (){
            $(this).parents(".shrink-btn").prev(".show-hidden").hide();
            $(this).parents(".shrink-btn").siblings(".bgw").hide();
            if($('.content > div').eq(0).hasClass('second-title-box')){
                $(this).parents(".btnbox").next(".content-right-info").css("top","100px");
            }else{
                $(this).parents(".btnbox").next(".content-right-info").css("top","20px");
            }
            $(this).hide().siblings(".unfold-img").show();
            $('.department-box').getNiceScroll().resize();
        })
        //表格搜索条件展开
        $(".unfold-img").click(function (){
            $(this).parents(".shrink-btn").prev(".show-hidden").show();
            $(this).parents(".shrink-btn").siblings(".bgw").show();
            if($('.content > div').eq(0).hasClass('second-title-box')){
                $(this).parents(".btnbox").next(".content-right-info").css("top",'130px');
            }else{
                $(this).parents(".btnbox").next(".content-right-info").css("top",'50px');
            }
            $(this).hide().siblings(".pack-up-img").show();
            tableClass.setBtnboxMin();
            $('.department-box').getNiceScroll().resize();
        })
        //select选择重置搜索区域宽度
        $(document).on('change','select',function(){
            tableClass.setBtnboxMin();
        })
        //表头操作栏筛选
        $("table tr:first").find(".operation .set li").click(function (){
            var type = $(this).attr("type")
            if($(this).hasClass("curr")){
                $(this).removeClass("curr");
                $(this).parents("a.set").siblings("a."+type).hide();
            }else {
                $(this).addClass("curr");
                $(this).parents("a.set").siblings("a."+type).show();
            }
        })
        //表头以外表格操作
        $(document).on("click",".operation .set li",function (){
            if($(this).parents("tr").index() > 0){
                var type = $(this).attr("type")
                if($(this).hasClass("curr")){
                    $(this).removeClass("curr");
                    $("tr:gt(0)").find("td:last a").each(function (index,item){
                        if($(item).hasClass(type)){
                            $(item).hide();
                        }
                    })
                    $("tr:gt(0)").find("td:last a:last li").each(function (index,item){
                        if($(item).attr("type") == type){
                            $(item).removeClass("curr");
                        }
                    })
                }else {
                    $(this).addClass("curr");
                    $("tr:gt(0)").find("td:last a").each(function (index,item){
                        if($(item).hasClass(type)){
                            $(item).show();
                        }
                    })
                    $("tr:gt(0)").find("td:last a:last li").each(function (index,item){
                        if($(item).attr("type") == type){
                            $(item).addClass("curr");
                        }
                    })
                }
            }
        })
        //行编辑1
        $(document).on("click",".operation .edit",function (){
            var tdLeng = $("tr").eq(0).find("td").length;
            $(this).parents("tr").find("td").each(function (index,item){
                if(index == 0 || tdLeng == (tdLeng -1)){
                    return;
                }else {
                    $(item).find("div").attr("contenteditable",true);
                }
            })
        })
        //行编辑2
        $(document).on("click",".export-hover .transfor",function (){
            var tdLeng = $("tr").eq(0).find("td").length;
            $(this).parents("tr").find("td").each(function (index,item){
                if(index == 0 || tdLeng == (tdLeng -1)){
                    return;
                }else {
                    $(item).children("div").attr("contenteditable",true);
                    $(this).parents(".export-hover").hide();
                }
            })
        })
        //行删除1
        $(document).on("click",".operation .del",function (){
            var _this = $(this);
            topWin.JLayer.confirm("确认删除么？",function (){
                _tableClass.parents("tr").remove();
                tableClass.resetOrder();
            })
        })
        //行删除2
        $(document).on("click",".export-hover .delete",function (){
            var _this = $(this);
            topWin.JLayer.confirm("确认删除么？",function (){
                _tableClass.parents("tr").remove();
                tableClass.resetOrder();
            })
        })
        //td编辑
        $(document).on("click",".editTd",function (){
            $("table").find("tr").eq(currRow).find("td").eq(currCol).find("div").attr("contenteditable",true);
            $(this).parents(".right_click_window").hide();
        })
        //td删除
        $(document).on("click",".delTd",function (){
            var _this = $(this);
            topWin.JLayer.confirm("确认删除么？",function (){
                $("table").find("tr").eq(currRow).find("td").eq(currCol).find("div").eq(0).empty();
                _tableClass.parents(".right_click_window").hide();
            })
        })
        //美化滚动条
        $(".export-hover,.right_click_window_box").niceScroll({
            cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
            cursorwidth: "8px", // 滚动条的宽度，单位：便素
            cursorborder: "0px solid #fff", // CSS方式定义滚动条边框
            cursorborderradius: "4px", // 滚动条圆角（像素）
            autohidemode: 'false', // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
            background: "#ddd", // 轨道的背景颜色
        });
        //操作一列，不包括表头
        $(document).on("mouseenter","td.center a",function (){
            $(this).find(".export-box").show();
            $(".export-hover").getNiceScroll().resize();
        })
        $(document).on("mouseleave","td.center a",function (){
            $(this).find(".export-box").hide();
            $(".export-hover").getNiceScroll().resize();
        })
        //表头操作
        $(document).on("mouseenter","td.operation a",function (){
            $(this).find(".export-box").show();
            $(".export-hover").getNiceScroll().resize();
        })
        $(document).on("mouseleave","td.operation a",function (){
            $(this).find(".export-box").hide();
            $(".export-hover").getNiceScroll().resize();
        })
    }
    //表格配置初始化
    ,initConfig:function(options){
        //动态添加配置样式表
        var stylestr = '<style>'+
            '.config-menu{display:none;position:absolute; top:40px; width:150px; max-height:160px; overflow-y:auto; z-index:10; right:87px; background-color:#fff;border:1px solid #ccc; border-radius:5px;}'+
            '.config-menu li{display: block;cursor:pointer;height:16px; line-height:16px; padding:5px 10px;overflow: hidden;}'+
            '.config-menu li input{margin-right:5px;cursor:pointer;-webkit-appearance: checkbox;}'+
            '</style>';
        $("head").append(stylestr);

        tableClass.gtNum = 0;
        if($(".department-box table").find("tr").eq(0).find('td[colspan]').length > 0){
            tableClass.gtNum = 1;
        }
        tableClass.settings = $.extend(true, tableClass.settings, options);
        var host = window.location.hostname;
        var port = window.location.port;
        var hostUrl = host + (port == ""?"":":"+port);
        //var hostUrl= '10.0.3.119';
        var url=location.href.indexOf('?')>-1?location.href.split('?')[0]:location.href;
        //看板类型判断
        tableClass.windowType = tableClass.getQueryString("type");
        if(tableClass.windowType==null || tableClass.windowType=="undefined"){
            tableClass.windowType = "big";
        }

        if(tableClass.windowType=="small"){
            tableClass.windowLen = 3;
        }
        if(tableClass.windowType=="center"){
            tableClass.windowLen = 8;
        }
        if(tableClass.windowType=="big" || tableClass.windowType=="undefined"){
            tableClass.windowLen = 10000;
        }

        //配置按钮切换
        $(document).on('click',tableClass.settings.btnId,function(){
            if($('.content > div').eq(0).hasClass('second-title-box')){
                $('#fieldListConfig').css({'right':'10px','top':'130px'});
            }else{
                $('#fieldListConfig').css({'right':'10px','top':'50px'});
            }
            $('#fieldListConfig').toggle();
            $("#fieldListConfig").getNiceScroll().resize();
        })
        //点击任意位置隐藏配置项
        var ope = $(tableClass.settings.btnId);
        //var ope = $('#create');
        $(document).click(function(event){
            var canHide = true;
            if(!ope.is(event.target) && ope.has(event.target).length === 0){

                $("#fieldListConfig li").each(function (index,item){
                    if($(item).is(event.target) || $(item).has(event.target).length > 0){
                        canHide = false;
                        return false;
                    }
                })
                if(canHide){
                    $('#fieldListConfig').hide();    //淡出消失
                }

            }
        });
        //列操作设置
        $(document).on("change","#fieldListConfig li input",function(){
            if(!tableClass.colHide(this)){
                return false;
            }
            var addF = false;
            var field = $(this).val();
            var tdCurr = $(".department-box tr").eq(0).find("td[field='"+field+"']");
            var tdW	= tdCurr.outerWidth();

            tableClass.windowWidth = tableClass.calcTableW();
            if($(this).attr("checked") == undefined){
                addF = false;
            }else{
                addF = true;
            }

            $("#fieldListConfig input").each(function (){
                var field = $(this).val(),isCurr = true;
                if($(this).attr("checked") == undefined){
                    isCurr = false;
                }else {
                    isCurr = true;
                }
                tableClass.resetTable(field,isCurr);
            })


            //提交列选择
            /*$.ajax({
                url:'http://'+hostUrl+ tableClass.settings.setConfigUrl,
                type:'POST',
                data:{
                    funUrl:url+"?"+ tableClass.getQueryString(tableClass.settings.configData.boardType) +"&"+ tableClass.getQueryString(tableClass.settings.configData.type),
                    configInfo:JSON.stringify(tableClass.fieldJson)
                },
                success:function(result){*/
            tableClass.resetHeight(tableClass.windowWidth,tdW,addF);
            /*	}
            })*/
        })
        //获取列
        tableClass.getCol();
        //页面缩放
        $(window).resize(function () {
            var contentWin = $('#cme-main').width();
            $('#main-content').css('width', ($('#main-content').width() + 1) + 'px');
            /*tableClass.windowWidth = $("#main-content table").width();
            tableClass.resetHeight(tableClass.windowWidth,0,null);*/
            $(".marginbox").getNiceScroll().resize();
            $('#main-content').getNiceScroll().resize();
            $('#main-content').css('width', contentWin + 'px');
            tableClass.copyTableHead();
            tableClass.setFormTermWidth();
        })
        tableClass.copyTableHead();
        tableClass.setFormTermWidth();
    }
    //工作台小看板时添加宽度
    ,setFormTermWidth: function (){
        var widthList = [];
//		if($(".marginbox .tips-word").length > 0){
//			var tipsWidth = $(".marginbox .tips-word").width();
//			widthList.push(tipsWidth);
//		}

        if($(".marginbox .form-term").length > 0){
            $(".marginbox .form-term").each(function (index,item){
                var formTermWidth = 0;
                if($(item).find(".lable").length > 0){
                    formTermWidth += $(item).find(".lable").width();
                }
                if($(item).find("input").length > 0){
                    formTermWidth += $(item).find("input").width()+90;
                }
                if($(item).find("textarea").length > 0){
                    formTermWidth += $(item).find("textarea").width()+90;
                }
                widthList.push(formTermWidth);
            })
        }

        if($(".marginbox .form-btn").length > 0){
            var btnWidth = $(".marginbox .form-btn").width();
            widthList.push(btnWidth);
        }

        console.log(widthList);
        if(widthList.length > 0){
            var max = Math.max.apply(null, widthList);
            console.log(max);
            if(max < 570){
                max = 570;
            }
//			$(".marginbox .tips-word").css("width",max+"px");
            $(".marginbox .form-term").css("width",max+"px");
            $(".marginbox .form-btn").css("width",max+"px");
        }

        $(".content-right-info").niceScroll({
            cursorcolor: "#bbb", // 改变滚动条颜色，使用16进制颜色值
            cursorwidth: "8px", // 滚动条的宽度，单位：便素
            cursorborder: "0px solid #e0e0e0", // CSS方式定义滚动条边框
            cursorborderradius: "3px", // 滚动条圆角（像素）
            horizrailenabled: true,
            autohidemode: 'false', // 隐藏滚动条的方式, 可用的值: true | // 无滚动时隐藏 "cursor" | // 隐藏 false | // 不隐藏, "leave" | // 仅在指针离开内容时隐藏"hidden" | // 一直隐藏  "scroll", // 仅在滚动时显示
            background: "#e0e0e0", // 轨道的背景颜色
        });

    }
}

/*初始化表格页面，页面加载完成执行
 * 如果表格是异步加载的，或者搜索之后显示的
 * 需要在ajax调用成功执行tableClass.initConfig({
	    btnId: "#create",
	    contentPosi: "#main-content",
	    setConfigUrl:'',
	    configData:{}
	});
 * */
$(function(){
    tableClass.initPage();
    tableClass.initConfig({
        btnId: "#create",        //筛选按钮默认id
        contentPosi: "#main-content",   //配置列表相对添加的盒子id
        setConfigUrl:'',
        configData:{}
    });
})
