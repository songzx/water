<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String accountid = "4EE9C8F0F12E11E2BF63E4517F000001";//(String)session.getAttribute("accountid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript" src="/waterweb/jstree/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="/waterweb/jstree/!style.css" />
<script type="text/javascript" src="/waterweb/jstree/jquery.jstree.js"></script>

<style type="text/css">
li {
	display: list-item;
	float: left;
	margin: -5px 0;
	padding: 0 10px;
	text-align:center;
	position: relative;
}
</style>
<script type="text/javascript">
	$(function() {
		var v_navtab_url = "/waterweb/admin/category/tmp/" + $('input[type=hidden][name=accountid]').val() + "_0.xml";
		
		$.ajax({
			url : v_navtab_url,
			success : function(data) {
				var v_li = "";
				$.each($(data).find('root'),function(){
					$('item content name',this).each(function(){
						v_li += '<li id="'+$('id',$(this).parent()).text()+'">'+$(this).text()+'</li>';
					});
				});
				$('#navtab').html(v_li);
				
				$('#navtab li').click(function(){
					//$('#lefttree').load('/waterweb/admin/category/lefttree.jsp',{parentid:$(this).attr('id')},{});
					$('#treeframe').attr('src','/waterweb/admin/category/lefttree.jsp?parentid='+$(this).attr('id'));
					$(this).css('border','solid 1px #ccc');
					$(this).siblings().css('border','');
					
				});
			}
		});
		
		

	});
</script>
</head>
<body>
	<input type="hidden" name="accountid" value="<%=accountid%>" />

	<div>
		<!-- 头部 -->
		<div style="overflow: hidden; height: 50px; border: solid 1px #f00;">
			<div style="height: 50px;">
				<div style="float: left; width: 250px; height: 50px; border: solid 1px #ccc">logo</div>
				<ul style="display: block; position: absolute; top: 8px; right: 0; list-style: none;">
					<li><a href="javascript:">欢迎你，admin</a></li>
					<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
					<li><a href="/waterweb/account/loginout">退出</a></li>
				</ul>
			</div>
		</div>
		<!-- 头部end -->

		<!-- 菜单 -->
		<div style="border: solid 1px #f00; height: 30px; margin: 5px 0;">
			<ul style="list-style: none;" id="navtab">
				<li>常用栏目</li>
			</ul>
		</div>
		<!-- 菜单end -->

		<!-- 内容 -->
		<div style="border: solid 1px #f00;">
			<div style="float: left; display: block; overflow: auto; width: 240px; border: solid 1px #ccc;" id="lefttree">
				<iframe src="javascript:" frameborder="0" width="100%" height="550px" id="treeframe"></iframe>
			</div>
			<div style="margin-left: 246px; border: solid 1px #ccc; height: 550px;">
			
			</div>
		</div>
		<!-- 内容end -->
	</div>
</body>
</html>