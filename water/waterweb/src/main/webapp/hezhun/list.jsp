<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
</head>
<body>
	<div class="main_bd">
		<input type="hidden" name="workflowstate" value="1" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="datalist" id="tab">
			<tr>
				<th width="5%">序号</th>
				<th width="15%">申报编号</th>
				<th width="10%">申请人姓名</th>
				<th nowrap="nowrap">被会见人姓名</th>
				<th width="10%">申请时间</th>
				<th width="10%">办理时限</th>
				<th width="11%">操作</th>
			</tr>
			<tbody class="tbody">
			</tbody>
		</table>
	</div>
	<div>
		<input type="hidden" name="currentpage" value="1" class="currentpage"/> 
		<input type="hidden" name="pagerows" value="10" class="pagerows"/>

		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td></td>
				<td nowrap>
				【总共<span class="totalrows"></span>条记录】&nbsp;&nbsp;
				【页码 <span class="currentpage"></span>/<span class="totalpages"></span> 】&nbsp;&nbsp;
				<span class="firstpage">首页</span>&nbsp;
				<span class="prepage">上一页</span>&nbsp;
				<span class="nextpage">下一页</span>&nbsp;
				<span class="lastpage">未页</span>&nbsp; 
				
				<select name="selectpage" class="selectpage">
				</select>
				
				</td>
			</tr>
		</table>
	</div>
</body>
</html>