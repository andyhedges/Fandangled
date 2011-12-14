package net.hedges.fandangled.codec.commons;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.hedges.fandangled.bindings.domain.Interface;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: andy
 * Date: 14/12/11
 * Time: 09:51
 * To change this template use File | Settings | File Templates.
 */
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
