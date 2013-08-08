<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<%!
public StringBuffer getcategorytree(List<Map<String, Object>> result,String id){
	StringBuffer sb = new StringBuffer("");
	for(Map<String,Object> tmpmap : result){
		if(id.equals(tmpmap.get("PARENTID").toString().trim())){
			sb.append("<item id=\""+tmpmap.get("ID").toString().trim()+"\">");
			sb.append("<content><name><![CDATA["+tmpmap.get("NAME").toString().trim()+"]]></name></content>");
			sb.append("<icourl><![CDATA["+tmpmap.get("ICOURL")==null?"":tmpmap.get("ICOURL").toString().trim()+"]]></icourl>");
			sb.append(getcategorytree(result, tmpmap.get("ID").toString().trim()));
			sb.append("</item>");
		}
	}
	return sb;
}

public Map<String,String> getFiles(File tmpfile,Map<String,String> result){
		if(tmpfile.isDirectory()){
			for(File file : tmpfile.listFiles()){
				result = getFiles(file,result);
			}
		}else{
			String tstr =result.get(tmpfile.getParentFile().getName()) == null ? "" : (result.get(tmpfile.getParentFile().getName()) +"&parm");
			result.put(tmpfile.getParentFile().getName(),tstr +tmpfile.getName()+"&parm"+  tmpfile.getAbsolutePath().replace("/hp/server/webapp/gsmpro/", "/gsmpro/"));
			//System.out.println(tmpfile.getParentFile().getName()+" | "+result.get(tmpfile.getParentFile().getName()) );
		}
		return result;
	}
