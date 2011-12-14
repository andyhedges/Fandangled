package net.hedges.fandangled.codec.wsdl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.commons.AbstractFreemarkerCodec;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.hedges.fandangled.codec.commons.XMLFormatter;

public class WsdlCodec extends AbstractFreemarkerCodec {

    @Override
    protected String getTemplateName() {
        return "WSDL.ftl";
    }
}
