package net.hedges.fandangled.codec.wsdl;

import java.io.File;


import javax.wsdl.Definition;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import junit.framework.TestCase;

import net.hedges.fandangled.bindings.builder.InterfaceBuilder;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.commons.Codec;


import com.sun.xml.ws.api.model.wsdl.WSDLModel;
import com.sun.xml.ws.api.server.SDDocumentSource;
import com.sun.xml.ws.api.wsdl.parser.WSDLParserExtension;
import com.sun.xml.ws.api.wsdl.parser.XMLEntityResolver;
import org.apache.axis2.wsdl.WSDL2Java;


public class TestValidWSDL extends TestCase {

    public void testWSDL() throws Exception {
        Codec wsdlCodec = new WsdlCodec();
        String example = wsdlCodec.getClass().getClassLoader().getResource("Example.idl").getFile();
        Interface _interface = InterfaceBuilder.parse(example);
        wsdlCodec.encode(_interface, new File("target"));

        ///validate with wsdl4j fwiw doesn't seem to complain about much
        File loc = new File("target", _interface.getName() + "-interface.wsdl");
        WSDLFactory factory = WSDLFactory.newInstance();
        WSDLReader wsdlReader = factory.newWSDLReader();
        Definition def = wsdlReader.readWSDL(null, loc.toURI().toString());
        assertNotNull(def);
        File outputDir = File.createTempFile("fandangled-unit-test-can-delete-", "");
        outputDir.delete();//'cos there isn't a temp dir method
        outputDir.mkdirs();
        System.out.println(outputDir.toString());
        WSDL2Java.main(new String[]{"-uri", loc.toURI().toString(), "-o", outputDir.getAbsolutePath(), "-p", "com.example.fandagled", "-ss"});
        System.out.println(outputDir.toString());
        deleteDir(outputDir);//'cos there isn't one in Java yet

    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] files = dir.list();
            for (int i = 0; i < files.length; i++) {
                if (!deleteDir(new File(dir, files[i]))) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

}
