<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<meta content="telephone=no" name="format-detection" />
		<link rel="stylesheet" href="${ctx }/css/myLogin.css">
	</head>
	<body>
		<div class="content">
			<h1>登&nbsp;录</h1>
			<div class="login-top">
				<div class="login-ic">
					<i></i>
					<input type="text" name="studentNum" id="studentNum" placeholder="用户名/学号" onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名/学号'}" />
					<div class="clear"></div>
				</div>
				<div class="login-ic">
					<i class="icon"></i>
					<input type="text" name="password" id="password" placeholder="password" value="" />
					<div class="clear"></div>
				</div>
				<div class="log-bwn">
					<input type="submit" name="" id="" value="login" />
				</div>
				<h3>没有帐号？&nbsp;<a href="#">去注册</a></h3>
				
			</div>
		</div>
	</body>
</html>
