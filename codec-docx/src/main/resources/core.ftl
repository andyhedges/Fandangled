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
<cp:coreProperties xmlns:cp="http://schemas.openxmlformats.org/package/2006/metadata/core-properties" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:dcterms="http://purl.org/dc/terms/" xmlns:dcmitype="http://purl.org/dc/dcmitype/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<dc:title>${name?xml} Interface</dc:title>
	<dc:subject>on the ${serviceName?xml} Service</dc:subject>
	<dc:creator><#list authors as author>${author.name?xml} &lt;${author.email}&gt;<#if author_has_next>; </#if></#list></dc:creator>
	<cp:lastModifiedBy>some other kid</cp:lastModifiedBy>
	<cp:revision>2</cp:revision>
	<dcterms:created xsi:type="dcterms:W3CDTF">2011-12-12T15:43:00Z</dcterms:created>
	<dcterms:modified xsi:type="dcterms:W3CDTF">2011-12-12T15:43:00Z</dcterms:modified>
</cp:coreProperties>
