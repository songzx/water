<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/waterweb/admin/css/common.css" />
<script type="text/javascript" src="/waterweb/admin/js/jquery-1.5.js"></script>
<link rel="stylesheet" type="text/css" href="/waterweb/jstree/!style.css" />
<script type="text/javascript" src="/waterweb/jstree/jquery.jstree.js"></script>
<script type="text/javascript">
$(function(){
	
	$('input[name=all][type=checkbox]').click(function(){
		var v_checked = $(this).attr('checked');
		$('input[name=id][type=checkbox]').each(function(){
			$(this).attr('checked',v_checked);
		});
	});
	$('table[class=tb] tr').click(function(){
		$('input[name=id][type=checkbox]',this).attr('checked',$('input[name=id][type=checkbox]',this).attr('checked')?false:true);
	});
	$('input[name=id][type=checkbox]').click(function(){
		$(this).attr('checked',$(this).attr('checked')?false:true);
	});
});
</script>
</head>
<body>
	<div class="divnav">系统设置->菜单管理</div>

	<div style="float: left; display: block; overflow: auto; width: 240px;">
		<iframe src="/waterweb/admin/category/lefttree.jsp" frameborder="0" width="100%" height="500px"></iframe>
	</div>
	<div style="margin-left: 246px;border: solid 1px #ccc;" id="content">
		<div>
			<input type="text" name="keyworkd" /><input type="submit" value="查	询" />
		</div>

		<div class="divoper">
			<span><a href="">新增菜单至</a></span> | <span><a href="">删除</a></span> | <span><a href="">编辑</a></span> | <span><a href="">打印</a></span> | <span><a href="">导出xls</a></span>
		</div>
		<table cellpadding="0" cellspacing="0" border="0" class="tb">
			<tr>
				<th align="center"><input type="checkbox" name="all"/></th>
				<th>名称</th>
				<th>路径</th>
				<th>所属角色</th>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
			<tr>
				<td align="center"><input type="checkbox" name="id" value="01"/></td>
				<td>菜单管理(category)</td>
				<td>/系统设置/菜单管理</td>
				<td>超管(role_admin) | 管理员(role_admin)</td>
			</tr>
		</table>
		<div>
			<input type="hidden" name="totalPages" id="totalPages" value="5"><input type="hidden" maxlength="2" value="10" name="pageSize" id="pageSize" style="width: 20px;"><input
				type="hidden" maxlength="3" value="1" name="curPage" id="curPage" style="width: 20px;" title="请输入页码并按回车"> 【总共<span class="totalrows">5</span>条记录】&nbsp;&nbsp;
			【页码 <span class="currentpage">1</span>/<span class="totalpages">1</span> 】&nbsp;&nbsp; <a onclick="MovePage(-2);">首页</a>&nbsp; <a onclick="MovePage(-1);">上一页</a>&nbsp; <a
				onclick="MovePage(1);">下一页</a>&nbsp; <a onclick="MovePage(2);">末页</a>
		</div>
	</div>
</body>
</html>