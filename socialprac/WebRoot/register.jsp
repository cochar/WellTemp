<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<meta content="telephone=no" name="format-detection" />
		<link rel="stylesheet" href="${ctx}/css/myLogin.css">
		
	</head>
	<body>
		<div class="content">
			<h1>注&nbsp;册</h1>
			<div class="re-bar">
				<ul class="re-ul">
					<li class="active">队员</li>
					<li>游客</li>
				</ul>
			</div>
			<div class="re-info">
				<div class="login-top ">
					<form action="${ctx}/regiter" method="post" id="formInfo">
						<div class="login-ic">
							<i></i>
							<input type="text" name="name" id="name" placeholder="学号" />
							<div class="clear"></div>
						</div>
						<div class="login-ic">
							<i></i>
							<input type="text" name="teamName" id="teamName" placeholder="团队名称" value="" />
							<div class="clear"></div>
						</div>
						<div class="login-ic">
							<i class="icon"></i>
							<input type="password" name="password" id="password" placeholder="密码" value="" />
							<div class="clear"></div>
						</div>
						<div class="login-ic">
							<i class="icon"></i>
							<input type="password" name="repassword" id="repassword" placeholder="确认密码" value="" />
							<div class="clear"></div>
						</div>
						<div class="log-bwn">
							<input type="button" name="" id="loginButton" value="regiter" onclick="regiter()"/>
						</div>
						
						
					</form>	
				</div>
				<div class="login-top" style="display:none">
					<form action="${ctx}/login" method="post" id="formInfoY">
						<div class="login-ic">
							<i></i>
							<input type="text" name="displayName" id="y-name" placeholder="昵称"  />
							<div class="clear"></div>
						</div>
						<div class="login-ic">
							<i></i>
							<input type="text" name="name" id="y-username" placeholder="用户名" value="" style="ime-mode:disabled" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
							<div class="clear"></div>
						</div>
						<div class="login-ic">
							<i class="icon"></i>
							<input type="password" name="password" id="y-password" placeholder="密码" value="" />
							<div class="clear"></div>
						</div>
						<div class="login-ic">
							<i class="icon"></i>
							<input type="password" name="repassword" id="y-repassword" placeholder="确认密码" value="" />
							<div class="clear"></div>
						</div>
						
						<div class="log-bwn">
							<input type="button" name="" id="loginButton" value="regiter" onclick="yregiter()"/>
						</div>
					</form>	
				</div>
				<h3 class="tishi" style="color:red"></h3>
			</div>	
			
		</div>
		
		<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
		<script>
			function login(){
				$.ajax({
							type:"post",
							url:"${ctx}/login",
							data: $('#formInfo').serialize(),
							dataType:"json",
							success:function(data){
								//alert(data+"+++++"+typeof(data));
								if(data=='success'){
									location.href="${ctx}/status/list";	
								}else if(data=='wrongName'){
									alert("用户名错误！");
								}else if(data=='wrongPassword'){
									alert("密码错误！");
								}else{
									location.href="${ctx}/QRCode.jsp";	
								}							
									//window.location.href = "status/list";
							}
						});
			}
		</script>
		<script>
		$(function(){
			$(document).ready(function()
			{
				$('.re-ul li').click(function(){
					var $this = $(this);
					var $t = $this.index();
					var $ul = $this.parent().parent().next().children('div');
					$this.siblings().removeClass();
					$this.addClass('active');
					$ul.css('display','none');
					$ul.eq($t).css('display','block');
				})
			})
		});
		function regiter(){
			var name=$("#name").val();
			var teamName=$("#teamName").val();
			var password=$("#password").val();
			var repassword=$("#repassword").val();
			
			if(name==""){
				$(".tishi").html("学号不能为空！");
				return false;
			}
			if(teamName==""){
				$(".tishi").html("团队名称不能为空！");
				return false;
			}
			if(password==""){
				$(".tishi").html("密码不能为空！");
				return false;
			}
			if(password!=repassword){
				$(".tishi").html("两次密码输入不一致！");
				return false;
			}
			$.ajax({
				type:"post",
				url:"${ctx}/register?identity=member",
				data: $('#formInfo').serialize(),
				dataType:"json",
				success:function(data){
					//alert(data+"+++++"+typeof(data));
					if(data=='success'){
						location.href="status/list";
					}else if(data=='noToken'){
						location.href="QRCode.jsp";
					}else{
						alert(data);
					}							
						//window.location.href = "status/list";
				}
			});
		}
		function yregiter(){
			var name=$("#y-name").val();
			var teamName=$("#y-username").val();
			var password=$("#y-password").val();
			var repassword=$("#y-repassword").val();
			
			if(name==""){
				$(".tishi").html("昵称不能为空！");
				return false;
			}
			if(teamName==""){
				$(".tishi").html("用户名不能为空！");
				return false;
			}
			if(password==""){
				$(".tishi").html("密码不能为空！");
				return false;
			}
			if(password!=repassword){
				$(".tishi").html("两次密码输入不一致！");
				return false;
			}
			$.ajax({
				type:"post",
				url:"${ctx}/register",
				data: $('#formInfoY').serialize(),
				dataType:"json",
				success:function(data){
					//alert(data+"+++++"+typeof(data));
					if(data=='success'){
						location.href="status/list";
					}else if(data=='noToken'){
						location.href="QRCode.jsp";
					}else{
						alert(data);
					}							
						//window.location.href = "status/list";
				}
			});
		}
</script>
	</body>
</html>
