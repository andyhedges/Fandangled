<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<cp:coreProperties xmlns:cp="http://schemas.openxmlformats.org/package/2006/metadata/core-properties" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:dcterms="http://purl.org/dc/terms/" xmlns:dcmitype="http://purl.org/dc/dcmitype/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<dc:title>${name?xml} Interface</dc:title>
	<dc:subject>on the ${serviceName?xml} Service</dc:subject>
	<dc:creator><#list authors as author>${author.name?xml} &lt;${author.email}&gt;<#if author_has_next>; </#if></#list></dc:creator>
	<cp:lastModifiedBy>some other kid</cp:lastModifiedBy>
	<cp:revision>2</cp:revision>
	<dcterms:created xsi:type="dcterms:W3CDTF">2011-12-12T15:43:00Z</dcterms:created>
	<dcterms:modified xsi:type="dcterms:W3CDTF">2011-12-12T15:43:00Z</dcterms:modified>
</cp:coreProperties>
