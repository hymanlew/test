<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/23/023
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<<<<<<< HEAD
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
=======
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
>>>>>>> a577d1ec65e51a687a6470d87c9351cccf139485
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

</script>
</html>
