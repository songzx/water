<%@page import="com.ucap.hp.tools.ValidateUtil"%>
<%@page import="org.apache.commons.dbutils.MyRowProcessor"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.Reader"%>
<%@page import="java.sql.Clob"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.ibm.db2.jcc.am.ie"%>
<%@page import="java.io.OutputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.Writer"%>
<%@page import="freemarker.template.Template"%>
<%@page import="java.io.File"%>
<%@page import="freemarker.template.Configuration"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.dbutils.handlers.MapListHandler"%>
<%@page import="org.apache.commons.dbutils.DbUtils"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.ucap.hp.tools.DatasourceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
public void getcategorytree(List<Map<String, Object>> result,String id,Map<String,String> reresult){
	boolean flag = false;
	Map<String,Object> map = null;
	for(Map<String,Object> tmpmap : result){
		if(id.equals(tmpmap.get("PARENTID").toString().trim())){
			getcategorytree(result, tmpmap.get("ID").toString().trim(),reresult);
			flag = true;
		}
	}
	if(!flag){
		//reresult.put(key, value);
	}
}
%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
int curPage = ValidateUtil.getInstance().checkInt(request.getParameter("curPage"), 1);
int pageSize = ValidateUtil.getInstance().checkInt(request.getParameter("pageSize"), 10,100);
String xzxk =  request.getParameter("xzxk") == null ? "1" : request.getParameter("xzxk");
String category =  request.getParameter("category") == null ? "-1" : request.getParameter("category");
String keyword =  request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
String oper =  request.getParameter("oper") == null ? "" : request.getParameter("oper");
String preoper =  request.getParameter("preoper") == null ? "addservice" : request.getParameter("preoper");
try {
	int rscount = 0;
	conn = DatasourceUtil.getInstance("/data/tomcatlist/apache-tomcat-7.0.34_hp/webapps/hp/WEB-INF/resourceconfig/datasource.properties").getDataSource("TMPWBSB").getConnection();
	
	if("addservice".equals(oper)){
		String sxids[] = request.getParameterValues("sxid") == null ? new String[0] : request.getParameterValues("sxid");
		pstmt = conn.prepareStatement("insert into tb_wsmp_category_service(SXID,CATEGORYID) values(?,?) ");
		int index = 0;
		for(String sxid : sxids){
			pstmt.setString(1, sxid);
			pstmt.setString(2,category);
			pstmt.addBatch();
			index ++;
		}
		if(index == sxids.length){
			pstmt.executeBatch();
		}
	}
	
	pstmt = conn.prepareStatement("select count(*)  from t_share_permissionitem t where t.sxname like ? and t.id not in(select sxid from tb_wsmp_category_service where categoryid = ? )");
	pstmt.setString(1, "%"+keyword+"%");
	pstmt.setString(2, category);
	rs = pstmt.executeQuery();
	if(rs.next()){
		rscount = rs.getInt(1);
	}
	
	pstmt = conn.prepareStatement("SELECT * FROM (SELECT T.*,ROWNUMBER() OVER(ORDER BY t.id ASC ) AS ROWID  FROM T_SHARE_PERMISSIONITEM T where t.sxname like ? and t.id not in(select sxid from tb_wsmp_category_service where categoryid = ? ) ) AS A WHERE A.ROWID > ? AND A.ROWID <= ?");
	pstmt.setString(1, "%"+keyword+"%");
	pstmt.setString(2, category);
	pstmt.setInt(3, pageSize * (curPage-1));
	pstmt.setInt(4, pageSize * curPage);
	rs = pstmt.executeQuery();
	
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>非过滤的分类事项</title>
<script type="text/javascript" src="/hp/scripts/jquery.js"></script>
<script type="text/javascript"> 
function MovePage(action) {
	var icur = parseInt($("#curPage").attr("value"));
	var pages = parseInt($("#totalPages").attr("value"));
	var pageSize = parseInt($("#pageSize").attr("value"));
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
	$("#curPage").attr("value", icur);
	$("#pageSize").attr("value", pageSize);
	$("#form1").attr("action", "/hp/admin/serviceitem/notcategoryserviceitemlist.jsp");
	$("#form1").submit();
}

$(function(){
	<%if("addservice".equals(oper)){%>
	$('#categoryserviceitem', parent.document).attr('src','/hp/admin/serviceitem/categoryserviceitemlist.jsp?category=<%=category %>');
	
	<%}%>
});
</script>
</head>
<body>

<form action="/hp/admin/serviceitem/notcategoryserviceitemlist.jsp" method="post"  name="form1" id="form1">
	<input type="hidden" name="category" value="<%=category%>" />
	<input type="text" name="keyword" value="<%=keyword%>" /> <input type="submit" value="查 询"/><input type="submit" value="添 加"/>
	<input type="hidden" name="oper" value="<%=preoper%>" />
	<table border="0" cellspacing="0" cellpadding="0" class="tablelist">
				<tr>
				  <th align="left"><input type="checkbox" name="selsxid"/></th>
				  <th align="left">事项标识</th>
					<th align="center" nowrap="nowrap">事项名称</th>
					<th align="center" nowrap="nowrap">受理部门</th>
				</tr>
				<%
				while(rs.next()){
				%>
				<tr >
					<td align="left"><input type="checkbox" name="sxid" value="<%=rs.getString("ID") %>"/></td>
					<td align="left"><%=rs.getString("ID") %></td>
				  	<td align="center" nowrap="nowrap"><%=rs.getString("SXNAME") %></td>
					<td align="center" nowrap="nowrap"><%=rs.getString("SPSLJGMC") %></td>
				</tr>
				<%
				} %>
			</table>
			
			<div class="fy">
			<input type="hidden" name="totalPages" id="totalPages" value="<%=rscount%pageSize==0?rscount/pageSize:(rscount/pageSize+1) %>"/>
			<input type="hidden" maxlength="2" value="<%=pageSize %>"  name="pageSize" id="pageSize"  style="width:20px;"/>
			<input type="hidden" maxlength="3" value="<%=curPage %>" name="curPage" id="curPage"  style="width:20px;" title="请输入页码并按回车"/>
			【总共<span class="totalrows"><%=rscount %></span>条记录】&nbsp;&nbsp; 
			【页码 <span class="currentpage"><%=curPage %></span>/<span class="totalpages"><%=rscount%pageSize==0?rscount/pageSize:(rscount/pageSize+1) %></span> 】&nbsp;&nbsp; 
			<a onclick="MovePage(-2);">首页</a>&nbsp;
			<a onclick="MovePage(-1);">上一页</a>&nbsp; 
			<a onclick="MovePage(1);">下一页</a>&nbsp; 
			<a onclick="MovePage(2);">末页</a></div>
			
</form>
</body>
</html>
<%
	} catch (Exception ex) {
		DbUtils.rollbackAndClose(conn);
		out.println(ex);
		ex.printStackTrace();
	} finally {
		DbUtils.closeQuietly(conn, pstmt, rs);
	}
%>