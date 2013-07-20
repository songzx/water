<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增表单</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="datalist">
		<div style="text-align: center; font-size: 20px; font-weight: bold;">
			广州公安局监管场所<br /> 网上视频会见被监管人员预约申请表
		</div>

		<div>
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td>申请人姓名<font color="#FF0000">*</font></td>
					<td><span class="wsfwApplyinfo.applicantName"></span></td>
					<td>性别</td>
					<td><span class="wsfwApplyinfo.sex"></span></td>
					<td rowspan="2"><img src="" width="100px" height="120px" name="wsfwWsspApplyinfo.applyPhoto"/></td>
				</tr>
				<tr>
					<td>申请人身份证号码</td>
					<td colspan="3"><span class="wsfwApplyinfo.idcard"></span></span></td>
				</tr>
				<tr>
					<td>申请人现住地址</td>
					<td colspan="4"><span class="wsfwApplyinfo.address"></span></td>
				</tr>
				<tr>
					<td>申请人单位</td>
					<td colspan="4"><span class="wsfwApplyinfo.unitname"></span></td>
				</tr>
				<tr>
					<td rowspan="2">申请人联系方式</td>
					<td>QQ号</td>
					<td><span class="wsfwApplyinfo.qq"></span></td>
					<td>Email</td>
					<td><span class="wsfwApplyinfo.email"></span></td>
				</tr>
				<tr>
					<td>手机</td>
					<td><span class="wsfwApplyinfo.telephone"></span></td>
					<td>固定电话</td>
					<td><span class="wsfwApplyinfo.mobile"></span></td>
				</tr>
				<tr>
					<td>预约时间</td>
					<td colspan="2"><span class="wsfwWsspApplyinfo.bookdate.time"></span></td>
					<td>预约日期</td>
					<td><span class="wsfwWsspApplyinfo.bookdate.date"></span></td>
				</tr>
				<tr>
					<td>被会见人姓名</td>
					<td><span class="wsfwWsspApplyinfo.intervieweeName"></span></td>
					<td colspan="2">申请人与被会见人的关系</td>
					<td><span class="wsfwWsspApplyinfo.relationship"></span> <span class="wsfwWsspApplyinfo.relationshipdesc"></span></td>
				</tr>
				<tr>
					<td>《处罚决定书》编号</td>
					<td><span class="wsfwWsspApplyinfo.punishnumber"></span></td>
					<td colspan="2">监所名称</td>
					<td><span class="wsfwWsspApplyinfo.detention"></span></td>
				</tr>
				<tr>
						<td align="right">随同人员信息</td>
						<td colspan="4">
							<table cellpadding="0" cellspacing="0" border="0" align="center" width="100%" style="border-collapse: collapse;">
								<tr>
									<td>姓名:</td>
									<td><span class="wsfwWsspApplyinfo.entouragename"></span></td>
									<td>姓名:</td>
									<td><span class="wsfwWsspApplyinfo.entouragename2"></span></td>
								</tr>
								<tr>
									<td>身份证号码:</td>
									<td><span class="wsfwWsspApplyinfo.entourageidcard"></span></td>
									<td>身份证号码:</td>
									<td><span class="wsfwWsspApplyinfo.entourageidcard2"></span></td>
								</tr>
								<tr>
									<td colspan="2"><img src="" width="100px" height="120px" name="wsfwWsspApplyinfo.entouragephoto"/></td>
									<td colspan="2"><img src="" width="100px" height="120px" name="wsfwWsspApplyinfo.entouragephoto2"/></td>
								</tr>
							</table>
						</td>
					</tr>
				<tr>
					<td>备注</td>
					<td colspan="4">
						<ol>
							<li>带 * 的为必填项目。</li>
							<li>一张申请表只能填写一个申请人。</li>
							<li>被会见人姓名身份证上的填写；所在的监所请填写全称。</li>
							<li>不如实填写相关信息的一律不予受理。</li>
						</ol>
					</td>
				</tr>
			</table>
			<p>
				说明：（1）性别：男、女<br /> （2）预约时间：下拉选择，每次会见默认为30分钟一个时间段,具体时间段后台可以由监管人员配置。如果预约名额已满，选择的时间段显示为灰色（不可选择状态）。<br /> （3）申请人与被会见人的关系为：近亲属、监护人员、单位负责人。

			</p>
		</div>
	</div>
</body>
</html>