%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String oper = request.getParameter("oper") == null ? "" : request.getParameter("oper");
String preoper = request.getParameter("preoper") == null ? "add" : request.getParameter("preoper");
try {
	conn = DatasourceUtil.getInstance("/hp/server/webapp/gsmpro/WEB-INF/resourceconfig/datasource.properties").getDataSource("TMPWBSB").getConnection();
	
	String code = request.getParameter("code") == null ? "":request.getParameter("code") ;
	String name = request.getParameter("name") == null ? "":request.getParameter("name") ;
	int sortnum = ValidateUtil.getInstance().checkInt(request.getParameter("sortnum") , 1);
	String available = request.getParameter("available") == null ? "1":request.getParameter("available") ;
	String parentid = request.getParameter("parentid") == null ? "0":request.getParameter("parentid") ;
	String id = request.getParameter("id") == null ? "0":request.getParameter("id") ;
	String icourl = request.getParameter("icourl") == null ? "/gsmpro/serviceitem/images/bsqy_jy/bsqy_jy_1.jpg":request.getParameter("icourl") ;

	if("add".equals(oper)){
		pstmt = conn.prepareStatement("insert into tb_wsmp_category(id,code,name,sortnum,available,parentid,icourl) values(?,?,?,?,?,?,?)");
		pstmt.setString(1, UUID.randomUUID().toString().replace("-", ""));
		pstmt.setString(2, code);
		pstmt.setString(3, name);
		pstmt.setInt(4, sortnum);
		pstmt.setString(5, available);
		pstmt.setString(6, parentid);
		pstmt.setString(7, icourl);
		pstmt.executeUpdate();
		
		
		MapListHandler mapListHandler = new MapListHandler(new MyRowProcessor());
		StringBuffer sb = new StringBuffer();
		pstmt = conn.prepareStatement("select * from tb_wsmp_category where available = '1'  order by sortnum asc");
		rs = pstmt.executeQuery();
		List<Map<String,Object>> result = mapListHandler.handle(rs);
		sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<root>");
		for(Map<String,Object> tmpmap : result){
			//System.out.println("0".equals(tmpmap.get("PARENTID").toString())+"["+tmpmap.get("PARENTID").toString()+"]");
			if("0".equals(tmpmap.get("PARENTID").toString().trim())){
				sb.append("<item id=\""+tmpmap.get("ID").toString().trim()+"\">");
				sb.append("<content><name><![CDATA["+tmpmap.get("NAME").toString().trim()+"]]></name></content>");
				sb.append("<icourl><![CDATA["+tmpmap.get("ICOURL")==null?"":tmpmap.get("ICOURL").toString().trim()+"]]></icourl>");
				sb.append(getcategorytree(result, tmpmap.get("ID").toString().trim()));
				sb.append("</item>");
			}
		}
		sb.append("</root>");
		OutputStream output = new FileOutputStream("/hp/server/webapp/gsmpro/serviceitem/data/category/category.xml");
		IOUtils.write(sb.toString(), output);

		out.println("<script>alert('添加分类成功。');location.href='/gsmpro/admin/serviceitem/category.jsp?parentid="+parentid+"';</script>");
		return;
	}else if("del".equals(oper)){
		pstmt = conn.prepareStatement("delete from tb_wsmp_category where id = ?");
		pstmt.setString(1, id);
		pstmt.executeUpdate();

		MapListHandler mapListHandler = new MapListHandler(new MyRowProcessor());
		StringBuffer sb = new StringBuffer();
		pstmt = conn.prepareStatement("select * from tb_wsmp_category where available = '1'  order by sortnum asc");
		rs = pstmt.executeQuery();
		List<Map<String,Object>> result = mapListHandler.handle(rs);
		sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<root>");
		for(Map<String,Object> tmpmap : result){
			//System.out.println("0".equals(tmpmap.get("PARENTID").toString())+"["+tmpmap.get("PARENTID").toString()+"]");
			if("0".equals(tmpmap.get("PARENTID").toString().trim())){
				sb.append("<item id=\""+tmpmap.get("ID").toString().trim()+"\">");
				sb.append("<content><name><![CDATA["+tmpmap.get("NAME").toString().trim()+"]]></name></content>");
				sb.append("<icourl><![CDATA["+tmpmap.get("ICOURL")==null?"":tmpmap.get("ICOURL").toString().trim()+"]]></icourl>");
				sb.append(getcategorytree(result, tmpmap.get("ID").toString().trim()));
				sb.append("</item>");
			}
		}
		sb.append("</root>");
		OutputStream output = new FileOutputStream("/hp/server/webapp/gsmpro/serviceitem/data/category/category.xml");
		IOUtils.write(sb.toString(), output);

		out.println("<script>location.href='/gsmpro/admin/serviceitem/lefttree.jsp';</script>");
		return;
	}
	
	if("query".equals(preoper) && !"".equals(id)){
		pstmt = conn.prepareStatement("SELECT * FROM TB_WSMP_CATEGORY WHERE ID = ? ");
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			code = rs.getString("code");
			name = rs.getString("name");
			sortnum = rs.getInt("sortnum");
			available = rs.getString("available");
			parentid = rs.getString("parentid");
			icourl = rs.getString("icourl");
		}
		preoper = "update";
	}else if("update".equals(oper) && !"".equals(id)){
		pstmt = conn.prepareStatement("update TB_WSMP_CATEGORY set CODE = ? ,NAME = ? ,SORTNUM = ? ,AVAILABLE = ? ,PARENTID = ?,icourl = ? where id = ?");
		pstmt.setString(1, code);
		pstmt.setString(2, name);
		pstmt.setInt(3, sortnum);
		pstmt.setString(4, available);
		pstmt.setString(5, parentid);
		pstmt.setString(6, icourl);
		pstmt.setString(7, id);
		pstmt.executeUpdate();

		MapListHandler mapListHandler = new MapListHandler(new MyRowProcessor());
		StringBuffer sb = new StringBuffer();
		pstmt = conn.prepareStatement("select * from tb_wsmp_category where available = '1'  order by sortnum asc");
		rs = pstmt.executeQuery();
		List<Map<String,Object>> result = mapListHandler.handle(rs);
		sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<root>");
		for(Map<String,Object> tmpmap : result){
			//System.out.println("0".equals(tmpmap.get("PARENTID").toString())+"["+tmpmap.get("PARENTID").toString()+"]");
			if("0".equals(tmpmap.get("PARENTID").toString().trim())){
				sb.append("<item id=\""+tmpmap.get("ID").toString().trim()+"\">");
				sb.append("<content><name><![CDATA["+tmpmap.get("NAME").toString().trim()+"]]></name></content>");
				sb.append("<icourl><![CDATA["+tmpmap.get("ICOURL")==null?"":tmpmap.get("ICOURL").toString().trim()+"]]></icourl>");
				sb.append(getcategorytree(result, tmpmap.get("ID").toString().trim()));
				sb.append("</item>");
			}
		}
		sb.append("</root>");
		OutputStream output = new FileOutputStream("/hp/server/webapp/gsmpro/serviceitem/data/category/category.xml");
		IOUtils.write(sb.toString(), output);

		out.println("<script>alert('修改分类成功。');location.href='/gsmpro/admin/serviceitem/category.jsp?parentid="+parentid+"';</script>");
		return;
	}
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类增改</title>
<link type="text/css" rel="stylesheet" href="/gsmpro/css/base.css" />
<link type="text/css" rel="stylesheet" href="/gsmpro/css/main.css" />
<script src="/gsmpro/script/jquery-1.3.2.min.js" language="JavaScript" type="text/javascript"></script>
<script src="/gsmpro/script/menu.js" language="JavaScript" type="text/javascript"></script>
<script type="text/javascript" src="/gsmpro/script/btn.js"></script>
<script type="text/javascript">
$(function(){
	$('td[id=selico] img').click(function(){
		$('img[name=icourl]').attr('src',$(this).attr('src'));
		$('input[type=hidden][name=icourl]').val($(this).attr('src'));
	});
});

