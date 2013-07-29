<%@page import="java.util.Properties"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<h1>Hello World!111111</h1>
	<%
		Map<String,String> tmpmap = System.getenv();
		for(Iterator<String> it = tmpmap.keySet().iterator(); it.hasNext();){
			String tmpkey = it.next();
			out.println(tmpkey+"---"+tmpmap.get(tmpkey)+"<br/>");	
		}
		Properties tmpProperties = System.getProperties();
		for(Iterator<Object> it = tmpProperties.keySet().iterator();it.hasNext();){
			Object key = it.next();
			out.println(key+" -- "+tmpProperties.getProperty(key.toString())+"<br/>");
		}
	%>
</body>
</html>
