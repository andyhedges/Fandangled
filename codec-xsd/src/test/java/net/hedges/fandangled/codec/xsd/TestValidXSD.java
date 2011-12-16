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