</script>
</head>
<body>
<div class="mtitle">
		<div class="ttleft"></div>
		<div class="ttright"></div>
		<div class="tdq">
			当前位置：网办&gt;管理分类列表&gt;管理分类

		</div>
		<div class="ttcon">
			<div class="icon">
				<img src="/gsmpro/images/icons/new_user_large.gif" width="32" height="32" />
			</div>
			<div class="font">
				<h1>管理分类</h1>
				<h4>&nbsp;</h4>
			</div>
		</div>
	</div>
	
	<div class="mbox_mline"></div>
	<div class="mbox">
		<div class="mpart01">
			<div class="tleftpart"></div>
			<div class="trightpart"></div>

			
			
	<form action="/gsmpro/admin/serviceitem/category.jsp" method="post">
<input type="hidden" name="oper" value="<%=preoper%>"/>
<input type="hidden" name="parentid" value="<%=parentid%>"/>
<input type="hidden" name="id" value="<%=id%>"/>
<table class="tlist">
		<tr>
			<td width="11%" align="right">分类编号<span class="red">*</span></td>
			<td width="38%"><input type="text" name="code"
				value="<%=code%>" class="input-text" style="width:250px;"/></td>
		</tr>
		<tr>
			<td align="right">分类名称<span class="red">*</span></td>
			<td><input type="text" name="name"
				value="<%=name%>"  style="width:250px;"/></td>
		</tr>
		<tr>
			<td align="right">是否有效</td>
			<td>
			<input name="available" type="radio" value="1" <%="1".equals(available)?"checked":"" %>>有 效 
			<input type="radio" name="available" value="0"  <%="0".equals(available)?"checked":"" %>>无 效</td>
		</tr>
		
		<tr>
			<td align="right">排序序号<span class="red">*</span></td>
			<td><input type="text" name="sortnum"	value="<%=sortnum%>" style="width:250px;"/></td>
		</tr>
		<tr>
			<td align="right">图片<span class="red">*</span></td>
			<td><input type="hidden" name="icourl"	value="<%=icourl%>" style="width:250px;"/><img src="<%=icourl%>" width="29" name="icourl" height="29" /></td>
		</tr>
		<tr>
			<td colspan="2" id="selico">
			<%
//------------------------读取图片－－－－－－－－－－－－－－－－－－－－－－－－
		File tfile = new File("/hp/server/webapp/gsmpro/serviceitem/data/申请表格");
		Map<String,String> result2 = new HashMap<String,String>();
		for(File sfile : tfile.listFiles()){
			result2 = getFiles(sfile,result2);
		}
		for(Iterator<String> sit = result2.keySet().iterator(); sit.hasNext();){
			String key = sit.next();
			out.println(key+"<hr/>");
			String []vals = result2.get(key).split("&parm");
			for(int i = 0; i < vals.length / 2 ; i++){
				out.println("<img src=\"" +  vals[i * 2 + 1] + "\" width=\"10px\" height=\"10px\" />");
			}
		}
		
		//------------------------读取图片END－－－－－－－－－－－－－－－－－－－－－－－－
			%>
			</td>
		</tr>
</table>
<div style="text-align:center;">
<input type="submit" value="提 交" class="btn2" />					
</div>
</form>
			
			
			
			<div class="mpart_bot">
				<div class="leftpart"></div>
				<div class="rightpart"></div>
			</div>
		</div>
	</div>
	<div class="rtop">
		<div class="r4"></div>
		<div class="r3"></div>
		<div class="r2"></div>
		<div class="r1"></div>
		<div class="r0"></div>
	</div>



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