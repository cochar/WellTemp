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
		<title>USTB社会实践</title>
		<link rel="stylesheet" href="${ctx }/assets/agile/css/agile.layout.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.component.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/flat.color.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconline.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/iconform.css">
		<link rel="stylesheet" href="${ctx }/assets/agile/css/flat/animate.css">
		<link rel="stylesheet" href="${ctx }/assets/component/timepicker/timepicker.css">
		<link rel="stylesheet" href="${ctx }/assets/app/css/app.css">
		<link rel="stylesheet" href="${ctx }/css/myStyle.css">
		<!--iscroll 滚动插件 -->
		<script type="application/javascript" src="${ctx }/isrcoll/iscroll.js"></script>
		<link rel="stylesheet" href="${ctx }/isrcoll/scrollbar.css">  
	</head>
	<body class="animated zoomIn">
		<div id="section_container">
			
			<section id="form_section" data-role="section" class="active">
				<header>
				     <div class="titlebar">
				    	<a data-toggle="back" href="#">
				    	<%-- <img src="${ctx }/img/bk_logo_1.png"/>	 --%>
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
				<!-- data-scroll="verticle" -->
				<article data-role="article" id="normal_article"  class="active" style="top:44px;bottom:0px;">
					<div id="wrapper">
					 <div class="scroller"> 
						 <div id="pullDown">
							<span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新...</span>
						</div> 
						<ul class="mylist" id="thelist">
								<c:forEach items="${obj.statusList}" var="it" varStatus="row">		
								        <li>
								        	<div class="mytitle">
								        		<img src="${ctx }/img/309.JPG" alt="头像" />
								        		<div class="title-name">${it.displayName }</div>
								        		<div class="title-time">
								        		<fmt:formatDate value="${it.releaseTime}" type="both" pattern="yyyy-MM-dd  hh:mm"/>
								        		</div>
								        	</div>
								            <div class="text" onclick="commentNew(${it.id},${it.commentNumber})">
								            	${it.textContent }
								            </div>
								            <div class="photo"></div>
								            
								            <div class="mybottom">
								            	<span onclick="commentNew('${it.id}','${it.commentNumber}')"><b class="list_comment"></b> <sub>评论（${it.commentNumber}）</sub></span>
								            	<span onclick="laud('${it.id}',this)"><b id="laud" class="list_laud"></b><sub> 赞（${it.praiseNumber}）</sub></span>
								            </div>
								        </li>
								</c:forEach>       
								       <!--  <li>
								            <div class="mytitle"></div>
								            <div class="text">
								            	Silder组件不会自动初始化，可以使用A.Slider(#id, opts)方式调用
								            </div>
								            <div class="mybottom"></div>
								        </li> -->
								        
						</ul>  
						 <div id="pullUp">
							<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
						</div> 
						<!-- <div id="noMore" hidden>
							<span>无更多动态</span>
						</div> 	 -->  
					 </div>
					</div> 
				</article>
				<!-- 排行榜 -->
				<article data-role="article" id="border_article"  style="top:88px;bottom:0px;">
					
					<div class="scroller">
						<div class="rank-title">
							<h4 class="ran-rank-title">团队榜</h4>
						</div>
						<!-- <div>
							<ul class="rank-header">
								<li class="team-name">社团名称</li>
								<li class="team-integral">积分</li>
							</ul>
						</div> -->
						<div class="rank-team">
							<ul>
							<c:forEach items="${obj.teamList}" var="team" varStatus="row">	
								<li>
									<sub class="team-num">${row.count}</sub>
									<b class="team-num-name">${team.name}</b>
									<span class="team-num-jf">${team.score}</span>
								</li>
								</c:forEach>
								
							</ul>
						</div>
						
						
						<div class="rank-title">
							<h4 class="ran-rank-title">个人榜单</h4>
						</div>
						<!-- <div>
							<ul class="rank-header">
								<li class="team-name">社团名称</li>
								<li class="team-integral">积分</li>
							</ul>
						</div> -->
						<div class="rank-team">
							<ul>
								<c:forEach items="${obj.userList}" var="user" varStatus="row">	
								<li>
									<sub class="team-num">${row.count}</sub>
									<b class="team-num-name">${user.name}</b>
									<span class="team-num-jf">${user.score}</span>
								</li>
								</c:forEach>
								
							</ul>
						</div>
					</div>
					
				</article>
				<!-- 个人中心  -->
				<article data-role="article" id="group_article" data-scroll="verticle" style="top:88px;bottom:0px;">
					<div class="scroller">
						<div class="team">
			        		<img src="${ctx }/img/309.JPG" alt="头像" />
			        		<span class="teamName">${obj.user.displayName}</span>
			        		
			        	</div>
			        	<div class="jifen">
				        	<c:if test="${obj.user.teamId!=null}" >
				        		<span id="j1">团队积分</span>
				        		<span id="j2">${obj.user.team.score }</span>
				        	</c:if>
				        	<c:if test="${obj.user.teamId==null}">
				        		<span id="j1">个人积分</span>
				        		<span id="j2">${obj.user.score }</span>
				        	</c:if>
			        	</div>
			        	
			        	<!-- <div class="jieshao">
			        		<p>
			        			克里夫兰在20世纪50年代曾拥有多达90余万的人口，然而在随后的数十年中人口不断流失，至美国2000年人口普查，克里夫兰市区人口下降至478,403。最新的美国2010年人口普查显示，克里夫兰市区人口进一步下降到396,815，为美国第45大城市，俄亥俄州第二大城市。它是幅员数县、俄亥俄最大的都会区，大克里夫兰的中心（面积随定义方式不同而改变）。克里夫兰-Elyria-Mentor 都会区2011年人口2,068,283，是美国第28大都会区。如果以克里夫兰-Akron-Elyria作为单位，人口可达到2,871,084（2011年），排名上升至第16位。
			        		
			        		</p>
			        	
			        	
			        	</div> -->
					</div>						
				</article>
				
			</section>
			<%-- <input id="lastPage" value="${page.lastPage}" class="lastPage"  type="hidden" > --%>
			<input id="pageNo" value="1" class="pageNo" type="hidden" >
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
		
			function go(){
				//$("body").addClass("animated fadeOutLeftBig");
				location.href="${ctx }/form.jsp";
				//$("body").addClass("animated fadeInLeftBig");
			}
			function laud(id,s){
				$.ajax({
					url:"${ctx}/status/praise?id="+id,
					success:function(result){
						if(result=='1'){
							var b=$(s).children("#laud");
							b.removeClass("list_laud").addClass("list_lauded");
							var $parent=b.parent();
							$parent.css("color","ea9518");
						}
					}
					
				});
				
			}
			function commentNew(id,commentNum){
				//如果有评论的话
				if(commentNum>0){
					location.href="${ctx}/status/single?id="+id;	
				}else{
					location.href="${ctx}/comment/create?id="+id;
				}				
			}
			
		
		</script>
		 <script type="text/javascript">
			var myScroll, 
				pullDownEl, pullDownOffset,
				pullUpEl, pullUpOffset,
				generatedCount = 0;
			
			/**
			 * 下拉刷新 （自定义实现此方法）
			 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
			 */
			function pullDownAction () {
				setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
					/* var el, li, i;
					el = document.getElementById('thelist');
			
					for (i=0; i<3; i++) {
						li = document.createElement('li');
						li.innerText = 'Generated row ' + (++generatedCount);
						el.insertBefore(li, el.childNodes[0]);
					} */
					location.href="${ctx}/status/list";
					myScroll.refresh();		//数据加载完成后，调用界面更新方法   Remember to refresh when contents are loaded (ie: on ajax completion)
				}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
			}
			
			/**
			 * 滚动翻页 （自定义实现此方法）
			 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
			 */
			function pullUpAction () {
				setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
					/* var el, li, i;
					el = document.getElementById('thelist');
			
					for (i=0; i<3; i++) {
						li = document.createElement('li');
						li.innerText = 'Generated row ' + (++generatedCount);
						el.appendChild(li, el.childNodes[0]);
					} */
					
					ajaxLoad();
					myScroll.refresh();		// 数据加载完成后，调用界面更新方法 Remember to refresh when contents are loaded (ie: on ajax completion)
				}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
			}
			
			/**
			 * 初始化iScroll控件
			 */
			function loaded() {
				pullDownEl = document.getElementById('pullDown');
				pullDownOffset = pullDownEl.offsetHeight;
				pullUpEl = document.getElementById('pullUp');	
				pullUpOffset = pullUpEl.offsetHeight;
				
				myScroll = new iScroll('wrapper', {
					scrollbarClass: 'myScrollbar', /* 重要样式 */
					useTransition: false, /* 此属性不知用意，本人从true改为false */
					topOffset: pullDownOffset,
					onRefresh: function () {
						if (pullDownEl.className.match('loading')) {
							pullDownEl.className = '';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
						} else if (pullUpEl.className.match('loading')) {
							pullUpEl.className = '';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
						}
					},
					onScrollMove: function () {
						if (this.y > 5 && !pullDownEl.className.match('flip')) {
							pullDownEl.className = 'flip';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
							this.minScrollY = 0;
						} else if (this.y < 5 && pullDownEl.className.match('flip')) {
							pullDownEl.className = '';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
							this.minScrollY = -pullDownOffset;
						} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
							pullUpEl.className = 'flip';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
							this.maxScrollY = this.maxScrollY;
						} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
							pullUpEl.className = '';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
							this.maxScrollY = pullUpOffset;
						}
					},
					onScrollEnd: function () {
						if (pullDownEl.className.match('flip')) {
							pullDownEl.className = 'loading';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';				
							pullDownAction(); 	// Execute custom function (ajax call?)
							/* ajaxLoad(); */
						} else if (pullUpEl.className.match('flip')) {
							pullUpEl.className = 'loading';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';				
							 pullUpAction(); 	// Execute custom function (ajax call?)
													
						}
					}
				});
				setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
				
			}
			
			//初始化绑定iScroll控件 
			 document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
			document.addEventListener('DOMContentLoaded', loaded, false);  
			
		</script>
		<script>
		function ajaxLoad(){
			/* var n = true; */
			/* if($("#lastPage").val()=='false'){ */
				/* $.mytoast({text: "正在加载，请稍后...",type: "success"}); */
				/* n = false; */
				$.ajax({
					url:"${ctx}/status/scrollUp?pageNo="+$("#pageNo").val(),
					async:false,
					dataType:"json",
					success:function(result){
						var jsonObj = eval("(" + result + ")");
						  for (var k in jsonObj) {
						  /*   alert(k + " : " + jsonObj[k].textContent); */
						  
						  var content='<li><div class="mytitle"><img src="" alt="头像" />'+
						  			'<div class="title-name">'+jsonObj[k].displayName+'</div><div class="title-time">'+
			        		'</div></div>'+
			        		
			        	
			            '<div class="text" onclick="commentNew(\''+jsonObj[k].id+'\',\''+jsonObj[k].commentNumber+'\')">'+jsonObj[k].textContent+'</div>'+
			           '<div class="photo"></div><div class="mybottom">'+
			           '<span onclick="commentNew(\''+jsonObj[k].id+'\',\''+jsonObj[k].commentNumber+'\')"><b class="list_comment"></b> <sub>评论（'+jsonObj[k].commentNumber+'）</sub></span>'+
			            	'<span onclick="laud(\''+jsonObj[k].id+'\',this)"><b id="laud" class="list_laud"></b><sub> 赞（'+jsonObj[k].praiseNumber+'）</sub></span></div></li>'; 
						    
						     
			            	$("#thelist").append(content);
						    
						  }  
						$("#pageNo").val(parseInt($("#pageNo").val())+1);
						/* var last = result.substring(result.indexOf('<input id="lastPage" value="') + '<input id="lastPage" value="'.length,result.indexOf('" class="lastPage"  type="'));
						$("#lastPage").val(last);
						var page = result.substring(result.indexOf('<input id="pageNo" value="') + '<input id="pageNo" value="'.length,result.indexOf('" class="pageNo" type="'));
						$("#pageNo").val(page);
						var label = '<div id="context">';
						result = result.substring(result.indexOf(label) + label.length,result.indexOf('<div class="footReturn">'));
						$("#context").append(result); */
				}});
			}
			/* if(n){
				$.mytoast({text: "没有可加载的数据...",type: "notice"});
			}
		} */
		
		</script>
	</body>
</html>
