$(function() {
	$('div[class=searchform]').load('searchform.jsp');
	$('div[class=list]').load('list.jsp', function() {
		fn_loadlist();
		fn_initpaging();
	});
});

// 初始化分页
function fn_initpaging() {
	$('div[class=list] span[class=firstpage]').css({
		'cursor' : 'pointer'
	});
	$('div[class=list] span[class=prepage]').css({
		'cursor' : 'pointer'
	});
	$('div[class=list] span[class=nextpage]').css({
		'cursor' : 'pointer'
	});
	$('div[class=list] span[class=lastpage]').css({
		'cursor' : 'pointer'
	});
	// 首页
	$('div[class=list] span[class=firstpage]').click(function() {
		$('div[class=list] *[class=currentpage]').val(1);
		fn_loadlist();
	});
	// 前一页
	$('div[class=list] span[class=prepage]').click(function() {
		var v_currentpage = parseInt($('div[class=list] *[class=currentpage]').text());
		if (v_currentpage != 1 && v_currentpage > 1) {
			$('div[class=list] *[class=currentpage]').val(v_currentpage - 1);
			fn_loadlist();
		}
	});
	// 下一页
	$('div[class=list] span[class=nextpage]').click(function() {
		var v_currentpage = parseInt($('div[class=list] span[class=currentpage]').text());
		var v_lastpage = parseInt($('div[class=list] span[class=totalpages]').text());
		if (v_currentpage != v_lastpage && v_currentpage < v_lastpage) {
			$('div[class=list] *[class=currentpage]').val(v_currentpage + 1);
			fn_loadlist();
		}
	});
	// 末页
	$('div[class=list] span[class=lastpage]').click(function() {
		$('div[class=list] *[class=currentpage]').val($('div[class=list] span[class=totalpages]').text());
		fn_loadlist();
	});
	// 选择页
	$('div[class=list] select[class=selectpage]').change(function() {
		$('div[class=list] *[class=currentpage]').val($(this).val());
		fn_loadlist();
	});
	// 查询按键
	$('div[class=searchform] input[class=clssubmit]').click(function() {
		$('div[class=list] *[class=currentpage]').val(1);
		fn_loadlist();
	});
}

// 加载列表相关参数
function fn_loadlist() {
	$('div[class=content]').empty();
	$('div[class=content]').append('正在加载中...');

	$.ajax({
		url : "<%=request.getContextPath()%>/wssl/wsslAction!shouliItemList.action",
		type : 'post',
		datatype : 'json',
		data : $("#listform").serialize(),
		success : function(data) {
			// 处理搜索表单
			$('div[class=searchform] input[name="wsfwApplyinfo.applyId"]').val(data.searchform[0].applyId);
			// $('div[class=searchform]
			// input[name="wsfwApplyinfo.acceptCaseNo"]').val(data.searchform[0].acceptCaseNo);
			$('div[class=searchform] input[name="wsfwApplyinfo.applicantName"]').val(data.searchform[0].applicantName);
			$('div[class=searchform] input[name="wsfwApplyinfo.idcard"]').val(data.searchform[0].idcard);

			// 处理列表
			var v_tbody = '';
			$.each(data.root, function(i) {
				v_tbody += '<tr>';
				v_tbody += '<td>' + (i + 1) + '</td>';
				v_tbody += '<td nowrap="nowrap">' + this.applyNo + '</td>';
				v_tbody += '<td>' + this.applicantName + '</td>';
				v_tbody += '<td nowrap="nowrap">' + this.intervieweeName + '</td>';
				v_tbody += '<td nowrap="nowrap">' + this.arrivalTime.substr(0, 10) + '</td>';
				v_tbody += '<td>' + v_remailDate[this.remailDate > 3 ? 4 : this.remailDate] + this.remailDate + '</td>';
				v_tbody += '<td><span class="oper"><a href="' + v_state[this.state][2] + this.applyId + '" target="frmitem">' + v_state[this.state][1] + '</a></span></td>';
				v_tbody += '</tr>';
			});

			$('div[class=list] tbody[class=tbody]').empty();
			$('div[class=list] tbody[class=tbody]').append(v_tbody);

			$('div[class=list] span[class=totalrows]').html(data.totalrows);
			$('div[class=list] span[class=pagerows]').html(data.pagerows);
			$('div[class=list] span[class=currentpage]').html(data.currentpage);
			$('div[class=list] span[class=totalpages]').html(data.totalpages);
		}
	});
}