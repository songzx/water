<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String accountid = "4EE9C8F0F12E11E2BF63E4517F000001";//(String)session.getAttribute("accountid");
String parentid = request.getParameter("parentid") == null ? "":request.getParameter("parentid");
if("".equals(parentid)){
	out.println("不存在目录树");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类树</title>
<script type="text/javascript" src="/waterweb/jstree/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="/waterweb/jstree/!style.css" />
<script type="text/javascript" src="/waterweb/jstree/jquery.jstree.js"></script>
<script type="text/javascript">
	$(function() {
		var v_jstree_url = "/waterweb/admin/category/tmp/"+$('input[type=hidden][name=accountid]').val()+"_"+$('input[type=hidden][name=parentid]').val()+".xml";
		$("#category").jstree({
			"xml_data" : {
				"ajax" : {
					"url" : v_jstree_url
				},
				"xsl" : "nest"
			},
			"plugins" : [
					"themes", "xml_data", "contextmenu"
			],
			"contextmenu": {
				"items": {
					"create": null,
					"rename": null,
					"remove": null,
					"ccp": null
				}
			}
		}).bind("click.jstree", function (event) {
			var eventNodeName = event.target.nodeName;               
            if (eventNodeName == 'INS') {                   
                return;               
            } else if (eventNodeName == 'A') {                   
                var $subject = $(event.target).parent();                   
                if ($subject.find('ul').length > 0) {            
                } else { 
                  //选择的id值
                  // alert($(event.target).parents('li').eq(0).attr("url")); 
                  if(!$(event.target).parents('li').eq(0).attr("href")){
                	  return;
                  }
				  $('#content', parent.document).attr('src',$(event.target).parents('li').eq(0).attr("href")+"?category="+$(event.target).parents('li').attr("id")+"&categoryname="+encodeURI($.trim($(event.target).parents('li').eq(0).text())));
                }               
            }           
		});
	});

</script>
</head>
<body>
	<input type="hidden" name="accountid" value="<%=accountid %>"/>
	<input type="hidden" name="parentid" value="<%=parentid %>"/>
	<div id="category"></div>
</body>
</html>