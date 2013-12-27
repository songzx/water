<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
String json = FileUtils.readFileToString(new File("D:\\coding\\study\\water\\water\\example\\src\\main\\webapp\\json.txt"), "UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/json; charset=UTF-8");
%>