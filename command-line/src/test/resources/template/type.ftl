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

public class ${type.name} {
<#list type.parameters as parameter>
	private <@typeCon typeInfo=parameter.typeInfo/> ${parameter.name};
</#list>
<#list type.parameters as parameter>

	public void set${parameter.name?cap_first}(<@typeCon typeInfo=parameter.typeInfo/> ${parameter.name}){
		this.${parameter.name} = ${parameter.name};
	}
	
	public <@typeCon typeInfo=parameter.typeInfo/> get${parameter.name?cap_first}(){
		return ${parameter.name};
	}
	
</#list>


}

<#macro typeCon typeInfo>${primitives[typeInfo.typeName]!typeInfo.name}</#macro>