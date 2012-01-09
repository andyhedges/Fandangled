/*
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
*/
package net.hedges.fandangled.codec.poco;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.hedges.fandangled.bindings.domain.Exception;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.bindings.domain.Type;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PocoCodec implements Codec {

    Configuration cfg = new Configuration();

    public PocoCodec() {
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
            create("Type.ftl", rootMap, new File(dir, type.getName() + ".cs"));
        }

        List<Exception> exceptions = serviceInterface.getExceptions();
        for (Exception exception : exceptions) {
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("interface", serviceInterface);
            rootMap.put("type", exception);
            create("Type.ftl", rootMap, new File(dir, exception.getName() + ".cs"));
        }
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("interface", serviceInterface);
        create("Enumerations.ftl", rootMap, new File(dir, "Enumerations.cs"));

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
