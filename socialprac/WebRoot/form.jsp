<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- HTML5文件 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<title>发布 - USTB社会实践</title>
		<link rel="stylesheet" href="${ctx }/assets/agile/css/agile.layout.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.component.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.color.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconline.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconform.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/animate.css">
		<link rel="stylesheet" href="${ctx }/assets/component/timepicker/timepicker.css">
		<link rel="stylesheet" href="${ctx }/assets/app/css/app.css">
		<link rel="stylesheet" href="${ctx }/css/myStyle.css">
		<link rel="stylesheet" href="${ctx }/css/myUpload.css">
		
	</head>
	<body>
		<div id="section_container">
			<section id="list_section" data-role="section" class="active">
				<header>
				    <div class="titlebar">
				    	<a data-toggle="back" href="${ctx}/status/list "><i class="iconfont iconline-arrow-left"></i></a>
				    	<h1>发布动态</h1>
				    	<button id="fbbutton" class="disable">发布</button>
				    </div>
				    
				</header>
				<article data-role="article" id="main_article" data-scroll="verticle" class="active" style="top:44px;bottom:0px;">
					<div class="scroller"> 
						<form id="formInfo" class="" action="">
							<textarea class="idea" name="textContent" placeholder="分享你们的新动态..."></textarea>
							<input type="hidden" name="photoPath" id="photoPath">
							
							
							 <div class="container">
						        <!--    照片添加    -->
						        <div class="z_photo">
						            <div class="z_file">
						              	<!-- <input type="file" name="file" id="file6"> -->
						                <input type="file" name="file" id="file" value="" accept="image/jpg,image/jpeg,image/png"  multiple onchange="imgChange('z_photo','z_file');" />
						
						            </div>
						
						        </div>
						
						        <!--遮罩层-->
						        <div class="z_mask">
						            <!--弹出框-->
						            <div class="z_alert">
						                <p>确定要删除这张图片吗？</p>
						                <p>
						                    <span class="z_cancel">取消</span>
						                    <span class="z_sure">确定</span>
						                </p>
						            </div>
						        </div>
						    </div>
							
							
						</form>
					</div>  
				</article>
			</section>
		</div>
		
		<!--- third --->
		<script src="${ctx }/assets/third/jquery/jquery-2.1.3.min.js"></script>
		<<script src="${ctx }/assets/third/jquery/jquery.mobile.custom.min.js"></script>
		<script src="${ctx }/assets/third/iscroll/iscroll-probe.js"></script>
		<script src="${ctx }/assets/third/arttemplate/template-native.js"></script>
		<!-- agile -->
		<script type="text/javascript" src="${ctx }/assets/agile/js/agile.js"></script>		
		<!--- bridge --->
		<script type="text/javascript" src="${ctx }/assets/bridge/exmobi.js"></script>
		<script type="text/javascript" src="${ctx }/assets/bridge/agile.exmobi.js"></script>
		<!-- 文件上传 -->
		<script type="text/javascript" src="${ctx }/js/ajaxfileupload.js"></script>
		<%-- <!-- app -->
		<script type="text/javascript" src="${ctx }/assets/app/js/app.js"></script>  --%>
		<script>
			$(function(){
				var textarea_height=$(".idea").offset().top;
				var screen_height=$("body").height();
				var fact_height=screen_height-textarea_height-300;
				$(".idea").css("height",fact_height+"px");
				$(".idea").focus();
				$('.idea').bind('input propertychange',function(){
					$('#fbbutton').removeClass("disable").css("background","#007AFF");
				});
				$('#fbbutton').click(function(){
					var idea=$(".idea").val();
					if(idea==''){
						A.alert('警告','发布内容不能为空！');
						return false;
					}else{
						$.ajaxFileUpload({
							url : "status/upload?PHPSESSID=1231",
							secureuri : false, 
							fileElementId : "file",
							dataType : "json",
							success : function(data, status) {
								var obj = eval('(' + data + ')');
								if (obj.success == 'yes') {
									///location.reload();
									//$("#files").val(obj.name);
									var picPath=obj.picPath;
									var pic=picPath.substring(picPath.indexOf(",")+1);
									$("#photoPath").val(pic);
									save();
								} else {
									alert(obj.success);
								}
							},
							error : function(data, status, e) {
								alert(e);
							}
						});
					
					}
				});
			});
			function save(){
				$.ajax({
					type:"post",
					url:"status/save",
					data: $('#formInfo').serialize(),
					datatype:"json",
					success:function(data){
						if(data=="1")
							window.location.href = "status/list";
					}
				});
				
			}
			
		</script>
		 <script type="text/javascript">
        //px转换为rem
        (function(doc, win) {
            var docEl = doc.documentElement,
                resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                recalc = function() {
                    var clientWidth = docEl.clientWidth;
                    if (!clientWidth) return;
                    if (clientWidth >= 640) {
                        docEl.style.fontSize = '100px';
                    } else {
                        docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
                    }
                };

            if (!doc.addEventListener) return;
            win.addEventListener(resizeEvt, recalc, false);
            doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window);

        function imgChange(obj1, obj2) {
            //获取点击的文本框
            var file = document.getElementById("file");
            //存放图片的父级元素
            var imgContainer = document.getElementsByClassName(obj1)[0];
            //获取的图片文件
            var fileList = file.files;
            //文本框的父级元素
            var input = document.getElementsByClassName(obj2)[0];
            var imgArr = [];
            //遍历获取到得图片文件
            for (var i = 0; i < fileList.length; i++) {
                var imgUrl = window.URL.createObjectURL(file.files[i]);
                imgArr.push(imgUrl);
                var img = document.createElement("img");
                img.setAttribute("src", imgArr[i]);
                img.setAttribute("name", "file");
                var imgAdd = document.createElement("div");
                imgAdd.setAttribute("class", "z_addImg");
                imgAdd.appendChild(img);
                imgContainer.appendChild(imgAdd);
            };
            imgRemove();
        };

        function imgRemove() {
            var imgList = document.getElementsByClassName("z_addImg");
            var mask = document.getElementsByClassName("z_mask")[0];
            var cancel = document.getElementsByClassName("z_cancel")[0];
            var sure = document.getElementsByClassName("z_sure")[0];
            for (var j = 0; j < imgList.length; j++) {
                imgList[j].index = j;
                imgList[j].onclick = function() {
                    var t = this;
                    mask.style.display = "block";
                    cancel.onclick = function() {
                        mask.style.display = "none";
                    };
                    sure.onclick = function() {
                        mask.style.display = "none";
                        t.style.display = "none";
                        t.remove();
                    };

                }
            };
        };

    </script>
		
	</body>
</html>