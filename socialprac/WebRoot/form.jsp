<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<title>发布动态</title>
		<link rel="stylesheet" href="assets/agile/css/agile.layout.css">
		<link rel="stylesheet" href="assets/agile/css/flat/flat.component.css">
		<link rel="stylesheet" href="assets/agile/css/flat/flat.color.css">
		<link rel="stylesheet" href="assets/agile/css/flat/iconline.css">
		<link rel="stylesheet" href="assets/agile/css/flat/iconlogo.css">
		<link rel="stylesheet" href="assets/agile/css/flat/animate.css">
		<link rel="stylesheet" href="assets/app/css/app.css">
		<link rel="stylesheet" href="css/myStyle.css">
	</head>
	<body>
		<div id="section_container">
			<section id="list_section" data-role="section" class="active">
				<header>
				    <div class="titlebar">
				    	<a data-toggle="back" href="javascript:history.back(-1)"><i class="iconfont iconline-arrow-left"></i></a>
				    	<h1>发布动态</h1>
				    	<button id="fbbutton" class="disable">发布</button>
				    </div>
				    
				</header>
				<article data-role="article" id="main_article" data-scroll="verticle" class="active" style="top:44px;bottom:0px;">
					<div class="scroller"> 
						<form id="formInfo" class="" action="">
							<textarea class="idea" name="idea" placeholder="分享你们的新动态..."></textarea>
							
						</form>
					</div>  
				</article>
			</section>
		</div>
		
		<!--- third --->
		<script src="assets/third/jquery/jquery-2.1.3.min.js"></script>
		<script src="assets/third/jquery/jquery.mobile.custom.min.js"></script>
		<script src="assets/third/iscroll/iscroll-probe.js"></script>
		<script src="assets/third/arttemplate/template-native.js"></script>
		<!-- agile -->
		<script type="text/javascript" src="assets/agile/js/agile.js"></script>		
		<!--- bridge --->
		<script type="text/javascript" src="assets/bridge/exmobi.js"></script>
		<script type="text/javascript" src="assets/bridge/agile.exmobi.js"></script>
		<!-- app -->
		<script type="text/javascript" src="assets/app/js/app.js"></script>
		<script>
			$(function(){
				var textarea_height=$(".idea").offset().top;
				var screen_height=$("body").height();
				var fact_height=screen_height-textarea_height
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
						$.ajax({
							type:"post",
							url:"",
							
						});
					}
				});
			});
		</script>
		
	</body>
</html>