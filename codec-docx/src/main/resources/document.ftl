<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<!--
   Copyright 2010 Andy Hedges <andy@hedges.net>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
-->
<w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships" xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math" xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing" xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main" xmlns:ns6="http://schemas.openxmlformats.org/schemaLibrary/2006/main" xmlns:c="http://schemas.openxmlformats.org/drawingml/2006/chart" xmlns:ns8="http://schemas.openxmlformats.org/drawingml/2006/chartDrawing" xmlns:dgm="http://schemas.openxmlformats.org/drawingml/2006/diagram" xmlns:pic="http://schemas.openxmlformats.org/drawingml/2006/picture" xmlns:ns11="http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing" xmlns:dsp="http://schemas.microsoft.com/office/drawing/2008/diagram" xmlns:ns13="urn:schemas-microsoft-com:office:excel" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w10="urn:schemas-microsoft-com:office:word" xmlns:ns17="urn:schemas-microsoft-com:office:powerpoint" xmlns:odx="http://opendope.org/xpaths" xmlns:odc="http://opendope.org/conditions" xmlns:odq="http://opendope.org/questions" xmlns:odi="http://opendope.org/components" xmlns:odgm="http://opendope.org/SmartArt/DataHierarchy" xmlns:ns24="http://schemas.openxmlformats.org/officeDocument/2006/bibliography" xmlns:ns25="http://schemas.openxmlformats.org/drawingml/2006/compatibility" xmlns:ns26="http://schemas.openxmlformats.org/drawingml/2006/lockedCanvas">
	<w:body>
        <@coversheet/>
		<w:p>
			<w:pPr><w:pStyle w:val="Heading1"/></w:pPr>
			<w:r><w:t>Overview</w:t></w:r>
		</w:p>
		<w:p>
			<w:pPr><w:pStyle w:val="Normal"/></w:pPr>
			<w:r><w:t>${description?xml}</w:t></w:r>
		</w:p>

		<!-- operations -->
		<w:p>
			<w:pPr><w:pStyle w:val="Heading1"/></w:pPr>
			<w:r><w:t>Operations</w:t></w:r>
		</w:p>
		<#list operations as operation>
		    <w:p>
			    <w:pPr><w:pStyle w:val="Heading2"/></w:pPr>
                <w:r><w:t>Operation: ${operation.name?xml} <#if operation.async>(async)</#if></w:t></w:r>
            </w:p>
            <w:p><w:pPr><w:pStyle w:val="Normal"/></w:pPr>
                <w:r><w:t>${operation.description?xml}</w:t></w:r>
            </w:p>
            <w:p>
                <w:pPr><w:pStyle w:val="Heading3"/></w:pPr>
                <w:r><w:t>Request Parameters</w:t></w:r>
            </w:p>
            <w:tbl>
                <@parameterTableHeader/>
                <@parameterTable parameters=operation.parameters/>
            </w:tbl>
            <#if operation.return?? && operation.return.typeInfo.name != "void">
                <w:p>
                    <w:pPr><w:pStyle w:val="Heading3"/></w:pPr>
                    <w:r><w:t>Response Parameter</w:t></w:r>
                </w:p>
                <w:tbl>
                    <@returnTable operation.return/>
                </w:tbl>
            </#if>
		</#list>

        <!-- events -->
		<w:p>
			<w:pPr><w:pStyle w:val="Heading1"/></w:pPr>
			<w:r><w:t>Events</w:t></w:r>
		</w:p>
		<#list events as event>
            <w:p>
                <w:pPr><w:pStyle w:val="Heading2"/></w:pPr>
                <w:r><w:t>Event: ${event.name?xml}</w:t></w:r>
            </w:p>
            <w:p>
                <w:pPr><w:pStyle w:val="Normal"/></w:pPr>
                <w:r><w:t>${event.description?xml}</w:t></w:r>
            </w:p>
            <w:tbl>
                <@parameterTableHeader/>
                <@parameterTable parameters=event.parameters/>
            </w:tbl>
		</#list>

		<!-- exception -->
		<w:p>
			<w:pPr><w:pStyle w:val="Heading1"/></w:pPr>
			<w:r><w:t>Exceptions</w:t></w:r>
		</w:p>
		<#list exceptions as exception>
            <w:p>
                <w:pPr><w:pStyle w:val="Heading2"/></w:pPr>
                <w:r><w:t>Exception: ${exception.name?xml}</w:t></w:r>
            </w:p>
            <w:p>
                <w:pPr><w:pStyle w:val="Normal"/></w:pPr>
                <w:r><w:t>${exception.description?xml}</w:t></w:r>
            </w:p>
            <w:tbl>
                <@parameterTableHeader/>
                <@parameterTable parameters=exception.parameters/>
            </w:tbl>
		</#list>

		<!-- types -->
		<w:p>
			<w:pPr><w:pStyle w:val="Heading1"/></w:pPr>
			<w:r><w:t>Types</w:t></w:r>
		</w:p>
		<#list types as type>
            <w:p>
                <w:pPr><w:pStyle w:val="Heading2"/></w:pPr>
                <w:r><w:t>Type: ${type.name?xml}</w:t></w:r>
            </w:p>
            <w:p>
                <w:pPr><w:pStyle w:val="Normal"/></w:pPr>
                <w:r><w:t>${type.description?xml}</w:t></w:r>
            </w:p>
            <w:tbl>
                <@parameterTableHeader/>
                <@parameterTable parameters=type.parameters/>
            </w:tbl>
        </#list>

        <!-- enumerations -->
		<w:p>
			<w:pPr><w:pStyle w:val="Heading1"/></w:pPr>
			<w:r><w:t>Enumerations</w:t></w:r>
		</w:p>
		<#list enumerations as enumeration>
            <w:p>
                <w:pPr><w:pStyle w:val="Heading2"/></w:pPr>
                <w:r><w:t>Enumeration: ${enumeration.name?xml}</w:t></w:r>
            </w:p>
            <w:p>
                <w:pPr><w:pStyle w:val="Normal"/></w:pPr>
                <w:r><w:t>${enumeration.description?xml}</w:t></w:r>
            </w:p>
            <w:tbl>
                <w:tblPr>
                    <w:tblStyle w:val="ColorfulList-Accent3"/>
                    <w:tblW w:w="0" w:type="auto"/>
                    <w:tblLook w:val="04A0"/>
                </w:tblPr>
                <w:tblGrid>
                    <w:gridCol w:w="2708"/>
                    <w:gridCol w:w="6318"/>
                </w:tblGrid>
                <w:tr>
                    <w:tc>
                        <w:tcPr><w:tcW w:w="2708" w:type="dxa"/></w:tcPr>
                        <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Name</w:t></w:r></w:p>
                    </w:tc>
                    <w:tc>
                        <w:tcPr><w:tcW w:w="6318" w:type="dxa"/></w:tcPr>
                        <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Description</w:t></w:r></w:p>
                    </w:tc>
                </w:tr>
                <#list enumeration.values as value>
                    <w:tr>
                        <w:tc>
                            <w:tcPr><w:tcW w:w="2708" w:type="dxa"/></w:tcPr>
                            <w:p><w:r><w:rPr/><w:t>${value.name}</w:t></w:r></w:p>
                        </w:tc>
                        <w:tc>
                            <w:tcPr><w:tcW w:w="6318" w:type="dxa"/></w:tcPr>
                            <w:p><w:r><w:rPr/><w:t>${(value.description!"No description")?xml}</w:t></w:r></w:p>
                        </w:tc>
                    </w:tr>
                </#list>
            </w:tbl>
		</#list>
		<w:sectPr>
			<w:pgSz w:w="11907" w:h="16839" w:code="9"/>
			<w:pgMar w:top="1440" w:right="1440" w:bottom="1440" w:left="1440"/>
		</w:sectPr>
	</w:body>
