<#--
<input type="?"/>
-->
<#macro input
	type="" maxlength="" readonly="" value="" placeholder="" onafterpaste="" autocomplete="off"
	label="" noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon=":" hasColon="true"
	id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey="" pattern=""
	vld="" equalTo="" maxlength="" minlength="" max="" min="" rname="" rvalue="" layVerify=""
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
	>
<input type="${type}"<#rt/>
<#if id!=""> id="${id}"</#if><#rt/>
<#if autocomplete!=""> autocomplete="${autocomplete}"</#if><#rt/>
<#if layVerify!=""> lay-verify="${layVerify}"</#if><#rt/>
<#if placeholder!=""> placeholder="${placeholder}"</#if><#rt/>
<#if onafterpaste!=""> onafterpaste="${onafterpaste}"</#if><#rt/>
<#if maxlength!=""> maxlength="${maxlength}"</#if><#rt/>
<#if max?string!=""> max="${max}"</#if><#rt/>
<#if min?string!=""> min="${min}"</#if><#rt/>
<#if readonly!=""> readonly="${readonly}"</#if><#rt/>
<#if rname!=""> rname="${rname}"</#if><#rt/>
<#if pattern!=""> pattern="${pattern}"</#if><#rt/>
<#if rvalue!=""> rvalue="${rvalue}"</#if><#rt/>
<#if title?? && title?string!=""> title="${title}"</#if><#rt/>
<#if value?? && value?string!=""> value="${value?html}"</#if><#rt/>
<#if required!=""> required="${required}"</#if><#rt/>
<#include "common-attributes.ftl"/><#rt/>
<#include "scripting-events.ftl"/><#rt/>
/><#rt/>
</#macro>
