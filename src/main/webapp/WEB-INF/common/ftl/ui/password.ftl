<#--
<input type="password"/>
-->
<#macro password
	maxlength="" readonly="" value="" autocomplete="off" placeholder=""
	label="" noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon=":" hasColon="true"
	id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
	vld="" equalTo="" maxlength="" minlength=""
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
	>
<input type="password" autocomplete="${autocomplete}"<#rt/>
<#if id!=""> id="${id}"</#if><#rt/>
<#if placeholder!=""> placeholder="${placeholder}"</#if><#rt/>
<#if required!=""> required="${required}"</#if><#rt/>
<#if maxlength!=""> maxlength="${maxlength}"</#if><#rt/>
<#if readonly!=""> readonly="${readonly}"</#if><#rt/>
<#if value!=""> value="${value}"</#if><#rt/>
<#include "common-attributes.ftl"/><#rt/>
<#include "scripting-events.ftl"/><#rt/>
/>
</#macro>
