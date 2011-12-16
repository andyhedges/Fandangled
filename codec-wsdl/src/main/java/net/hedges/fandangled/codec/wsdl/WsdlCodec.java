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
package net.hedges.fandangled.codec.wsdl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.commons.AbstractFreemarkerCodec;
import net.hedges.fandangled.codec.commons.Codec;
import net.hedges.fandangled.codec.commons.TranscodingException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.hedges.fandangled.codec.commons.XMLFormatter;

public class WsdlCodec extends AbstractFreemarkerCodec {

    @Override
    protected String getTemplateName() {
        return "WSDL.ftl";
    }
}
