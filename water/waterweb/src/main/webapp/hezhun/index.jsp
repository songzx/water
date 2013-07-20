<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分管民警签署意见</title>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/dataTableColor.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('div[class=searchform]').load('searchform.jsp');
		$('div[class=list]').load('list.jsp', function() {
			fn_loadshoulilist();
			fn_initpaging();
		});
	});

	//初始化分页
	function fn_initpaging(){
		$('div[class=list] span[class=firstpage]').css({'cursor':'pointer'});
		$('div[class=list] span[class=prepage]').css({'cursor':'pointer'});
		$('div[class=list] span[class=nextpage]').css({'cursor':'pointer'});
		$('div[class=list] span[class=lastpage]').css({'cursor':'pointer'});
		//首页
		$('div[class=list] span[class=firstpage]').click(function(){
			$('div[class=list] *[class=currentpage]').val(1);
			fn_loadshoulilist();
		});
		//前一页
		$('div[class=list] span[class=prepage]').click(function(){
			var v_currentpage = parseInt($('div[class=list] *[class=currentpage]').text());
			if(v_currentpage != 1 && v_currentpage > 1){
				$('div[class=list] *[class=currentpage]').val(v_currentpage-1);
				fn_loadshoulilist();
			}
		});
		//下一页
		$('div[class=list] span[class=nextpage]').click(function(){
			var v_currentpage = parseInt($('div[class=list] span[class=currentpage]').text());
			var v_lastpage = parseInt($('div[class=list] span[class=totalpages]').text());
			if(v_currentpage != v_lastpage && v_currentpage < v_lastpage){
				$('div[class=list] *[class=currentpage]').val(v_currentpage+1);
				fn_loadshoulilist();
			}
		});
		//末页
		$('div[class=list] span[class=lastpage]').click(function(){
			$('div[class=list] *[class=currentpage]').val($('div[class=list] span[class=totalpages]').text());
			fn_loadshoulilist();
		});
		//选择页
		$('div[class=list] select[class=selectpage]').change(function(){
			$('div[class=list] *[class=currentpage]').val($(this).val());
			fn_loadshoulilist();
		});
		//查询按键
		$('div[class=searchform] input[class=clssubmit]').click(function(){
			$('div[class=list] *[class=currentpage]').val(1);
			fn_loadshoulilist();
		});
	}
	
	//加载列表相关参数
	function fn_loadshoulilist() {
		$('div[class=list] tbody[class=tbody]').empty();
		$('div[class=list] tbody[class=tbody]').append('<tr><td colspan="10">正在加载中...</td></tr>');
		
		var v_state = {
				'0' : [ '待受理', '受理', '' ],
				'1' : [ '待审核', '审核', 'form.jsp?applyId=' ],
				'2' : [ '待批准', '批准', '' ],
				'9' : [ '已办结', '查看', '' ],
				'' : [ '已办结', '查看', '' ]
			};
		var v_remailDate = {
				'0':'<img src="/gzga/images/1.jpg" width="15" height="15" />&nbsp;',	
				'1':'<img src="/gzga/images/1.jpg" width="15" height="15" />&nbsp;',	
				'2':'<img src="/gzga/images/2.jpg" width="15" height="15" />&nbsp;',	
				'3':'<img src="/gzga/images/3.jpg" width="15" height="15" />&nbsp;',
				'4':'<img src="/gzga/images/3.jpg" width="15" height="15" />&nbsp;'
			};
		$.ajax({
			url : "<%=request.getContextPath()%>/wssl/wsslAction!shouliItemList.action",
			type : 'post',
			datatype : 'json',
			data : $("#indexform").serialize(),
			success : function(data) {
				//处理搜索表单
				$('div[class=searchform] input[name="wsfwApplyinfo.applyId"]').val(data.searchform[0].applyId);
				//$('div[class=searchform] input[name="wsfwApplyinfo.acceptCaseNo"]').val(data.searchform[0].acceptCaseNo);
				$('div[class=searchform] input[name="wsfwApplyinfo.applicantName"]').val(data.searchform[0].applicantName);
				$('div[class=searchform] input[name="wsfwApplyinfo.idcard"]').val(data.searchform[0].idcard);

				//处理列表
				var v_tbody = '';
				$.each(data.root, function(i) {
					v_tbody += '<tr>';
					v_tbody += '<td>' + (i + 1) + '</td>';
					v_tbody += '<td nowrap="nowrap">' + this.applyNo + '</td>';
					//v_tbody += '<td nowrap="nowrap">' + this.acceptCaseNo + '</td>';
					v_tbody += '<td>' + this.applicantName + '</td>';
					v_tbody += '<td nowrap="nowrap">' + this.intervieweeName + '</td>';
					v_tbody += '<td  nowrap="nowrap">' + this.arrivalTime.substr(0,10) + '</td>';
					v_tbody += '<td>' + v_remailDate[this.remailDate > 3 ? 4 : this.remailDate]+this.remailDate + '</td>';
					v_tbody += '<td><span class="oper"><a href="'+v_state[this.state][2]+this.applyId+'" target="frmitem">' + v_state[this.state][1] + '</a></span></td>';
					v_tbody += '</tr>';
				});
				
				$('div[class=list] tbody[class=tbody]').empty();
				$('div[class=list] tbody[class=tbody]').append(v_tbody);
				
				$('div[class=list] span[class=totalrows]').html(data.totalrows);
				$('div[class=list] span[class=pagerows]').html(data.pagerows);
				$('div[class=list] span[class=currentpage]').html(data.currentpage);
				$('div[class=list] span[class=totalpages]').html(data.totalpages);
				var v_option = "";
				for(var v_i = 1; v_i <= data.totalpages; v_i++ ){
					var v_selected = data.currentpage == v_i ? 'selected' :'';
					v_option += '<option value="'+v_i+'" '+v_selected+'>'+v_i+'</option>';
				}
				$('div[class=list] select[class=selectpage]').empty();
				$('div[class=list] select[class=selectpage]').append(v_option);
				//$('div[class=list] select[class=selectpage] option[value='+data.currentpage+']').attr('selected',true);
				

				$('span[class=oper]').click(function(){
					$('div[class=iframeitem]').show();
					$('div[class=iframeitem]').siblings().hide();
				});
			}
		});
	}
</script>
</head>
<body>
	<div>
	<div class="main_title" style="cursor: pointer;">
		<span class="main_title_pic"></span>分管民警签署意见
	</div>
	<form action="<%=request.getContextPath()%>/wssl/wsslAction!shouliItemList.action" method="post" id="indexform">
		<div class="searchform"></div>
		
		<div class="main_title">
			<label></label>
		</div>
		<div class="list"></div>
	</form>
	</div>
	
	<div class="iframeitem" style="display:none;">
		<iframe src="" width="100%" height="100px" name="frmitem" id="frmitem" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe>
		    <script type="text/javascript">
	    function reinitIframe(){
	    var iframe = document.getElementById("frmitem");
	    try{
	    var bHeight = iframe.contentWindow.document.body.scrollHeight;
	    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
	    var height = Math.max(bHeight, dHeight);
	    iframe.height =  height;
	    }catch (ex){}
	    }
	    window.setInterval("reinitIframe()", 200);
	    
	    /* $('#frmitem').load(function(data){
	    	alert($(this).contents().text());
	    	
	    	alert($(this.document).find('input[name=rebutton]').attr('name'));
	    	
	    	$('input[name=rebutton]').click(function(){
	    		$('div[class=iframeitem]').hide();
				$('div[class=iframeitem]').siblings().show();
	    	});
	    });*/
    </script>
	</div>
</body>
</html>