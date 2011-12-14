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
<wsdl:definitions name="EndorsementSearch"
  targetNamespace="http://example.com/namespace/"
  xmlns:tns="http://example.com/namespace/"
  xmlns:domain="http://example.com/namespace/domain/"
  xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
  xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  
	  <wsdl:types>
	  
	      <xsd:schema targetNamespace="http://example.com/namespace/domain/" xmlns="http://example.com/namespace/domain/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
	      
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
			  
			<#list operations as operation>
				<#if operation.parameters?has_content>
				<xsd:element name="${operation.name?cap_first}In">
				    <xsd:complexType>
                        <xsd:sequence>
                            <#list operation.parameters as parameter>
                                <@typeMac typeInfo=parameter.typeInfo name=parameter.name/>
                            </#list>
                        </xsd:sequence>
					</xsd:complexType>
				</xsd:element>
				</#if>
				<#if operation.return?? && operation.return.typeInfo.name != "void">
					<xsd:element name="${operation.name?cap_first}Out">
					    <xsd:complexType>
                            <xsd:sequence>
                                <@typeMac typeInfo=operation.return.typeInfo name="return"/>
                            </xsd:sequence>
						</xsd:complexType>
					</xsd:element>
				</#if>
			</#list>
			
			<#list exceptions as exception>
				<xsd:element name="${exception.name?cap_first}Fault">
					<xsd:complexType>
						<xsd:sequence>
							<#list exception.parameters as parameter>
								<xsd:sequence>
									<@typeMac typeInfo=parameter.typeInfo name=parameter.name/>
								</xsd:sequence>						
							</#list>
						</xsd:sequence>
					</xsd:complexType>
				</xsd:element>
			</#list>
			
			<xsd:element name="empty"/>
	      
	      </xsd:schema>
	  
	  </wsdl:types>
	  
	  <#list operations as operation>
			<#if operation.parameters?has_content>
				<wsdl:message name="${operation.name?cap_first}Request">
					<wsdl:part name="request" element="domain:${operation.name?cap_first}In"/>
				</wsdl:message>
			<#else>
				<wsdl:message name="${operation.name?cap_first}Request">
					<wsdl:part name="request" element="domain:empty"/>
				</wsdl:message>
			</#if>
			
			<#if operation.return?? && operation.return.typeInfo.name != "void">
				<wsdl:message name="${operation.name?cap_first}Response">
					<wsdl:part name="response" element="domain:${operation.name?cap_first}Out"/>
				</wsdl:message>
			<#else>
				<wsdl:message name="${operation.name?cap_first}Response">
					<wsdl:part name="response" element="domain:empty"/>
				</wsdl:message>
			</#if>
			
			<#list operation.exceptions as exception>
				<wsdl:message name="${exception.typeName?cap_first}Fault">
					<wsdl:part name="fault${exception.typeName?cap_first}" element="domain:${exception.typeName?cap_first}Fault"/>
				</wsdl:message>	
			</#list>	
	  </#list>
	  
	 <wsdl:portType name="${name}PortType">
	  	<#list operations as operation>
	  		<wsdl:operation name="${operation.name}">
				<wsdl:input message="tns:${operation.name?cap_first}Request"/>
				<wsdl:output message="tns:${operation.name?cap_first}Response"/>
				<#list operation.exceptions as exception>
					<wsdl:fault name="${exception.typeName?uncap_first}Fault" message="tns:${exception.typeName?cap_first}Fault"/>
				</#list>
			</wsdl:operation>
	  	</#list>	
	 </wsdl:portType>
	 
	 <wsdl:binding name="${name}Binding" type="tns:${name}PortType">
	 	<soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
	  	<#list operations as operation>
	  		<wsdl:operation name="${operation.name}">
				<soap:operation soapAction="http://example.com/${operation.name}"/>
				<wsdl:input>
					<soap:body use="literal"/>
				</wsdl:input>
				<wsdl:output>
					<soap:body use="literal"/>
				</wsdl:output>
				<#list operation.exceptions as exception>
					<wsdl:fault name="${exception.typeName?uncap_first}Fault"/>
				</#list>
			</wsdl:operation>
	  	</#list>	 
	 </wsdl:binding>
	 
	 <wsdl:service name="${name}ServiceInterface">
	 	<wsdl:documentation>${description}</wsdl:documentation>
	 	<wsdl:port name="${name}Port" binding="tns:${name}Binding">
	 		<soap:address location="http://set/this/programmatically"/>
	 	</wsdl:port>
	 </wsdl:service>
   
  </wsdl:definitions>
 
 
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