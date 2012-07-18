package net.hedges.fandangled.codec.generic;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import net.hedges.fandangled.bindings.domain.*;
import net.hedges.fandangled.bindings.domain.Exception;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic codec
 */
public class GenericCodec implements Codec {

    Configuration cfg = new Configuration();
    
    private String templatePath = ".";
    private String extension = ".txt";
    private String prefix = "Fandangled-";

    public GenericCodec() {
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setClassForTemplateLoading(this.getClass(), "/");
    }

    public void encode(Interface serviceInterface, File outputDirectory) throws TranscodingException {

        File dir = new File(outputDirectory, serviceInterface.getName());
        dir.mkdirs();

        List<Type> types = serviceInterface.getTypes();
        for (Type type : types) {
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("interface", serviceInterface);
            rootMap.put("type", type);
            create("Type.ftl", rootMap, new File(dir, prefix + type.getName() + extension));
        }

        List<net.hedges.fandangled.bindings.domain.Exception> exceptions = serviceInterface.getExceptions();
        for (Exception exception : exceptions) {
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("interface", serviceInterface);
            rootMap.put("exception", exception);
            create("Exception.ftl", rootMap, new File(dir, prefix + exception.getName() + extension));
        }

        List<Enumeration> enumerations = serviceInterface.getEnumerations();
        for (Enumeration enumeration : enumerations) {
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("interface", serviceInterface);
            rootMap.put("enumeration", enumerations);
            create("Enumeration.ftl", rootMap, new File(dir, prefix + enumeration.getName() + extension));
        }

        List<Event> events = serviceInterface.getEvents();
        for (Event event : events) {
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("interface", serviceInterface);
            rootMap.put("event", enumerations);
            create("Event.ftl", rootMap, new File(dir, event.getName() + ".cs"));
        }

    }

    private void create(String templateName, Map<String, Object> rootMap, File outputFile) throws TranscodingException {

        PrintWriter out = null;
        try {

            Template template = cfg.getTemplate(templateName);
            out = new PrintWriter(new FileWriter(outputFile));
            template.process(rootMap, out);
            out.close();
        } catch (IOException ioe) {
            throw new TranscodingException("Transcoding Exception", ioe);
        } catch (TemplateException te) {
            throw new TranscodingException("Transcoding Exception", te);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
