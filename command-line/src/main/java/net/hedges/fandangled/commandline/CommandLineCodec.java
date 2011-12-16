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
package net.hedges.fandangled.commandline;

import net.hedges.fandangled.bindings.builder.FandangledBindingException;
import net.hedges.fandangled.bindings.builder.InterfaceBuilder;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;
import net.hedges.fandangled.codec.docx.DocxCodec;
import net.hedges.fandangled.codec.wsdl.WsdlCodec;
import net.hedges.fandangled.codec.xsd.XsdCodec;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: andy
 * Date: 07/12/11
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class CommandLineCodec {

    public static void main(String[] args) {
        if (args.length != 4 || !args[0].equals("-t")) {
            usage();
        }
        String type = args[1];
        String codecClassName = null;
        Codec codec = null;
        try {
            if (type.equals("xsd")) {
                codec = new XsdCodec();
            } else if (type.equals("wsdl")) {
                codec = new WsdlCodec();
            } else if (type.equals("docx")) {
                codec = new DocxCodec();
            } else {
                System.err.print("Invalid type '" + type + "' please specify xsd, wsdl, docx");
                System.exit(1);
            }
            File inputFile = new File(args[2]);
            if(!inputFile.exists()){
                System.out.println("Couldn't find " + inputFile.getAbsolutePath());
            }
            File outputDir = new File(args[3]);
            codec.encode(InterfaceBuilder.parse(inputFile), outputDir);
        } catch (FandangledBindingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TranscodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private static void usage() {
        System.err.println("usage: fidl -t <TYPE> INPUTFILE OUTPUTDIR");
        System.err.println("TYPE: xsd, wsdl, docx");
    }

}
