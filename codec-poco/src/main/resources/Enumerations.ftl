using System;

<#if interface.namespacePrefix??>
<#assign nsp = '${interface.namespacePrefix?capitalize}.'/>
<#else>
<#assign nsp = ''/>
</#if>



namespace ${interface.organisation}.${nsp}${interface.serviceName}.${interface.name}.Domain
{
    <#list interface.enumerations as enumeration>
    public enum ${enumeration.name}
    {
        <#list enumeration.values as value>
        ${value.name}<#if value_has_next>, </#if>
        </#list>
    }<#if enumeration_has_next>

    </#if>
    </#list>

}