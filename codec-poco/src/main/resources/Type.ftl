 <#ftl strip_whitespace=true/>
 <#assign
 	primitives = {	"boolean":"bool",
 					"byte":"byte",
 					"short":"short",
 					"int":"int",
 					"long":"long",
 					"float":"float",
 					"double":"double",
 					"string":"string",
 					"datetime":"DateTime"}

 	>
using System;
using System.Runtime.Serialization;
using System.Collections.Generic;

<#if interface.namespacePrefix??>
<#assign nsp = '${interface.namespacePrefix?capitalize}.'/>
<#else>
<#assign nsp = ''/>
</#if>

namespace ${interface.organisation}.${nsp}${interface.serviceName}.${interface.name}.Domain
{
    [DataContract(Namespace = Constants.Namespace)]
    public class ${type.name} <#if type.class.name == 'Exception'>: System.Exception </#if>
    {
        <#list type.parameters as parameter>
        [DataMemeber] public <@typeMac typeInfo=parameter.typeInfo/> ${parameter.name?cap_first} {get; private set;}
        </#list>
    }

    public ${type.name} (<#list type.parameters as parameter><@typeMac typeInfo=parameter.typeInfo/> ${parameter.name?cap_first}<#if parameter_has_next>, </#if></#list>)
    {
        <#list type.parameters as parameter>
        ${parameter.name?cap_first} = ${parameter.name?uncap_first};
        </#list>
    }
}

 <#macro typeMac typeInfo><#if typeInfo.class.simpleName == "TypeInfo">${primitives[typeInfo.typeName]!typeInfo.typeName}<#elseif typeInfo.class.simpleName == "CollectionInfo">IEnumerable<<@typeMac typeInfo=typeInfo.containedTypeInfo/>><#elseif typeInfo.class.simpleName == "MapInfo">IDictionary<<@typeMac typeInfo=typeInfo.containedNameTypeInfo/>, <@typeMac typeInfo=typeInfo.containedValueTypeInfo/>></#if></#macro>