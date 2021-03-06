<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		var v_jstree_url = "/waterweb/admin/category/tmp/category.xml";
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
					"create": {
						"label": "添加菜单",
						"action": function (obj) { 
							$('#content', parent.document).load("/waterweb/admin/category/add.jsp?parentid="+obj.attr("id"));
						}
					},
					"rename": {
						"label": "编辑菜单",
						"action": function (obj) { 
							$('#content', parent.document).load("/waterweb/admin/category/modify.jsp?parentid="+obj.attr("id"));
						}
					},
					"remove": {
						"label": "删除菜单",
						"action": function (obj) { 
							if(confirm("是否删除，若删除上级则会递归删除该数据")){
								$('#content', parent.document).load("/waterweb/admin/category/list.jsp?parentid="+obj.attr("id"));
							}
						}
					},
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
		}).bind("loaded.jstree", function (e, data) { 
            data.inst.open_all(-1); // -1 打开所有节点 
        });
	});

</script>
</head>
<body>
	<div id="category"></div>
</body>
</html>