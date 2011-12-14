package net.hedges.fandangled.codec.xsd;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.commons.AbstractFreemarkerCodec;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;
import net.hedges.fandangled.codec.commons.XMLFormatter;

import java.io.*;

public class XsdCodec extends AbstractFreemarkerCodec {

    @Override
    protected String getTemplateName() {
        return "XSD.ftl";
    }
}
