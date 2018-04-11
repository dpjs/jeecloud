<#--
<form></form>
-->
<#macro form
	action="" method="post" target="" enctype="multipart/form-data" acceptCharset=""
	theme="jeesys" width="100%" tableClass="pn-ftable" labelWidth="20" required="false" colspan="1" cellspacing="1"
	id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
	onsubmit="" dataAuto="" dataAjax=""
	>
<form<#rt/>
 method="${method}"<#rt/>
 action="${action}"<#rt/>
<#if id!=""> id="${id}"</#if><#rt/>
<#if target!=""> target="${target}"</#if><#rt/>
<#if enctype!=""> enctype="${enctype}"</#if><#rt/>
<#if dataAuto!=""> data-auto="${dataAuto}"</#if><#rt/>
<#if dataAjax!=""> data-ajax="${dataAjax}"</#if><#rt/>
<#if onsubmit!=""> onsubmit="${onsubmit}"</#if><#rt/>
<#if acceptCharset!=""> accept-charset="${acceptCharset}"</#if><#rt/>
<#include "common-attributes.ftl"/><#rt/>
>
<#nested/><#rt/>
</form>
</#macro>
