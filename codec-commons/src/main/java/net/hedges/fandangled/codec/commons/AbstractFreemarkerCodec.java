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
package net.hedges.fandangled.codec.commons;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.hedges.fandangled.bindings.domain.Interface;

import java.io.*;

public abstract class AbstractFreemarkerCodec implements Codec {

    protected abstract String getTemplateName();


	public void encode(Interface serviceInterface, File outputDirectory)
			throws TranscodingException {

		PrintWriter out = null;

		try {
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setClassForTemplateLoading(this.getClass(), "/");
			Template wsdl = cfg.getTemplate(getTemplateName());
			out = new PrintWriter(new FileWriter(new File(outputDirectory,
					serviceInterface.getName() + "-interface.wsdl")));
			StringWriter proxy = new StringWriter();
			wsdl.process(serviceInterface, proxy);
			out.println(XMLFormatter.format(proxy.toString()));
			out.close();
		} catch (IOException ioe) {
			throw new TranscodingException("Transcoding Exception", ioe);
		} catch (TemplateException te){
			throw new TranscodingException("Transcoding Exception", te);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
