<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/security/tags"
prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>项目申请</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagination.css" media="screen">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>jedate/skin/jedate.css">
	<link rel="stylesheet" href="<%=basePath%>css/index1.2.css" media="all">
	<link rel="stylesheet" href="<%=basePath%>manage/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=basePath%>css/heart.css" media="all">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=basePath%>js/date.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=basePath%>js/index1.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/menu.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ss/svg.js"></script>
	<script src="<%=basePath%>js/ajaxfileupload.js"></script>
	<style type="text/css">
		#allmap{width:100%;height:100%;}
		p{ font-size:14px;}
		section {width: 1200px;margin: 200px auto;}
		.icon {width: 1.2em;height: 1.2em;vertical-align: -0.15em;fill: currentColor;overflow: hidden;}
		.item {float: left;position: relative;}
		.addImg {width: 250px;height: 190px;}
		.upload_input {display: none;}
		.preview {position: absolute;width: 250px;height: 190px;left: 0;top: 0;}
		.click {position: absolute;width: 250px;height: 190px;left: 0;top: 0;cursor: pointer;border: 1px solid #e6e6e6; border-radius: 4px; text-align: center;line-height: 190px; color:#a1a1a1;}
		.delete {position: absolute;right: 54.5rem;top: -0.6rem;cursor: pointer;display: none;}
		.preview img {width: 100%;height: 100%;z-index: 1000; position: absolute;}
	</style>

</head>
<body>

<!-- 左侧栏通用开始 -->
<div class="index-left" id="index-left">
  <div class="toggle-btn" onclick="leftToggle()"> <span class="fa fa-reorder fa-rotate-90"></span> </div>
</div>
<div class="index-right">
	<div class="index-body" style="margin-top: 46px;padding-bottom: 10px; background:#fff;">

		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:160px;text-align:right;"><span style="color: red;margin-right:5px;">*</span>竣工验收证明书</label>
				<div class="item" style="width: 60%;">
					<svg class="icon addImg" aria-hidden="true" style="float: left;">
						<use></use>
					</svg>
					<input name="url" type="file" class="upload_input" onChange="preview(this,0)">
					<div class="preview" ></div>
					<div class="click" onClick="loadImg(this)" ><p>点击上传竣工验收证明书</p></div>
					<div class="delete" onClick="deleteImg(this,0)">
						<svg class="icon" aria-hidden="true">
							<use xlink:href="#icon-shanchu4"></use>
						</svg>
					</div>
                    <p  style=" margin: 170px 0 0 260px; color:red; z-index: -1;">格式仅限（jpg;png;bmp;），且大小小于1M</p>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label" style="width:160px;text-align:right;"><span style="color: red;margin-right:5px;">*</span>企业无拖欠工资承诺书</label>
				<div class="item" style="width: 60%;">
					<svg class="icon addImg" aria-hidden="true" style="float: left;">
						<use></use>
					</svg>
					<input name="url" type="file" class="upload_input" onChange="preview(this,1)">
					<div class="preview" ></div>
					<div class="click" onClick="loadImg(this)" ><p>点击上传承诺书</p></div>
					<div class="delete" onClick="deleteImg(this,1)">
						<svg class="icon" aria-hidden="true">
							<use xlink:href="#icon-shanchu4"></use>
						</svg>
					</div>
					<p  style=" margin: 170px 0 0 260px; color:red; z-index: -1;">格式仅限（jpg;png;bmp;），且大小小于1M</p>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label" style="width:160px;text-align:right;"><span style="color: red;margin-right:5px;">*</span>企业工资发放流水</label>

                <a class="layui-btn" style="color: #fff;background:#4975c9;" id="imgTitle">上传详情图</a><a class="layui-btn" style="background:#ccc;margin-right:18px;" id="clearImg">取消上传</a>
				<font color="red">格式仅限（jpg;png;bmp;），且大小小于1M（支持多图批量上传）</font>
                <input type="file" name="manyImages" id="manyImages" onchange="uploadImg(this);" style="display: none;"
                       accept="image/jpg,image/jpeg,image/png,image/bmp" multiple="multiple"/>

				<div class="cp-img" id="logoImgDiv6">
					<ul id="detailImgs" style="overflow: hidden;width: 50%; margin-top: 5px; margin-left: 190px;">
					</ul>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label" style="width:160px;text-align:right;"><span style="color: red;margin-right:5px;">*</span>申请说明</label>
				<div class="layui-input-inline">
                    <textarea class="layui-input" id="content" style="height: 180px; width: 650px;resize:none;text-indent:35px;border-radius: 6px;line-height:26px; padding: 5px 5px;"></textarea>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block" style="margin-left: 190px;">
					<a class="layui-btn" lay-submit="" id="submit" onclick="submit()" style="background:#4975c9;">立即提交</a>
					<a onClick="javascript :history.back(-1);"  class="layui-btn" style="background:#ccc;">返回</a>
				</div>
			</div>
		</form>

		<%--
		HTML页面中的表单最初所采用 application/x-www-form-urlencode 编码方式，并不满足文件上传的需要。所以RFC 1867在此基础
		之上增加了新的multipart/form-data编码方式以支持基于表单的文件上传。

		enctype=”multipart/form-data”，是设置表单的MIME编码。默认这个编码格式是 application/x-www-form-urlencoded，不能用
		于文件上传；只有使用了multipart/form-data，才能完整的传递文件数据。

		--%>
		<form id="upload" name="upload" action="<%=basePath%>employee/photoUpload_allow" enctype="multipart/form-data" method="post">
			<input type="file" name="file1">
			<input type="file" name="file2">
			<input type="submit" value="上传文件 " class="button">
		</form>
	</div>
</div>
<!-- 右侧body结束 --> 
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>manage/plugins/layui/layui.js"></script>
<script>
    //选择图片
    var loadImg = function(obj){
        $(obj).parent().find(".upload_input").click();
    }
    //删除
    var deleteImg = function(obj,num){
        $(obj).parent().find('input').val('');
        $(obj).parent().find('.preview').html('');
        $(obj).hide();
        if(num==0){
            $("#image").val("");
		}else{
            $("#promiseImage").val("");
		}
    }
    function preview(file,num) {
        var prevDiv = $(file).parent().find('.preview');

        if (file.files && file.files[0]) {
            var f = $(file).val();
            if(f==undefined || f=="" || f==null){
                layer.alert('请重新添加附件图片!');
                return false;
            }else if(!/\.(?:png|jpg|bmp)$/.test(f)) {
                layer.alert('格式不正确，请重新添加附件图片!');
                $(file).val('');
                return false;
            }
            var reader = new FileReader();
            reader.onload = function(evt) {
                prevDiv.html('<img src="' + evt.target.result + '" />');
                var imgstr = evt.target.result; //这就是base64字符串
                $.post("<%=basePath%>employee/uploadPic_allow",{"base64":imgstr},function (data) {
                    if(data.result=='success'){
                        if(num==0){
                            $("#image").val(data.image);
						}else{
                            $("#promiseImage").val(data.image);
						}
                    }else {
                        layer.alert('请重新添加附件图片!');
                    }
                })
            }
            reader.readAsDataURL(file.files[0]);
        } else {//IE
            prevDiv.html('<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>');
            var imgstr = evt.target.result; //这就是base64字符串
            alert(imgstr)
            $.post("<%=basePath%>employee/uploadPic_allow",{"base64":imgstr},function (data) {
                if(data.result=='success'){
                    $("#image").val(data.image);
                }else {
                    layer.alert('请重新添加附件图片!');
                }
            })
        }
        $(file).parent().find('.delete').show();
    }

    $("#imgTitle").on("click",function (eventObject) {
        $("#manyImages").trigger("click");
	});

    $("#clearImg").on("click",function (eventObject) {
        $("#imageslist").val("");
        $("#detailImgs").html("");
        imageList.splice(0,imageList.length);
	})

    var imageList = [];
    function uploadImg(image) {
		var file = $(image).val();
		if(file==undefined || file=="" || file==null){
            layer.alert('请重新添加附件图片!');
            return false;
		}else if(!/\.(?:png|jpg|bmp)$/.test(file)){
            layer.alert('格式不正确，请重新添加附件图片!');
            $(image).val('');
            return false;
		}else{
			$.ajaxFileUpload({
				url: "<%=basePath%>employee/uploadManyPic_allow",
				type: "post",
                secureuri: false,
				fileElementId: "manyImages",
				dataType: "json",
				contentType: false,
				processData: false,
				success: function (data) {
                    data = $.parseJSON(data.replace(/<.*?>/ig,""));
                    var Data=data.list;
					if(Data!=null){
					    for(var key in Data){
                            imageList.push(Data[key]);
						}
					    var html = "";
                        for(var i=0;i<imageList.length;i++){
                            html += '<li style="float:left; margin:0 5px 5px 0;"><img src="<%=basePath%>'+imageList[i]+'" style="width: 250px;height: 190px" onclick="showImages(this)"></li>';
						}
						$("#detailImgs").html(html);
                        $("#imageslist").val("img");
					}else{
                        layer.alert('上传失败!');
                        $(image).val('');
					}
                },
				error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.alert('上传失败，请检查网络后重试!');
                    $(image).val('');
                }
			});
		}
    }

