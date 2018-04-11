<#--
表格标签：用于显示列表数据。
	value：列表数据，可以是Pagination也可以是List。
	class：table的class样式。默认"pn-ltable"。
	sytle：table的style样式。默认""。
	width：表格的宽度。默认100%。
-->
<#macro tableb class="" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<table<#rt/>
<#if class!=""> class="${class}"</#if><#rt/>
<#if style!=""> style="${style}"</#if><#rt/>
 width="${width}" cellspacing="${cellspacing}" cellpadding="${cellpadding}" border="${border}">
	<#nested/>
</table>
</#macro>