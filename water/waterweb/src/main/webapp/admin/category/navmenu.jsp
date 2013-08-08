<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.water.metamodel.tree.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
public StringBuffer getcategorytree(List<Category> categorys,String id){
	StringBuffer sb = new StringBuffer("");
	int index = 0;
	
	for(Category category : categorys){
		if(id.equals(category.getParentid())){
			if(index == 0){
				sb.append("<ul>");
				index ++;
			}
			sb.append("<li><a href=\""+(category.getUrl()==null?"javascript:":("/waterweb"+ category.getUrl()))+"\" target=\"navTab\" rel=\""+category.getId()+"\" external=\"true\" >"+category.getName()+"</a>");
			sb.append(getcategorytree(categorys, category.getId()));
			sb.append("</li>");
		}
	}
	if(index > 0){
		sb.append("</ul>");
	}
	return sb;
}
%>
<%
String categoryid = request.getParameter("categoryid");
String categoryname = request.getParameter("categoryname");
List<Category> categorys = request.getAttribute("categorys")==null?new ArrayList<Category>():(List<Category>)request.getAttribute("categorys");


StringBuffer sb = new StringBuffer();

for(Category category : categorys){
	//System.out.println("0".equals(tmpmap.get("PARENTID").toString())+"["+tmpmap.get("PARENTID").toString()+"]");
	
	if(categoryid.equals(category.getParentid())){
		sb.append("<li><a href=\""+(category.getUrl()==null?"javascript:":("/waterweb"+ category.getUrl()))+"\" target=\"navTab\" rel=\""+category.getId()+"\" external=\"true\" >"+category.getName()+"</a>");
		sb.append(getcategorytree(categorys, category.getId()));
		sb.append("</li>");
	}
}
%>
<div id="menu1" class="accordion" fillSpace="sidebar">
		<div class="accordionHeader">
			<h2><span>Folder</span><%=categoryname %></h2>
		</div>
		<div class="accordionContent">
			<ul class="tree treeFolder">
				<%=sb.toString() %>
			</ul>
		</div>
</div>