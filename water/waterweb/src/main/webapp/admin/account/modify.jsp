<%@page import="com.water.metamodel.account.AccountAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	AccountAdmin accountAdmin = (AccountAdmin)request.getAttribute("accountAdmin");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
</head>
<body>
<form action="/waterweb/account/modify" method="post" name="form1" id="form1">
	<input type="hidden" name="id" value="<%=accountAdmin.getId()%>"/>
	<table cellpadding="0" width="100%" border="0">
		<tr>
			<td>登陆帐号：</td><td><input type="text" name="logincode" value="<%=accountAdmin.getLogincode()%>"/></td>
			<td>登陆密码：</td><td><input type="password" name="loginpasswd" value="<%=accountAdmin.getLoginpasswd()%>"/></td>
			<td>登陆邮箱：</td><td><input type="text" name="email" value="<%=accountAdmin.getEmail()%>"/></td>
		</tr>
	</table>
	
	<input type="submit" value="提　交"/>
</form>
</body>
</html>