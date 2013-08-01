<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Object obj = request.getSession().getAttribute("loginaccount");
if(obj != null){
	request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<body>
	<form action="/waterweb/account/login" method="post">
		<fieldset>
			<legend>登陆</legend>
			<ul>
				<li><span>帐号：<input type="text" name="account.logincode" /></span></li>
				<li><span>密码：<input type="password" name="account.loginpasswd" /></span></li>
				<li><input type="submit" value="登陆" />&nbsp;<a href="/oauthexample/account/regeditfrom.jsp">注册</a></li>
			</ul>
		</fieldset>
	</form>
</body>
</html>