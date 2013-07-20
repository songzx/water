$(function() {
	fn_initpaging();
});

// 初始化分页
function fn_initpaging() {
	$('div[class=pager] span[class=firstpage]').css({
		'cursor' : 'pointer'
	});
	$('div[class=pager] span[class=prepage]').css({
		'cursor' : 'pointer'
	});
	$('div[class=pager] span[class=nextpage]').css({
		'cursor' : 'pointer'
	});
	$('div[class=pager] span[class=lastpage]').css({
		'cursor' : 'pointer'
	});
	
	// 首页
	$('div[class=pager] span[class=firstpage]').click(function() {
		$('div[class=pager] *[class=currentpage]').val(1);
	});
	// 前一页
	$('div[class=pager] span[class=prepage]').click(function() {
		var v_currentpage = parseInt($('div[class=pager] *[class=currentpage]').text());
		if (v_currentpage != 1 && v_currentpage > 1) {
			$('div[class=pager] *[class=currentpage]').val(v_currentpage - 1);
		}
	});
	// 下一页
	$('div[class=pager] span[class=nextpage]').click(function() {
		var v_currentpage = parseInt($('div[class=pager] span[class=currentpage]').text());
		var v_lastpage = parseInt($('div[class=pager] span[class=totalpages]').text());
		if (v_currentpage != v_lastpage && v_currentpage < v_lastpage) {
			$('div[class=pager] *[class=currentpage]').val(v_currentpage + 1);
			fn_loadlist();
		}
	});
	// 末页
	$('div[class=pager] span[class=lastpage]').click(function() {
		$('div[class=pager] *[class=currentpage]').val($('div[class=pager] span[class=totalpages]').text());
		fn_loadlist();
	});
}