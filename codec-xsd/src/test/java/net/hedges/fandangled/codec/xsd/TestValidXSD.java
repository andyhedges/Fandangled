package net.hedges.fandangled.codec.xsd;

import junit.framework.TestCase;
import net.hedges.fandangled.bindings.builder.FandangledBindingException;
import net.hedges.fandangled.bindings.builder.InterfaceBuilder;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.commons.Codec;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Unit test for simple App.
 */
public class TestValidXSD
        extends TestCase {

    public void testValidXSD() throws Exception {
        Codec wsdlCodec = new XsdCodec();
        String example = wsdlCodec.getClass().getClassLoader()
                .getResource("Example.idl").getFile();
        Interface _interface = InterfaceBuilder.parse(example);
        wsdlCodec.encode(_interface, new File("target"));
    }
}
