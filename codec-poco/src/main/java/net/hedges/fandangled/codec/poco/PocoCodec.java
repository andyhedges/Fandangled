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
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.bindings.domain.Type;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;

import java.io.*;
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
        List<Type> types = serviceInterface.getTypes();
        File dir = new File(outputDirectory, serviceInterface.getName());
        dir.mkdirs();
        for(Type type: types){
            create("Type.ftl", serviceInterface, type, new File(dir, type.getName() + ".cs"));
        }
    }

    private void create(String templateName, Interface serviceInterface, Type type, File outputFile) throws TranscodingException {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("interface", serviceInterface);
        rootMap.put("type", type);
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
