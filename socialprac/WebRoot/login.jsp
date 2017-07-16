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
			<h1>登&nbsp;录</h1>
			<div class="login-top">
			<form action="${ctx}/login" method="post" id="formInfo">
				<div class="login-ic">
					<i></i>
					<input type="text" name="name" id="name" placeholder="用户名/学号" onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名/学号'}" />
					<div class="clear"></div>
				</div>
				<div class="login-ic">
					<i class="icon"></i>
					<input type="password" name="password" id="password" placeholder="password" value="" />
					<div class="clear"></div>
				</div>
				<div class="log-bwn">
					<input type="button" name="" id="loginButton" value="login" onclick="login()"/>
				</div>
			</form>	
				<h3>没有帐号？&nbsp;<a href="register.jsp">去注册</a></h3>
				
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
	</body>
</html>
