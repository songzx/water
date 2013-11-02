${item.text }:<select name="${item.name }">
	<option value="">--请选择--</option>
	<#list option as item.options>
	<option value="${option.value }" ${item.selectoption == option.value ? 'selected':'' }>${option.text }</option>
	</#list>
</select>