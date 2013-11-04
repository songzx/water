<div>
	<!-- 抛开参数传值，参考电商的做法，使用访问拼接 list_10_1_5_49_desc.html-->
	<input type="hidden" name="currentpage" value="${list.pager['currentpage'] }" />
	<input type="hidden" name="pagerows" value="${list.pager['pagerows'] }" />
	<input type="hidden" name="totalpage" value="${list.pager['totalpage'] }" />
	<input type="hidden" name="totalrows" value="${list.pager['totalrows'] }" />
	
	<a href="">首页</a>
	<a href="">下一页</a>
	<a href="">上一页</a>
	<a href="">末页</a>
</div>