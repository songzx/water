<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新</title>
<link type="text/css" rel="stylesheet" href="/waterweb/admin/css/common.css" />
</head>
<body>
	<form action="/waterweb/admin/category/list.jsp">
		<input type="hidden" name="id" value="1"/>
	
		<table cellpadding="0" cellspacing="0" border="0" class="tbform">
			<tr>
				<td>代码</td>
				<td><input type="text" name="code" value="code"/></td>
			</tr>
			<tr>
				<td>名称</td>
				<td><input type="text" name="name" value="名称"/></td>
			</tr>
			<tr>
				<td>简称</td>
				<td><input type="text" name="abbreviation" value="简称"/></td>
			</tr>
			<tr>
				<td>图标</td>
				<td><input type="text" name="imgsrc" value="图标"/></td>
			</tr>
			<tr>
				<td>链接</td>
				<td><input type="text" name="url" value="链接"/></td>
			</tr>
			<tr>
				<td>其它属性(XML)</td>
				<td><input type="text" name="otherproperties" value="其它属性"/></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="更 新" /></td>
			</tr>
		</table>
	</form>
</body>
</html>