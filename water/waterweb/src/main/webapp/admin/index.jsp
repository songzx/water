<%@page import="java.util.ArrayList"%>
<%@page import="com.water.metamodel.tree.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.water.basictool.OnlineAccount"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	Map<String, String> loginaccount = (Map<String, String>) session.getAttribute("loginaccount");
	System.out.println(OnlineAccount.getInstance().getWebUsersMap().toString());
	String[] onlines = OnlineAccount.getInstance().getWebUsersMap().get(loginaccount.get("id"));
	
	List<Category> categorys = request.getAttribute("categories")==null?new ArrayList<Category>():(List<Category>)request.getAttribute("categories");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>water计划</title>

<link href="/waterweb/dwz-ria/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/waterweb/dwz-ria/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/waterweb/dwz-ria/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/waterweb/dwz-ria/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="/waterweb/dwz-ria/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<style type="text/css">
	#header{height:85px}
	#leftside, #container, #splitBar, #splitBarProxy{top:90px}
</style>
<!--[if lte IE 9]>
<script src="/waterweb/dwz-ria/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="/waterweb/dwz-ria/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/jquery.validate.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="/waterweb/dwz-ria/chart/raphael.js"></script>
<script type="text/javascript" src="/waterweb/dwz-ria/chart/g.raphael.js"></script>
<script type="text/javascript" src="/waterweb/dwz-ria/chart/g.bar.js"></script>
<script type="text/javascript" src="/waterweb/dwz-ria/chart/g.line.js"></script>
<script type="text/javascript" src="/waterweb/dwz-ria/chart/g.pie.js"></script>
<script type="text/javascript" src="/waterweb/dwz-ria/chart/g.dot.js"></script>

<script src="/waterweb/dwz-ria/js/dwz.core.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.util.date.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.drag.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.tree.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.accordion.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.ui.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.theme.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.navTab.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.tab.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.resize.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.dialog.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.stable.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.ajax.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.pagination.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.database.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.effects.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.panel.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.history.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.combox.js" type="text/javascript"></script>
<script src="/waterweb/dwz-ria/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="/waterweb/dwz-ria/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("/waterweb/admin/dwz.frag.xml", {
		loginUrl:"login_dialog.html", 
		loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/waterweb/dwz-ria/themes"}); // themeBase 相对于index页面的主题base路径
			//setTimeout(function() {$("#sidebar .toggleCollapse div").trigger("click");}, 10);
		}
	});
	//初始化菜单
	$.post(encodeURI($('#navMenu').find('li').eq(0).find('a').eq(0).attr("href")),{},function(html){
		$("#sidebar").find(".accordion").remove().end().append(html);
		$('#navMenu').find('li').eq(0).addClass("selected");
	});
		
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="javascript:">标志</a>
				<ul class="nav">
					<li><a href="javascript:">欢迎你，admin</a></li>
					<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
					<li><a href="/waterweb/account/loginout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<div id="navMenu">
				<ul>
					<%
					for(Category category : categorys){
					%>
					<li><a href="/waterweb/category/cagetorylistbyaccount?categoryid=<%=category.getId() %>&categoryname=<%=category.getName() %>"><span><%=category.getName() %></span></a></li>
					<%} %>
				</ul>
			</div>
			<!-- navMenu -->
			
		</div>

		<div id="leftside" style="top:90px">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				
				<!-- 菜单1start -->
				
				<!-- 菜单1end -->
			</div>
		</div>
		
		
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<iframe src="/waterweb/admin/index_body.jsp" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2013 <a href="demo_page2.html" target="dialog">Water团队</a> </div>
</body>
</html>