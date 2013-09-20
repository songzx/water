<%@page import="com.water.metamodel.account.AccountAdmin"%>
<%@page import="com.water.metamodel.account.Account"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.water.actions.PageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	PageBean pageBean = (PageBean) request.getAttribute("pageBean");
	if (pageBean == null) {
		out.println("没有返回列表数据！");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类过滤事项</title>
<link href="/waterweb/dwz-ria/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>

<script type="text/javascript" src="/waterweb/scripts/jquery.js"></script>
<script src="/waterweb/dwz-ria/js/dwz.print.js" type="text/javascript"></script>

<script type="text/javascript">
	function MovePage(action) {
		var icur = parseInt($("#curPage").val());
		var pages = parseInt($("#totalPages").val());
		var pageSize = parseInt($("#pageSize").val());
		switch (action) {
		case -2:
			icur = 1;
			break;
		case -1:
			icur = icur > 1 ? icur - 1 : 1;
			break;
		case 1:
			icur = pages > icur ? icur - (-1) : pages;
			break;
		case 2:
			icur = pages;
			break;
		case 0:
			var mathandkeyteststr = /[^0-9]/g;
			if (mathandkeyteststr.test(icur) || (parseInt(icur) < 1) || (parseInt(icur) > pages)) {
				alert("请您输入正确的页码数。");
				$("#curPage").focus();
				return false;
			}
		}
		$("#curPage").val(icur);
		$("#pageSize").val(pageSize);
		$("#form1").submit();
	}

	$(function() {
		/*var fn_checkbox_click = function(){
			var v_name = $(this).attr('for');
			$('input[name=id][type=checkbox]').attr('checked',$(this).attr('checked'));
		}*/

		var fn_add = function() {

		}
		
		var fn_export = function(filetype) {

		}

		$('input[name=all][type=checkbox]').click(function() {
			var v_name = $(this).attr('for');
			$('input[name=id][type=checkbox]').attr('checked', $(this).attr('checked'));
		});

	});
	
	function fn_modif(url) {
		if($('input[type=checkbox][name=id]:checked').length == 0){
			alert('请选择要编辑的记录');
		}else if($('input[type=checkbox][name=id]:checked').length == 1){
			var v_id = $('input[type=checkbox][name=id]:checked').val();
			window.open(url+"?id="+v_id);
		}else{
			alert('多选，请确认要编辑的记录');
		}
	}
	function fn_remove(url) {
		if($('input[type=checkbox][name=id]:checked').length > 0){
			if(confirm('是否删除')){
				var v_id = $('input[type=checkbox][name=id]:checked').val();
				window.open(url+"?id="+v_id);
			}
		}else{
			alert('请确认要删除的记录');
		}
	}
	
</script>
</head>
<body>
	<form action="/waterweb/account/list" method="post" name="form1" id="form1">
		<div style="border: solid 1px #f00; margin: 10px 0; padding: 5px 0;">
			关键字：<input type="text" name="keyword" value="<%=pageBean.getParams().get("keyword") == null? "": pageBean.getParams().get("keyword")%>" /> 
			<input type="submit" value="查 询" />
		</div>
		<div style="border: solid 1px #f00; margin: 10px 0; line-height: 25px;">
			<a class="icon" href="/waterweb/admin/account/add.jsp">新增</a>| 
			<a class="icon" href="javascript:fn_modif('/waterweb/account/premodify')">编辑</a>| 
			<a class="icon" href="javascript:fn_remove('/waterweb/account/remove')">删除</a>| 
			<a class="icon" href="javascript:fn_export('xls')">导出xls</a> | 
			<a class="icon" href="javascript:window.print()">打印</a>
		</div>
		<div id="print">
			<table border="1" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th><input type="checkbox" name="all" for="id" /></th>
					<th>帐号</th>
					<th>邮箱</th>
				</tr>
				<%
					for (Iterator it = pageBean.getResult().iterator(); it.hasNext();) {
						AccountAdmin account = (AccountAdmin) it.next();
				%>
				<tr>
					<td align="center" nowrap="nowrap"><input type="checkbox" name="id" value="<%=account.getId()%>" /></td>
					<td align="center" nowrap="nowrap"><%=account.getLogincode()%></td>
					<td align="center" nowrap="nowrap"><%=account.getEmail()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div class="fy">
			<input type="hidden" name="totalPages" id="totalPages" value="<%=pageBean.getTotalPages()%>" /> 
			<input type="hidden" maxlength="2" value="<%=pageBean.getPageSize()%>" name="pageSize" id="pageSize" style="width: 20px;" /> 
			<input type="hidden" maxlength="3" value="<%=pageBean.getCurPage()%>" name="curPage" id="curPage" style="width: 20px;" /> 
			【总共<span class="totalrows"><%=pageBean.getTotalrows()%></span>条记录】&nbsp;&nbsp;【<%=pageBean.getStartrows()+"-"+pageBean.getEndrows() %>】
			【页码 <span class="currentpage"><%=pageBean.getCurPage()%></span>/<span class="totalpages"><%=pageBean.getTotalPages()%></span> 】&nbsp;&nbsp; <a onclick="MovePage(-2);">首页</a>&nbsp;
			<a onclick="MovePage(-1);">上一页</a>&nbsp; <a onclick="MovePage(1);">下一页</a>&nbsp; <a onclick="MovePage(2);">末页</a>
		</div>
	</form>
</body>
</html>