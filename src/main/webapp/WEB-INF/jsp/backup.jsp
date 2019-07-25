<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/23/023
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- 工地登录时先选派所属街道 --%>
<div id="selectStreet" class="modal" style="background-color: rgba(0,0,0,0.7);padding-top:0;">
    <!-- 弹窗内容 -->
    <div class="modal-content" style="height: 220px; width:500px;border:none;top: 30%; margin-top: -75px">
        <div class="modal-header" style=" background:#4975c9;">
            <h1 style="color:#fff; border:none;font-size: 20px;">完善项目信息</h1>
        </div>
        <div class="modal-body" style="height:150px;">
            <div class="modal-style1" style="margin-top: 7%;">
                <p style="width: 80px;">所属街道</p>
                <select id="streetName" lay-filter="district" lay-verify="required">
                </select>
                <div style="text-align: center; margin: 7% 41%; width: 15%;background-color: #4975c9;">
                    <p onclick="submitStreet()" style="color:#fff; border:none;font-size: 16px;top:15px; padding: 4px 10px;">提交</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use('layer', function() {
        var $ = layui.jquery,
            layer = layui.layer;

        $('#video1').on('click', function() {
            layer.open({
                title: 'YouTube',
                maxmin: true,
                type: 2,
                content: 'video.html',
                area: ['800px', '500px']
            });
        });
    });
</script>
</body>
<script>

    function getStreetNames() {
        var parent = 1851;
        $.ajax({
            type:"post",
            async:false,
            url:"area/findAreaByParent",
            data:{
                "parent":parent
            },
            dataType:"json",
            error:function (jqXHR, textStatus, errorThrow) {
                console.log(textStatus);
                console.log(errorThrow);
            },
            success:function (data) {
                console.log(data);
                var code = "<option value=''>请选择（乡/镇/街道）</option>";
                for(var i=0;i<data.length;i++){
                    code += "<option value='"+data[i].id+"'>"+data[i].district+"</option>";
                }
                $("#streetName").html(code);
            }
        });
    }

    function submitStreet(){
        var id = $("#siteId").val()
        var streetId = $("#streetName").val();
        if(streetId==null || streetId==""){
            layer.alert('请选择街道!');
            return;
        }
        $.post("site/updateSite",{"id":id,"streetId":streetId},function (data) {
            if(data=='success'){
                layer.alert('修改成功!');
                $("#selectStreet").hide();
                <%--setTimeout(function(){location.href="<%=basePath%>page/findWelcome";},3000);--%>
            }else {
                layer.alert('修改失败!');
            }
        });
    }


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

    // 表格右键菜单
    // <div class="right_click_window" id="table-menu">
    //     <ul class="right_click_window_box">
    //     <li class="editTd">
    //     <a href="javascript:;">编辑</a>
    //     </li>
    //     <li class="delTd">
    //     <a href="javascript:;">删除</a>
    //     </li>
    //     <li>
    //     <a href="javascript:;">下架</a>
    //     </li>
    //     </ul>
    // </div>


    // js 屏蔽浏览器右键菜单：
    // <body oncontextmenu="doNothing()">
    function doNothing(){
        window.event.returnValue=false;
        return false;
    }


    // 使用 Validform 进行表单元素验证（validform/v5.3.2/Validform.min.js）（dataType，nullmsg，errormsg）：
    // <input type="text" name="validator" value="0.1" dataType="/^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$|^(0\.d*[1-9])$/" nullmsg="不能为空" errormsg="请填写正确信息！(1.0 ~ 0.1)" placeholder="xxx">
    // <input placeholder="" dataType="uniquecode" />

    $("#submitForm").Validform({
        btnSubmit:"#addAppBut",
        tiptype: 4,
        ajaxPost:true,
        showAllError:true,
        datatype:{
            uniquecode:function(gets,obj,curform,regxp){
                result = false;
                if (null != gets && undefined != gets && ''!=gets) {

                }
                return result;
            }
        },
        beforeSubmit:function(curform){
            //在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
            //这里明确return false的话表单将不会提交;
            // return false;
        },
        beforeCheck:function(curform){
            //在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
            //这里明确return false的话将不会继续执行验证操作;
        },
        callback:function(data){
            if (data.state == 1) {

            } else {

            }
        }
    });

</script>
</html>
