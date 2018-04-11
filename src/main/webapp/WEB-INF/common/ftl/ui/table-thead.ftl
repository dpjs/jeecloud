<#--
表格头标签：用于显示列表头按钮。
	colspan：横跨行数。
-->
<#macro thead value colspan="2" sellectAll="" thClass="" operator="">
<thead><tr>
	<#if sellectAll != ""> <th lay-data="{checkbox:true, fixed: true}"></th></#if>
	<#list value as row>
	  <#if row.showType == '0'>
		<th <#if thClass!=""> class="${thClass}"</#if> lay-data="{field:'${row.value!''}', width:80}">${row.name!''}</th>
	  </#if>
	</#list>
	<#if operator!="">
		<th <#if thClass!=""> class="${thClass}"</#if> lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#operatorBar'}">操作</th>
	</#if>
</tr></thead>
</#macro>