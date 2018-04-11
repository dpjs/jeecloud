<input type="radio"<#rt/>
 value="${rkey}"<#rt/>
<#if (rkey?string=="" && (!value?? || value?string=="")) || (value?? && value?string!="" && value?string==rkey?string)> checked="checked"</#if><#rt/>
<#include "common-attributes.ftl"/><#rt/>
<#include "scripting-events.ftl"/><#rt/>
title="${rvalue}"/><#if hasNext> </#if>