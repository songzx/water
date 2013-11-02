<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${list.head.title}</title>
</head>
<body>
	<div>
		<form method="post" action="${list.formaction }" id="listform">
			<div class="searchform">${list.searchform }</div>
			<div class="authz">${list.authz }</div>
			<table>
				<tr>
					<th><input type="checkbox" name="all" /></th>
					<#list item as list.th>
					<th>${item.name }</th></#list>
				</tr>
				<#list item as list.tr>
				<tr>
					<td><input type="checkbox" name="id" value="${item['id'] }"/></td>
					<#list key as list.th>
					<td>${item[key] }</td> 
					</#list>
				</tr>
				</#list>
			</table>
			<div class="pager">${list.pager }</div>
		</form>
	</div>
</body>
</html>