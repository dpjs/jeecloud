<#--
<input type="radio"/>
-->
<#macro radio
	value="" checked=""
	noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon=":" hasColon="true"
	id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
	>
<input type="radio"<#rt/>
 value="${value}"<#rt/>
<#if id!=""> id="${id}"</#if><#rt/>
<#if (checked!="")> checked="${checked}"</#if><#rt/>
<#include "common-attributes.ftl"/><#rt/>
<#include "scripting-events.ftl"/><#rt/>
/><#rt/>
</#macro>