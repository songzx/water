<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${read.head.title }</title>
</head>
<body>
<form action="${read.formaction }" method="post" id="addform">
	<input type="hidden" name="id" value="${read.id }"/>
	
	<table>
		<!-- item.value 直接输出html的表单片段，如<input type="text" name="key"/> -->
		<#list item as read.list>
		<tr>
			<td>${item.key }</td><td>${item.value }</td>
		</tr>
		</#list>
	</table>
	<div>
		<input type="submit" value="提　交"/>
	</div>
</form>
</body>
</html>