</w:document>

<!-- macros -->

<#macro parameterTableHeader>
    <w:tblPr>
        <w:tblStyle w:val="ColorfulList-Accent3"/>
        <w:tblW w:w="0" w:type="auto"/>
        <w:tblLook w:val="04A0"/>
    </w:tblPr>
    <w:tblGrid>
        <w:gridCol w:w="1354"/>
        <w:gridCol w:w="1354"/>
        <w:gridCol w:w="1354"/>
        <w:gridCol w:w="4964"/>
    </w:tblGrid>
    <w:tr>
        <w:tc>
            <w:tcPr><w:tcW w:w="1354" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Type</w:t></w:r></w:p>
        </w:tc>
        <w:tc>
            <w:tcPr><w:tcW w:w="1354" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Name</w:t></w:r></w:p>
        </w:tc>
        <w:tc>
            <w:tcPr><w:tcW w:w="1354" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Mandatory</w:t></w:r></w:p>
        </w:tc>
        <w:tc>
            <w:tcPr><w:tcW w:w="4964" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Description</w:t></w:r></w:p>
        </w:tc>
    </w:tr>
</#macro>

<#macro parameterTable parameters>
    <#list parameters as parameter>
        <w:tr>
            <w:tc>
                <w:tcPr><w:tcW w:w="1354" w:type="dxa"/></w:tcPr>
                <w:p><w:r><w:rPr></w:rPr><w:t>${parameter.typeInfo.name?xml}</w:t></w:r></w:p>
            </w:tc>
            <w:tc>
                <w:tcPr><w:tcW w:w="1354" w:type="dxa"/></w:tcPr>
                <w:p><w:r><w:rPr/><w:t>${parameter.name?xml}</w:t></w:r></w:p>
            </w:tc>
            <w:tc>
                <w:tcPr><w:tcW w:w="1354" w:type="dxa"/></w:tcPr>
                <w:p><w:r><w:rPr/><w:t>${parameter.mandatory?string}</w:t></w:r></w:p>
            </w:tc>
            <w:tc>
                <w:tcPr><w:tcW w:w="4964" w:type="dxa"/></w:tcPr>
                <w:p><w:r><w:rPr/><w:t>${parameter.description?xml}</w:t></w:r></w:p>
            </w:tc>
        </w:tr>
    </#list>
