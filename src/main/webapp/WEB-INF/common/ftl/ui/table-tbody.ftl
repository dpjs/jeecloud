<#--
表格体标签：用于显示列表体数据。
	value：列表数据，可以是Pagination也可以是List。
	class：table的class样式。默认"pn-ltable"。
	sytle：table的style样式。默认""。
	width：表格的宽度。默认100%。
-->
<#macro tbody value colspan="" class="" style="" trClass="" trOndblclick="">
	<#if value?is_sequence>
		<#local pageList=value/>
	<#else>
		<#local pageList=value.list/>
	</#if>
	<tbody<#if class!=""> class="${class}"</#if>>
		<#if pageList?size gt 0>
			<#list pageList as row>
				<tr<#rt/>
				<#if trClass != "">class="${trClass}"</#if>
				<#if trOndblclick!="">ondblclick=""</#if>>
				<#nested row row_index/>
				</tr>
			</#list>
		<#else>
			<tr>
			<td align="center" colspan="${colspan}">暂无记录！</td>
			</tr>
		</#if>
	</tbody>
</#macro>