<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String applyId = request.getParameter("applyId")==null?"":request.getParameter("applyId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache" />
<title>新增表单</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.metadata.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/messages_cn.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/validate/screen.css" />
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/dataTableColor.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

	var v_sex = {
		"0" : "男",
		"1" : "女",
		"" : ""
	}
	var v_result = {
			'受理':{"1" : "<img src='../images/yes.jpg' />",'0': "<img src='../images/no.jpg' />"},
			'所领导审核':{"1" : "<img src='../images/yes.jpg' />",'0': "<img src='../images/no.jpg' />"},
			'管教审核':{"1" : "<img src='../images/yes.jpg' />",'0': "<img src='../images/no.jpg' />"}
	}
	$.metadata.setType("attr", "validate");
	$(function() {
		$("#iteminfoform").validate();
		var v_index = 0;
		
		$('div[class=iteminfoform]').load('iteminfoform.jsp',function(){
			v_index++;
			if(v_index == 3){
				fn_load();
			}
		});
		$('div[class=itemform]').load('itemread.jsp',function(){
			v_index++;
			if(v_index == 3){
				fn_load();
			}
		});
		$('div[class=historylist]').load('historylist.jsp', function() {
			v_index++;
			if(v_index == 3){
				fn_load();
			}
		});

		$('div[class=itemform]').hide();
		//$('div[class=historylist]').hide();
		

	});
	function fn_load(){
		$.ajax({
			url : "<%=request.getContextPath()%>/wssl/wsslAction!iteminfo.action",
			type : 'post',
			datatype : 'json',
			data : {
				applyId : $('input[type=hidden][name=applyId]').val(),
				flowstate : '1'
			},
			success : function(data) {
				//审批表单
				//返回按键
				$('input[id=rebutton]').click(function(){
					$('div[class=iframeitem]',parent.document).hide();
					$('div[class=iframeitem]',parent.document).siblings().show();
					parent.fn_loadshoulilist();
				});
				
				//选择意见
				$('select[name=beselect]').click(function(){
					$('textarea[name=advice]').val($('option:selected',this).val());
				});
					
				$.each(data.form, function() {
					$('input[name="wsfwApplyinfo.applyNo"]').val(this.applyNo);
					$('span[class="wsfwApplyinfo.applyNo"]').html(this.applyNo);
					
					$('input[name="wsfwApplyinfo.address"]').val(this.address);
					$('span[class="wsfwApplyinfo.address"]').html(this.address);
					
					//$('input[name="wsfwApplyinfo.projectName"]').val('行政羁押人员网上视频会见');//this.projectName);
					//$('span[class="wsfwApplyinfo.projectName"]').html('行政羁押人员网上视频会见');//this.projectName);
					
					$('input[name="wsfwApplyinfo.unitname"]').val(this.unitname);
					$('span[class="wsfwApplyinfo.unitname"]').html(this.unitname);
	
					$('input[name="wsfwApplyinfo.applicantName"]').val(this.applicantName);
					$('span[class="wsfwApplyinfo.applicantName"]').html(this.applicantName);
	
					$('input[name="wsfwApplyinfo.mobile"]').val(this.mobile);
					$('span[class="wsfwApplyinfo.mobile"]').html(this.mobile);
	
					$('input[name="wsfwApplyinfo.telephone"]').val(this.telephone);
					$('span[class="wsfwApplyinfo.telephone"]').html(this.telephone);
	
					$('input[name="wsfwApplyinfo.email"]').val(this.email);
					$('span[class="wsfwApplyinfo.email"]').html(this.email);
	
					$('span[class="wsfwApplyinfo.sex"]').html(v_sex[this.sex]);
					
					$('input[name="wsfwApplyinfo.idcard"]').val(this.idcard);
					$('span[class="wsfwApplyinfo.idcard"]').html(this.idcard);
	
					$('input[name="wsfwApplyinfo.qq"]').val(this.qq);
					$('span[class="wsfwApplyinfo.qq"]').html(this.qq);
					
					$('input[name="wsfwApplyinfo.acceptCaseNo"]').val(this.acceptCaseNo);
					$('span[class="wsfwApplyinfo.acceptCaseNo"]').html(this.acceptCaseNo);
					
					$('input[name="wsfwWsspApplyinfo.intervieweeName"]').val(this.intervieweeName);
					$('span[class="wsfwWsspApplyinfo.intervieweeName"]').html(this.intervieweeName);		
					
					$('input[name="wsfwWsspApplyinfo.relationship"]').val(this.relationship);
					$('span[class="wsfwWsspApplyinfo.relationship"]').html(this.relationship);	
	
					$('input[name="wsfwWsspApplyinfo.relationshipdesc"]').val(this.relationshipdesc);
					$('span[class="wsfwWsspApplyinfo.relationshipdesc"]').html(this.relationshipdesc);	
					
					$('input[name="wsfwWsspApplyinfo.detention"]').val(this.detention);
					$('span[class="wsfwWsspApplyinfo.detention"]').html(this.detention);	
					
	
					$('input[name="wsfwWsspApplyinfo.punishnumber"]').val(this.punishnumber);
					$('span[class="wsfwWsspApplyinfo.punishnumber"]').html(this.punishnumber);	
					
					$('input[name="wsfwWsspApplyinfo.entouragename"]').val(this.entouragename);
					$('span[class="wsfwWsspApplyinfo.entouragename"]').html(this.entouragename);	
					
					$('input[name="wsfwWsspApplyinfo.entouragename2"]').val(this.entouragename2);
					$('span[class="wsfwWsspApplyinfo.entouragename2"]').html(this.entouragename2);
					
					$('input[name="wsfwWsspApplyinfo.entourageidcard"]').val(this.entourageidcard);
					$('span[class="wsfwWsspApplyinfo.entourageidcard"]').html(this.entourageidcard);	
					
					$('input[name="wsfwWsspApplyinfo.entourageidcard2"]').val(this.entourageidcard2);
					$('span[class="wsfwWsspApplyinfo.entourageidcard2"]').html(this.entourageidcard2);	
					
					
					//$('input[name="wsfwWsspApplyinfo.intime"]').val(this.intime);
					//$('span[class="wsfwWsspApplyinfo.intime"]').html(this.intime);
					
					$('input[name="wsfwWsspApplyinfo.bookdate.time"]').val(this.reservationBookdate.substr(10,18));
					$('span[class="wsfwWsspApplyinfo.bookdate.time"]').html(this.reservationBookdate.substr(10,18));
					
					$('input[name="wsfwWsspApplyinfo.bookdate.date"]').val(this.reservationBookdate.substr(0,10));
					$('span[class="wsfwWsspApplyinfo.bookdate.date"]').html(this.reservationBookdate.substr(0,10));
					
					$('img[name="wsfwWsspApplyinfo.applyPhoto"]').attr('src','../pic.jsp?oper=0&applyId='+this.applyId);
					$('img[name="wsfwWsspApplyinfo.entouragephoto"]').attr('src','../pic.jsp?oper=1&applyId='+this.applyId);
					$('img[name="wsfwWsspApplyinfo.entouragephoto2"]').attr('src','../pic.jsp?oper=2&applyId='+this.applyId);
					//表单
					
					
					
					//日志处理
					var v_tbody = '';
					$.each(this.wsfwWorkinfos,function(){
						v_tbody += '<tr>';
						v_tbody += '<td>'+this.step2+'</td>';
						v_tbody += '<td>'+this.username+'</td>';
						v_tbody += '<td>'+this.workDate+'</td>';
						v_tbody += '<td>'+this.advice+'</td>';
						v_tbody += '<td>'+v_result[this.step2][this.result]+'</td>';
						v_tbody += '</tr>';
					});
					if(v_tbody != ''){
						$('div[class=historylist] tbody[class=historylistbody]').empty();
						$('div[class=historylist] tbody[class=historylistbody]').append(v_tbody);
					}
					
					//添加空格
					$('span').prepend('&nbsp;');
				});
				
				//统计
				$('span[class=count]').append(data.tj[0]);
				$('span[class=successcount]').append(data.tj[1]);
				$('span[class=failecount]').append(data.tj[2]);
				if(data.tj[1]>= 2){
					$('div[class=warn]').show();
					$('div[class=warn]').append('<font color="red">警告: 被会见人会见次数已经超过2次.</font>');
				}
				
				//处理意见
				var v_option = "";
				$.each(data.advices, function() {
					v_option += ('<option value="'+this.CONTENT+'">'+this.TITLE+'</option>');
				});
				$('select[name=beselect]').empty();
				$('select[name=beselect]').append(v_option);
			}
		});
	}
	
	function fn_show(v_cls,v_a) {
		$('div[class="' + v_cls + '"]').show();
		$('div[class="' + v_cls + '"]').siblings().hide();
		$('a[id=m1]').removeClass('btn_tab');
		$('a[id=m2]').removeClass('btn_tab');
		//$('a[id=m3]').removeClass('btn_tab');
		$('a[id='+v_a+']').addClass('btn_tab');
	}
</script>
<style type="text/css">
.itemform {
	margin: 10 0 0 0;
	border: thick 1px #000;
}
</style>
</head>
<body>
	<div class="main_title">
		<span class="main_title_pic"></span>分管民警签署意见
	</div>
	<div>
		
		
		<div class="btn_control">
			<table>
				<tr>
					<td><a id="m1" href="javascript:" onclick="fn_show('twolist','m1')" class="btn_tab" >基本信息</a></td>
					<td><a id="m2" href="javascript:" onclick="fn_show('itemform','m2')" >申请表</a></td>
					<td style="display:none;"><a id="m3" href="javascript:" onclick="fn_show('historylist','m3')">审批记录</a></td>
				</tr>
			</table>
		</div>
		
		<div style="padding: 10px 10px;font-size:14px;color:#0142A6;">被会见人申请了<span class="count"></span>次,成功次数<span class="successcount"></span>,失败次数<span class="failecount"></span>.</div>
		<div style="padding: 2px 2px; font-size: 16px; color: #0142A6;display:none;" class="warn" ></div>
		
		<form action="<%=request.getContextPath()%>/wssl/hezhun/wsslAction!workflow.action" name="iteminfoform" method="post" id="iteminfoform">
			<input type="hidden" name="applyId" value="<%=applyId%>"/>
			<input type="hidden" name="workflowstate" value="1"/>
			<div class="twolist">
			<div class="historylist"></div>
			<div class="iteminfoform"></div>
			</div>
			<div class="itemform"></div>
		</form>
	</div>
</body>
</html>