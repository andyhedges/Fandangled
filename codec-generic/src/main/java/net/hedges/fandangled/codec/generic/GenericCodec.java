package net.hedges.fandangled.codec.generic;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import net.hedges.fandangled.bindings.domain.*;
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

    }

    public void encode(Interface serviceInterface, File outputDirectory) throws TranscodingException {


        try {
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
        } catch (IOException ioe) {
            throw new TranscodingException("Couldn't find template directory: " + templatePath, ioe);
        }

        File dir = new File(outputDirectory, serviceInterface.getName());
        dir.mkdirs();

        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("interface", serviceInterface);
        create("interface.ftl", rootMap, new File(dir, prefix + serviceInterface.getName() + extension));

        processEntity(serviceInterface, "type", serviceInterface.getTypes(), dir);
        processEntity(serviceInterface, "event", serviceInterface.getEvents(), dir);
        processEntity(serviceInterface, "operation", serviceInterface.getOperations(), dir);
        processEntity(serviceInterface, "enumeration", serviceInterface.getEnumerations(), dir);
        processEntity(serviceInterface, "exception", serviceInterface.getExceptions(), dir);

    }

    private <T extends AbstractEntity> void processEntity(Interface serviceInterface, String name, List<T> items, File outputDir) throws TranscodingException {
        File individualTemplate = new File(templatePath, name + ".ftl");
        Map<String, Object> rootMap = new HashMap<String, Object>();
        if (individualTemplate.exists()) {
            for (T i : items) {
                rootMap = new HashMap<String, Object>();
                rootMap.put("interface", serviceInterface);
                rootMap.put(name, i);
                create(name + ".ftl", rootMap, new File(outputDir, i.getName() + extension));
            }
        }

        File groupTemplate = new File(templatePath, name + "s.ftl");
        if (groupTemplate.exists()) {
            rootMap = new HashMap<String, Object>();
            rootMap.put("interface", serviceInterface);
            rootMap.put(name + "s", items);
            create(name + "s.ftl", rootMap, new File(outputDir, prefix + name + extension));
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

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
