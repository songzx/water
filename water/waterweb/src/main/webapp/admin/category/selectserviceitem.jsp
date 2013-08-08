<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

String category =  request.getParameter("category") == null ? "-1" : request.getParameter("category");
String categoryname =  request.getParameter("categoryname") == null ? "" : request.getParameter("categoryname");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分类列表</title>
</head>
<body>
已选事项(<font color="red"><%=categoryname %></font>)<br/>
<div>
	<iframe frameborder="0" src="/hp/admin/serviceitem/categoryserviceitemlist.jsp?category=<%=category %>" width="100%" height="200" name="categoryserviceitem" id="categoryserviceitem"></iframe>
</div>
待选事项<br/>
<div>
<iframe frameborder="0" src="/hp/admin/serviceitem/notcategoryserviceitemlist.jsp?category=<%=category %>" width="100%" height="200" name="notcategoryserviceitemlist" id="notcategoryserviceitemlist"></iframe>
</div>
</body>
</html>