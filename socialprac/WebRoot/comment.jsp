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
		<title>发评论</title>
		<link rel="stylesheet" href="${ctx }/assets/agile/css/agile.layout.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.component.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.color.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconline.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconform.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/animate.css">
		<link rel="stylesheet" href="${ctx }/assets/component/timepicker/timepicker.css">
		<link rel="stylesheet" href="${ctx }/assets/app/css/app.css">
		<link rel="stylesheet" href="${ctx }/css/myStyle.css">
		<link rel="stylesheet" href="${ctx }/css/myComment.css">
	</head>
	<body>
		<div id="section_container">
			
			<section id="list_section" data-role="section" class="active">
				<header>
				    <div class="titlebar">
				    	<a data-toggle="back" href="${ctx}/status/list"><i class="iconfont iconline-arrow-left"></i></a>
				    	<h1>正文</h1>
				    </div>
				</header>
				<article data-role="article" id="main_article" data-scroll="verticle" class="active" style="top:44px;bottom:0px;">
					<div class="scroller" style="background:#eee"> 
						<div class="comment_text">
							<div class="mytitle">
				        		<img src="${ctx }/img/pic1.png" alt="头像" />
				        		<div class="title-name">${obj.displayName }</div>
				        		<div class="title-time">
				        			<fmt:formatDate value="${obj.releaseTime}" type="both" pattern="yyyy-MM-dd  hh:mm"/>
				        		</div>
				        	</div>
				        	<div class="text">
				            	${obj.textContent }
				            </div>
				            <div class="photo">
								<c:forEach items="${obj.picList}" var="pic" >
									<img src="${ctx }/upload/${pic}">
								</c:forEach>	
							</div>
						</div>
						<div class="commentbar">
							<span id="p1">评论</span>
							<span id="p2" onclick="spark('${obj.id}')">我来两句</span>
							<div style="clear:both"></div>
						</div>
						<div class="commentList">
							<ul>
								<c:forEach items="${obj.commentList}" var="it" varStatus="row">	
								<li>
									<div class="comment_text">
										<div class="mytitle">
							        		<img src="${ctx }/img/pic.png" alt="头像" />
							        		<div class="title-name">
							        			${it.user.displayName}
							        		</div>
							        		<div class="title-time">
							        			<fmt:formatDate value="${it.commentTime}" type="both" pattern="yyyy-MM-dd  hh:mm"/>
							        		</div>
							        	</div>
							        	<div class="comment_content">
							            	${it.textContent }
							            </div>
							            <div class="photo"></div>
									</div>
								</li>
								</c:forEach>
								
							</ul>
						</div>
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
		<script type="text/javascript" src="${ctx }/assets/component/timepicker/agile.timepicker.js"></script>	
		<script type="text/javascript" src="${ctx }/assets/component/extend.js"></script>
		<script type="text/javascript" src="${ctx }/assets/app/js/app.js"></script>
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
						A.alert('警告','评论内容不能为空！');
						return false;
					}else{
						alert($('#formInfo').serialize());
						$.ajax({
							type:"post",
							url:"${ctx}/comment/save",
							data: $('#formInfo').serialize(),
							dataType:"text",
							success:function(data){
								alert("-----"+data);
								if(data=="1"){
									alert('---------');
								}
									//window.location.href = "status/list";
							}
						});
					}
				});
			});
			function spark(id){
				location.href="${ctx}/comment/create?id="+id;
				
			}
		</script>
		
	</body>
</html>
