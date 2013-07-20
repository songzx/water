<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增表单</title>
<style type="text/css">
.block { display: block; }
form label.error { display: none; }
</style>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="datalist" id="a">
		<tr>
			<td width="178">受理事项：</td>
			<td width="560" style="text-align: left;">
			<span>行政羁押人员网上视频会见</span>
			</td>
			<td width="99" >申报编号：</td>
			<td width="402" style="text-align: left;"><span class="wsfwApplyinfo.applyNo"></span></td>
		</tr>
		<tr style="display:none;">
			<td >受理编号：</td>
			<td style="text-align: left;" colspan="3"><span class="wsfwApplyinfo.acceptCaseNo" ></span></td>
		</tr>
		<tr>
			<td nowrap="nowrap">申请人或单位：</td>
			<td width="296" style="text-align: left;">
			<span class="wsfwApplyinfo.unitname" ></span>
			</td>
			<td nowrap="nowrap">申请人姓名：</td>
			<td width="648" style="text-align: left;"><span class="wsfwApplyinfo.applicantName" ></span></td>
		</tr>
		<tr>
			<td>申请人手机：</td>
			<td style="text-align: left;">
			<span class="wsfwApplyinfo.telephone" ></span></td>
			<td nowrap="nowrap">申请人固定电话：</td>
			<td style="text-align: left;">
			<span class="wsfwApplyinfo.mobile" ></span></td>
		</tr>
		<tr>
			<td>申请人邮箱：</td>
			<td style="text-align: left;">
			<span class="wsfwApplyinfo.email" ></span></td>
			<td style="text-align: left;"><div align="center">联系地址：</div></td>
			<td style="text-align: left;"><span class="wsfwApplyinfo.address" ></span></td>
		</tr>
		<tr>
			<td>被会见人姓名：</td>
			<td style="text-align: left;"><span class="wsfwWsspApplyinfo.intervieweeName"  /></td>
			<td style="text-align: left;" nowrap="nowrap">《处罚决定书》编号：</td>
			<td style="text-align: left;"><span class="wsfwWsspApplyinfo.punishnumber"  /></td>
		</tr>
		<tr>
			<td width="156">审核结果：</td>
			<td colspan="3" style="text-align: left;">
			<input type="radio" name="result" value="1" validate="required:true"/>通过&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="result" value="0" validate="required:true"/>不通过<br />
			<label for="result" class="error">请选择审核结果</label>
			</td>
		</tr>
		<tr>
			<td>审核意见：</td>
			<td colspan="3" style="text-align: left;"><textarea name="advice" cols="80" rows="5" validate="required:true"></textarea>&nbsp;<select name="beselect" multiple="multiple" style="width:300px; height: 60px;">
				<option>同意受理</option>
				<option>不同意受理</option>
				<option>同意受理</option>
				<option>同意受理</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="4">
				<div align="center">
					<input type="submit" class="btn_2k3" value="提交"  /> <input type="button" value="返回" class="btn_2k3" name="rebutton" id="rebutton" />
				</div>
			</td>
		</tr>
	</table>
</body>
</html>