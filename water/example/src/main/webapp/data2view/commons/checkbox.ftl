<#list checkbox as item.checkboxs>
<input type="checkbox" name="${item.name}" value="${checkbox.value }" ${item.selectcheckbox == checkbox.value ? 'checked':'' }/>${checkbox.text }
</#list>