</#macro>

<#macro returnTable return>
    <w:tblPr>
        <w:tblStyle w:val="TableGrid"/>
        <w:tblW w:w="0" w:type="auto"/>
        <w:tblLook w:val="04A0"/>
    </w:tblPr>
    <w:tblGrid>
        <w:gridCol w:w="2708"/>
        <w:gridCol w:w="6318"/>
    </w:tblGrid>
    <w:tr>
        <w:tc>
            <w:tcPr><w:tcW w:w="2708" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Type</w:t></w:r></w:p>
        </w:tc>
        <w:tc>
            <w:tcPr><w:tcW w:w="6318" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr><w:b/></w:rPr><w:t>Description</w:t></w:r></w:p>
        </w:tc>
    </w:tr>
    <w:tr>
        <w:tc>
            <w:tcPr><w:tcW w:w="2708" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr/><w:t>${return.typeInfo.name?xml}</w:t></w:r></w:p>
        </w:tc>
        <w:tc>
            <w:tcPr><w:tcW w:w="6318" w:type="dxa"/></w:tcPr>
            <w:p><w:r><w:rPr/><w:t>${return.description?xml}</w:t></w:r></w:p>
        </w:tc>
    </w:tr>
</#macro>

<#macro coversheet>
    <w:sdt>
        <w:sdtPr>
            <w:rPr>
                <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                <w:caps/>
            </w:rPr>
            <w:id w:val="-1217045357"/>
            <w:docPartObj>
                <w:docPartGallery w:val="Cover Pages"/>
                <w:docPartUnique/>
            </w:docPartObj>
        </w:sdtPr>
        <w:sdtEndPr>
            <w:rPr>
                <w:rFonts w:asciiTheme="minorHAnsi" w:eastAsiaTheme="minorHAnsi" w:hAnsiTheme="minorHAnsi" w:cstheme="minorBidi"/>
                <w:caps w:val="0"/>
                <w:lang w:val="en-GB" w:eastAsia="en-US"/>
            </w:rPr>
        </w:sdtEndPr>
        <w:sdtContent>
            <w:tbl>
                <w:tblPr>
                    <w:tblW w:w="5000" w:type="pct"/>
                    <w:jc w:val="center"/>
                    <w:tblLook w:val="04A0" w:firstRow="1" w:lastRow="0" w:firstColumn="1" w:lastColumn="0" w:noHBand="0" w:noVBand="1"/>
                </w:tblPr>
                <w:tblGrid>
                    <w:gridCol w:w="9242"/>
                </w:tblGrid>
                <w:tr w:rsidR="00BB5BAE">
                    <w:trPr>
                        <w:trHeight w:val="2880"/>
                        <w:jc w:val="center"/>
                    </w:trPr>
                    <w:sdt>
                        <w:sdtPr>
                            <w:rPr>
                                <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                <w:caps/>
                            </w:rPr>
                            <w:alias w:val="Company"/>
                            <w:id w:val="15524243"/>
                            <w:placeholder>
                                <w:docPart w:val="BA4CB09CA16C425FBCF7C56818864F7F"/>
                            </w:placeholder>
                            <w:dataBinding w:prefixMappings="xmlns:ns0='http://schemas.openxmlformats.org/officeDocument/2006/extended-properties'" w:xpath="/ns0:Properties[1]/ns0:Company[1]" w:storeItemID="{6668398D-A668-4E3E-A5EB-62B293D839F1}"/>
                            <w:text/>
                        </w:sdtPr>
                        <w:sdtContent>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct"/>
                                </w:tcPr>
                                <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE" w:rsidP="00BB5BAE">
                                    <w:pPr>
                                        <w:pStyle w:val="NoSpacing"/>
                                        <w:jc w:val="center"/>
                                        <w:rPr>
                                            <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                            <w:caps/>
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                            <w:caps/>
                                        </w:rPr>
                                        <w:t></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:sdtContent>
                    </w:sdt>
                </w:tr>
                <w:tr w:rsidR="00BB5BAE">
                    <w:trPr>
                        <w:trHeight w:val="1440"/>
                        <w:jc w:val="center"/>
                    </w:trPr>
                    <w:sdt>
                        <w:sdtPr>
                            <w:rPr>
                                <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                <w:sz w:val="80"/>
                                <w:szCs w:val="80"/>
                            </w:rPr>
                            <w:alias w:val="Title"/>
                            <w:id w:val="15524250"/>
                            <w:placeholder>
                                <w:docPart w:val="78ED7D1C1E1C4775A9D435D722E3B139"/>
                            </w:placeholder>
                            <w:dataBinding w:prefixMappings="xmlns:ns0='http://schemas.openxmlformats.org/package/2006/metadata/core-properties' xmlns:ns1='http://purl.org/dc/elements/1.1/'" w:xpath="/ns0:coreProperties[1]/ns1:title[1]" w:storeItemID="{6C3C8BC8-F283-45AE-878A-BAB7291924A1}"/>
                            <w:text/>
                        </w:sdtPr>
                        <w:sdtContent>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct"/>
                                    <w:tcBorders>
                                        <w:bottom w:val="single" w:sz="4" w:space="0" w:color="4F81BD" w:themeColor="accent1"/>
                                    </w:tcBorders>
                                    <w:vAlign w:val="center"/>
                                </w:tcPr>
                                <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE" w:rsidP="00BB5BAE">
                                    <w:pPr>
                                        <w:pStyle w:val="NoSpacing"/>
                                        <w:jc w:val="center"/>
                                        <w:rPr>
                                            <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                            <w:sz w:val="80"/>
                                            <w:szCs w:val="80"/>
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                            <w:sz w:val="80"/>
                                            <w:szCs w:val="80"/>
                                        </w:rPr>
                                        <w:t></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:sdtContent>
                    </w:sdt>
                </w:tr>
                <w:tr w:rsidR="00BB5BAE">
                    <w:trPr>
                        <w:trHeight w:val="720"/>
                        <w:jc w:val="center"/>
                    </w:trPr>
                    <w:sdt>
                        <w:sdtPr>
                            <w:rPr>
                                <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                <w:sz w:val="44"/>
                                <w:szCs w:val="44"/>
                            </w:rPr>
                            <w:alias w:val="Subtitle"/>
                            <w:id w:val="15524255"/>
                            <w:placeholder>
                                <w:docPart w:val="3DD8036AFC1B4C619560BB1705D5C72A"/>
                            </w:placeholder>
                            <w:dataBinding w:prefixMappings="xmlns:ns0='http://schemas.openxmlformats.org/package/2006/metadata/core-properties' xmlns:ns1='http://purl.org/dc/elements/1.1/'" w:xpath="/ns0:coreProperties[1]/ns1:subject[1]" w:storeItemID="{6C3C8BC8-F283-45AE-878A-BAB7291924A1}"/>
                            <w:text/>
                        </w:sdtPr>
                        <w:sdtContent>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct"/>
                                    <w:tcBorders>
                                        <w:top w:val="single" w:sz="4" w:space="0" w:color="4F81BD" w:themeColor="accent1"/>
                                    </w:tcBorders>
                                    <w:vAlign w:val="center"/>
                                </w:tcPr>
                                <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE" w:rsidP="00BB5BAE">
                                    <w:pPr>
                                        <w:pStyle w:val="NoSpacing"/>
                                        <w:jc w:val="center"/>
                                        <w:rPr>
                                            <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                            <w:sz w:val="44"/>
                                            <w:szCs w:val="44"/>
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi" w:cstheme="majorBidi"/>
                                            <w:sz w:val="44"/>
                                            <w:szCs w:val="44"/>
                                        </w:rPr>
                                        <w:t></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:sdtContent>
                    </w:sdt>
                </w:tr>
                <w:tr w:rsidR="00BB5BAE">
                    <w:trPr>
                        <w:trHeight w:val="360"/>
                        <w:jc w:val="center"/>
                    </w:trPr>
                    <w:tc>
                        <w:tcPr>
                            <w:tcW w:w="5000" w:type="pct"/>
                            <w:vAlign w:val="center"/>
                        </w:tcPr>
                        <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE">
                            <w:pPr>
                                <w:pStyle w:val="NoSpacing"/>
                                <w:jc w:val="center"/>
                            </w:pPr>
                        </w:p>
                    </w:tc>
                </w:tr>
                <w:tr w:rsidR="00BB5BAE">
                    <w:trPr>
                        <w:trHeight w:val="360"/>
                        <w:jc w:val="center"/>
                    </w:trPr>
                    <w:sdt>
                        <w:sdtPr>
                            <w:rPr>
                                <w:b/>
                                <w:bCs/>
                            </w:rPr>
                            <w:alias w:val="Author"/>
                            <w:id w:val="15524260"/>
                            <w:placeholder>
                                <w:docPart w:val="CF17DB75115049BFB8038EAD8E5AC95A"/>
                            </w:placeholder>
                            <w:dataBinding w:prefixMappings="xmlns:ns0='http://schemas.openxmlformats.org/package/2006/metadata/core-properties' xmlns:ns1='http://purl.org/dc/elements/1.1/'" w:xpath="/ns0:coreProperties[1]/ns1:creator[1]" w:storeItemID="{6C3C8BC8-F283-45AE-878A-BAB7291924A1}"/>
                            <w:text/>
                        </w:sdtPr>
                        <w:sdtContent>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct"/>
                                    <w:vAlign w:val="center"/>
                                </w:tcPr>
                                <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00B24F28" w:rsidP="00B24F28">
                                    <w:pPr>
                                        <w:pStyle w:val="NoSpacing"/>
                                        <w:jc w:val="center"/>
                                        <w:rPr>
                                            <w:b/>
                                            <w:bCs/>
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:b/>
                                            <w:bCs/>
                                            <w:lang w:val="en-GB"/>
                                        </w:rPr>
                                        <w:t></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:sdtContent>
                    </w:sdt>
                </w:tr>
                <w:tr w:rsidR="00BB5BAE">
                    <w:trPr>
                        <w:trHeight w:val="360"/>
                        <w:jc w:val="center"/>
                    </w:trPr>
                    <w:sdt>
                        <w:sdtPr>
                            <w:rPr>
                                <w:b/>
                                <w:bCs/>
                            </w:rPr>
                            <w:alias w:val="Date"/>
                            <w:id w:val="516659546"/>
                            <w:placeholder>
                                <w:docPart w:val="F4B8A6AD8C554BD0B7A6D91BC467E23B"/>
                            </w:placeholder>
                            <w:showingPlcHdr/>
                            <w:dataBinding w:prefixMappings="xmlns:ns0='http://schemas.microsoft.com/office/2006/coverPageProps'" w:xpath="/ns0:CoverPageProperties[1]/ns0:PublishDate[1]" w:storeItemID="{55AF091B-3C7A-41E3-B477-F2FDAA23CFDA}"/>
                            <w:date>
                                <w:lid w:val="en-US"/>
                                <w:storeMappedDataAs w:val="dateTime"/>
                                <w:calendar w:val="gregorian"/>
                            </w:date>
                        </w:sdtPr>
                        <w:sdtContent>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct"/>
                                    <w:vAlign w:val="center"/>
                                </w:tcPr>
                                <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE">
                                    <w:pPr>
                                        <w:pStyle w:val="NoSpacing"/>
                                        <w:jc w:val="center"/>
                                        <w:rPr>
                                            <w:b/>
                                            <w:bCs/>
                                        </w:rPr>
                                    </w:pPr>
                                    <w:r>
                                        <w:rPr>
                                            <w:b/>
                                            <w:bCs/>
                                        </w:rPr>
                                        <w:t>[Pick the date]</w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:sdtContent>
                    </w:sdt>
                </w:tr>
            </w:tbl>
            <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE"/>
            <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE"/>
            <w:tbl>
                <w:tblPr>
                    <w:tblpPr w:leftFromText="187" w:rightFromText="187" w:horzAnchor="margin" w:tblpXSpec="center" w:tblpYSpec="bottom"/>
                    <w:tblW w:w="5000" w:type="pct"/>
                    <w:tblLook w:val="04A0" w:firstRow="1" w:lastRow="0" w:firstColumn="1" w:lastColumn="0" w:noHBand="0" w:noVBand="1"/>
                </w:tblPr>
                <w:tblGrid>
                    <w:gridCol w:w="9242"/>
                </w:tblGrid>
                <w:tr w:rsidR="00BB5BAE">
                    <w:sdt>
                        <w:sdtPr>
                            <w:alias w:val="Abstract"/>
                            <w:id w:val="8276291"/>
                            <w:placeholder>
                                <w:docPart w:val="B0E14C5AD5A5478DAB75F9424963C99F"/>
                            </w:placeholder>
                            <w:dataBinding w:prefixMappings="xmlns:ns0='http://schemas.microsoft.com/office/2006/coverPageProps'" w:xpath="/ns0:CoverPageProperties[1]/ns0:Abstract[1]" w:storeItemID="{55AF091B-3C7A-41E3-B477-F2FDAA23CFDA}"/>
                            <w:text/>
                        </w:sdtPr>
                        <w:sdtContent>
                            <w:tc>
                                <w:tcPr>
                                    <w:tcW w:w="5000" w:type="pct"/>
                                </w:tcPr>
                                <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00962644" w:rsidP="00962644">
                                    <w:pPr>
                                        <w:pStyle w:val="NoSpacing"/>
                                    </w:pPr>
                                    <w:r w:rsidRPr="00962644">
                                        <w:t></w:t>
                                    </w:r>
                                </w:p>
                            </w:tc>
                        </w:sdtContent>
                    </w:sdt>
                </w:tr>
            </w:tbl>
            <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE"/>
            <w:p w:rsidR="00BB5BAE" w:rsidRDefault="00BB5BAE">
                <w:r>
                    <w:br w:type="page"/>
                </w:r>
            </w:p>
            <w:bookmarkStart w:id="0" w:name="_GoBack" w:displacedByCustomXml="next"/>
            <w:bookmarkEnd w:id="0" w:displacedByCustomXml="next"/>
        </w:sdtContent>
    </w:sdt>
</#macro>