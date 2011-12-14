package net.hedges.fandangled.codec.docx;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;
import net.hedges.fandangled.codec.commons.XMLFormatter;
import org.docx4j.XmlUtils;
import org.docx4j.customXmlProperties.DatastoreItem;
import org.docx4j.docProps.core.CoreProperties;
import org.docx4j.docProps.core.ObjectFactory;
import org.docx4j.docProps.extended.Properties;
import org.docx4j.jaxb.Context;
import org.docx4j.model.datastorage.BindingHandler;
import org.docx4j.model.datastorage.CustomXmlDataStorage;
import org.docx4j.model.datastorage.CustomXmlDataStorageImpl;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.CustomXmlDataStoragePart;
import org.docx4j.openpackaging.parts.CustomXmlDataStoragePropertiesPart;
import org.docx4j.openpackaging.parts.DocPropsCorePart;
import org.docx4j.openpackaging.parts.PartName;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBResult;
import java.io.*;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: andy
 * Date: 08/12/11
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class DocxCodec implements Codec {
    public void encode(Interface serviceInterface, File outputDirectory) throws TranscodingException {
        PrintWriter out = null;

        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, false);
            String doc = generateString("document.ftl", serviceInterface);
            ByteArrayInputStream inDoc = new ByteArrayInputStream(doc.getBytes());
            wordMLPackage.getMainDocumentPart().setJaxbElement((org.docx4j.wml.Document) XmlUtils.unmarshal(inDoc));

            String app = generateString("app.ftl", serviceInterface);
            Unmarshaller u = Context.jcDocPropsExtended.createUnmarshaller();
            ByteArrayInputStream inApp = new ByteArrayInputStream(app.getBytes());
            wordMLPackage.getDocPropsExtendedPart().setJaxbElement((Properties) u.unmarshal(inApp));

            String core = generateString("core.ftl", serviceInterface);
            Unmarshaller u2 = Context.jcDocPropsCore.createUnmarshaller();
            ByteArrayInputStream inCore = new ByteArrayInputStream(core.getBytes());
            wordMLPackage.getDocPropsCorePart().setJaxbElement((CoreProperties) u2.unmarshal(inCore));

            String item = generateString("item1.ftl", serviceInterface);
            ByteArrayInputStream inItem = new ByteArrayInputStream(item.getBytes());
            CustomXmlDataStoragePart customXmlDataStoragePart = new CustomXmlDataStoragePart(wordMLPackage.getParts());
            CustomXmlDataStorage data = new CustomXmlDataStorageImpl();
            data.setDocument(inItem);
            customXmlDataStoragePart.setData(data);
            wordMLPackage.getMainDocumentPart().addTargetPart(customXmlDataStoragePart);
            CustomXmlDataStoragePropertiesPart part = new CustomXmlDataStoragePropertiesPart();
            org.docx4j.customXmlProperties.ObjectFactory of = new org.docx4j.customXmlProperties.ObjectFactory();
            DatastoreItem dsi = of.createDatastoreItem();
            String newItemId = "{55AF091B-3C7A-41E3-B477-F2FDAA23CFDA}";
            dsi.setItemID(newItemId);
            part.setJaxbElement(dsi);
            customXmlDataStoragePart.addTargetPart(part);

            wordMLPackage.save(new File(outputDirectory, serviceInterface.getName() + "-" + serviceInterface.getVersion().getValue() + ".docx"));
        } catch (IOException ioe) {
            throw new TranscodingException("Transcoding Exception", ioe);
        } catch (TemplateException te) {
            throw new TranscodingException("Transcoding Exception", te);
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Docx4JException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private String generateString(String templateName, Interface serviceInterface) throws IOException, TemplateException, JAXBException {
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        Template template = cfg.getTemplate(templateName);
        StringWriter proxy = new StringWriter();
        template.process(serviceInterface, proxy);
        return XMLFormatter.format(proxy.toString());
    }
}
