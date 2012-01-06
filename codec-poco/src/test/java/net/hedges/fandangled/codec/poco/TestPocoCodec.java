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

import net.hedges.fandangled.bindings.builder.InterfaceBuilder;
import net.hedges.fandangled.bindings.domain.Interface;

import java.io.File;

public class TestPocoCodec {

    public void testPoco() throws Exception {
        PocoCodec codec = new PocoCodec();
        String example = codec.getClass().getClassLoader().getResource("Example.idl").getFile();
        Interface _interface = InterfaceBuilder.parse(example);
        codec.encode(_interface, new File("target"));
    }

}