</script>
<script>
    var form;
    layui.use(['form', 'layedit', 'laydate'], function() {
        form = layui.form(),
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;
    });
    $(function () {
        $(".fa-power-off").click(function () {
            if (confirm("确定退出登录？")) {
                //点击确定后操作
                // console.log(getBasePath())
                window.location.href = getBasePath() + "j_spring_security_logout";
            }
        });
    })

    function showImages(url){
        var imag = $(url).attr("src");
        window.open(imag);
    }

    function submit() {
        //var base64=$("#base64").val();
        var siteId = ${site.id};
        var apply = $("#apply").val();
        var phone = $("#phone").val();
        var content =  $("#content").val();
        //alert(base64)
		var image=$("#image").val();
        var promiseImage=$("#promiseImage").val();
		var images = $("#imageslist").val();

        if (apply==""&&apply!=null){
           layer.alert("请输入申请人");
           return;
        }else if (phone==""&&phone!=null){
           layer.alert("请输入申请人电话");
            return;
        }else if (phone!=""&&phone!=null) {
            var re = /0?(13|14|15|17|18|19)[0-9]{9}/;
            if (!re.test(phone)) {
                layer.alert("手机号码格式不正确");
                return;
            }
            else if (image == "" && image != null) {
                console.log(phone);
                //alert(image)
                layer.alert("请上传竣工验收说明书");
                return;
            } else if (content == "" && content != null) {
                layer.alert("请输入申请说明");
                return;
            } else if (promiseImage == "" && promiseImage != null) {
                layer.alert("请上传企业无拖欠工资承诺书附件");
                return;
            } else if (images == "" && images != null) {
                layer.alert("请上传工资流水附件");
                return;
            } else {
                $.ajax({
                    type: "post",
                    url: "<%=basePath%>finish/add",
                    traditional: true,
                    data: {
                        "siteId": siteId,
                        "apply": apply,
                        "phone": phone,
                        "content": content,
                        "image": image,
                        "promiseImage": promiseImage,
                        "imageList": imageList
                    },
                    dataType: "text",
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log(textStatus);
                        console.log(errorThrown);
                        console.log(XMLHttpRequest);
                        layer.alert('申请失败!');
                    },
                    success: function (data) {
                        if (data == 'success') {
                            layer.alert('申请成功!');
                            $("#submit").hide();
                        } else {
                            layer.alert('申请失败!');
                        }
                    }
                });
            }
        }
    }
</script>
<script type="text/javascript">
    <!-- 右上角用户菜单自动弹出设置 -->
    var $animate = $('#animate');
    $(".index-header").mouseenter(function(){
        $animate.css("display","block").addClass('slideInDown animated infinite');
        setTimeout(removeClass, 500);
        $(".index-display > span").css("-webkit-transform","rotate(180deg)");
    })
    function removeClass(){
        $animate.removeClass("slideInDown");
    }
    $(".index-header").mouseleave(function(){
        $animate.css("display","none");
        $(".index-display > span").css("-webkit-transform","rotate(0deg)");
    })
</script>
<input type="hidden" id="constructionID" value="${site.constructionId}">
<input type="hidden" id="base64" value="">
<input type="hidden" id="image" value="">
<input type="hidden" id="promiseImage" value="">
<input type="hidden" id="imageslist" value="">
</body>
</html>