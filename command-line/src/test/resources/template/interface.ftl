/*
 * Simple Example: in this case I have access to the main interface type
 */
 
 <#assign
 	primitives = {	"bool":"boolean",
 			"datetime":"Calendar",
 			"list":"List",
 			"set":"Set",
 			"map":"Map",
 			"string":"String"}
 	>
package <#list interface.organisationDomainName?split(".")?reverse as bit>${bit}.</#list>${interface.namespacePrefix};

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Calendar;

/**
 * <#list interface.description.paragraphs as paragraph>
 * ${paragraph.text?replace("\n", "\n *")}
 * </#list>
<#list interface.authors as author>
 * @author ${author.name} (${author.email})
</#list>
 * @version ${interface.version.value}
 */

public interface ${interface.name} {

<#list interface.operations as operation>

	/**
	<#list operation.description.paragraphs as paragraph>
	 * ${paragraph.text?replace("\n", "\n	 *")}
	</#list>
	 *
	<#list operation.parameters as parameter>
	 * @param ${parameter.name} <#list parameter.description.paragraphs as paragraph>${paragraph.text?replace("\n", "\n	 *")}</#list>
	</#list>
	 *
	 * @return <#list operation.return.description.paragraphs as paragraph>${paragraph.text?replace("\n", "\n	 *")}</#list>
	 * 
	<#list operation.exceptions as exception>
	 * @throws ${exception.typeName} <#list exception.description.paragraphs as paragraph>${paragraph.text?replace("\n", "\n	 *")}</#list>
	</#list>
	 * @since ${operation.since.value}
	 */
	public <@typeCon typeInfo=operation.return.typeInfo/> ${operation.name}(
 		<#list operation.parameters as parameter>
			<@typeCon typeInfo=parameter.typeInfo/> ${parameter.name}<#if parameter_has_next>,</#if>		 		
 		</#list>
 	)<#if operation.exceptions?has_content> throws </#if><#list operation.exceptions as exception>${exception.typeName}<#if exception_has_next>,</#if></#list>;
 	
 </#list>
}

<#macro typeCon typeInfo>${primitives[typeInfo.typeName]!typeInfo.name}</#macro>