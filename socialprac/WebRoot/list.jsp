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
		<title>BK社团</title>
		<link rel="stylesheet" href="${ctx }/assets/agile/css/agile.layout.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.component.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.color.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconline.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconform.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/animate.css">
		<link rel="stylesheet" href="${ctx }/assets/component/timepicker/timepicker.css">
		<link rel="stylesheet" href="${ctx }/assets/app/css/app.css">
		<link rel="stylesheet" href="${ctx }/css/myStyle.css">
	</head>
	<body class="animated zoomIn">
		<div id="section_container">
			
			<section id="form_section" data-role="section" class="active">
				<header>
				     <div class="titlebar">
				    	<a data-toggle="back" href="#">
				    	<img src="img/bk_logo_1.png"/>	
				    	</a>
				    	<!--<h1>菜单组件</h1>-->
				    	<a data-toggle="aside" href="javascript:void(0);" onclick="go()">
				    		<i class="iconfont iconline-write"></i>
				    		<!--<i class="iconfont iconline-pencil"></i>-->
				    	</a>
				    	
				    </div>
				    <div class="tabbar">
				    	<a class="tab active" data-role="tab" href="#normal_article" data-toggle="article">首页</a>
				    	<a class="tab" data-role="tab" href="#border_article" data-toggle="article">积分榜</a>
				    	<a class="tab" data-role="tab" href="#group_article" data-toggle="article">个人</a>
				    </div>
				</header>
				<article data-role="article" id="normal_article" data-scroll="verticle" class="active" style="top:88px;bottom:0px;">
					<div class="scroller"> 
						<ul class="mylist">
								<c:forEach items="${obj}" var="it" varStatus="row">		
								        <li>
								        	<div class="mytitle">
								        		<img src="img/309.JPG" alt="头像" />
								        		<div class="title-name">${it.user.name }</div>
								        		<div class="title-time">${it.releaseTime}</div>
								        	</div>
								            <div class="text">
								            	${it.textContent }
								            </div>
								            <div class="photo"></div>
								            
								            <div class="mybottom">
								            	<span onclick="comment(${it.id})"><i class="iconfont iconline-write"></i> 评论（${it.commentNumber}）</span>
								            	<span onclick="laud(${it.id})"><i class="iconfont iconline-heart"></i> 赞（${it.praiseNumber}）</span>
								            </div>
								        </li>
								</c:forEach>       
								        <li>
								            <div class="mytitle"></div>
								            <div class="text">
								            	Silder组件不会自动初始化，可以使用A.Slider(#id, opts)方式调用
								            </div>
								            <div class="mybottom"></div>
								        </li>
								        <li>
								            <div class="mytitle"></div>
								            <div class="text">
								            	Silder组件不会自动初始化，可以使用A.Slider(#id, opts)方式调用
								            </div>
								            <div class="mybottom"></div>
								        </li>
								        <li>
								            <div class="mytitle"></div>
								            <div class="text">
								            	Silder组件不会自动初始化，可以使用A.Slider(#id, opts)方式调用
								            </div>
								            <div class="mybottom"></div>
								        </li>
								        <li>
								           <div class="mytitle"></div>
								            <div class="text">
								            	Silder组件不会自动初始化，可以使用A.Slider(#id, opts)方式调用
								            </div>
								            <div class="mybottom"></div>
								        </li>
						</ul>    
					</div>  
				</article>
				
				<article data-role="article" id="border_article" data-scroll="verticle" style="top:88px;bottom:0px;">
					<div class="scroller">
						<a href="form.html">go</a>
						222222222222222222222222222222
					</div>
				</article>
				
				<article data-role="article" id="group_article" data-scroll="verticle" style="top:88px;bottom:0px;">
					<div class="scroller">
						3333333333333333333333333333
					</div>						
				</article>
				
			</section>
		</div>
		
		<!--- third --->
		<script src="${ctx }/assets/third/zepto/zepto.min.js"></script>
		<script src="${ctx }/assets/third/iscroll/iscroll-probe.js"></script>
		<script src="${ctx }/assets/third/arttemplate/template-native.js"></script>
		<!-- agile -->
		<script type="text/javascript" src="${ctx }/assets/agile/js/agile.js"></script>		
		<!--- bridge --->
		<script type="text/javascript" src="${ctx }/assets/bridge/exmobi.js"></script>
		<script type="text/javascript" src="${ctx }/assets/bridge/agile.exmobi.js"></script>
		<!-- app -->
		<script src="${ctx }/assets/component/timepicker/agile.timepicker.js"></script>	
		<script type="text/javascript" src="${ctx }/assets/component/extend.js"></script>
		<script type="text/javascript" src="${ctx }/assets/app/js/app.js"></script>
		<script>
			function go(){
				//$("body").addClass("animated fadeOutLeftBig");
				location.href="${ctx }/form.jsp";
				//$("body").addClass("animated fadeInLeftBig");
			}
			function laud(id){
				
			}
		</script>
	</body>
</html>