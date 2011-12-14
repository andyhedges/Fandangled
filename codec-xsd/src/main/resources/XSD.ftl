<?xml version="1.0"?>
 <#assign
 	primitives = {	"boolean":"xsd:boolean",
 					"byte":"xsd:byte",
 					"short":"xsd:short",
 					"int":"xsd:integer",
 					"long":"xsd:integer",
 					"float":"xsd:float",
 					"double":"xsd:double",
 					"string":"xsd:string",
 					"datetime":"xsd:integer"}

 	>

<xsd:schema targetNamespace="http://example.com/namespace/domain/"
                xmlns="http://example.com/namespace/domain/"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema">

      <#list types as type>
            <xsd:complexType name="${type.name}">
                <xsd:sequence>
                    <#list type.parameters as parameter>
                        <@typeMac typeInfo=parameter.typeInfo name=parameter.name/>
                    </#list>
                </xsd:sequence>
            </xsd:complexType>
      </#list>

      <#list enumerations as enumeration>
            <xsd:simpleType name="${enumeration.name}">
                <xsd:restriction base="xsd:string">
                    <#list enumeration.values as value>
                        <xsd:enumeration value="${value.name}"/>
                    </#list>
                </xsd:restriction>
            </xsd:simpleType>
      </#list>

</xsd:schema>

 <#macro typeMac typeInfo name>
	<#if typeInfo.class.simpleName == "TypeInfo">
	    <!-- single type -->
		<xsd:element name="${name}" type="${primitives[typeInfo.typeName]!typeInfo.typeName}"/>
	<#elseif typeInfo.class.simpleName == "CollectionInfo">
	    <!-- collection -->
		<xsd:element name="${name}" minOccurs="0" maxOccurs="unbounded">
			<xsd:complexType>
				<xsd:sequence>
					<@typeMac typeInfo=typeInfo.containedTypeInfo name="item"/>
				</xsd:sequence>
			</xsd:complexType>
		</xsd:element>
	<#elseif typeInfo.class.simpleName == "MapInfo">
		<!-- map -->
		<xsd:element name="${name}" minOccurs="0" maxOccurs="unbounded">
            <xsd:complexType>
                <xsd:sequence>
                    <@typeMac typeInfo=typeInfo.containedNameTypeInfo name="name"/>
                    <@typeMac typeInfo=typeInfo.containedValueTypeInfo name="value"/>
                </xsd:sequence>
            </xsd:complexType>
		</xsd:element>
	</#if>

 </#macro>