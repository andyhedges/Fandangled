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
package net.hedges.fandangled.bindings.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.hedges.fandangled.bindings.FandangledBindingWalker;
import net.hedges.fandangled.bindings.FandangledLexer;
import net.hedges.fandangled.bindings.FandangledParser;
import net.hedges.fandangled.bindings.domain.Interface;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;

public class InterfaceBuilder {

	public static Interface parse(InputStream in)
			throws FandangledBindingException {
		try {
			CharStream cs = new ANTLRInputStream(in);
			FandangledLexer lexer = new FandangledLexer(cs);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			FandangledParser parser = new FandangledParser(tokens);
			FandangledParser.serviceInterface_return result = parser
					.serviceInterface();
			Tree t = (Tree) result.getTree();
			CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
			nodes.setTokenStream(tokens);
			FandangledBindingWalker walker = new FandangledBindingWalker(nodes);
			Interface serviceInterface = walker.serviceInterface();
			return serviceInterface;
		} catch (RecognitionException re) {
			throw new FandangledBindingException(re);
		} catch (IOException ioe) {
			throw new FandangledBindingException(ioe);
		}
	}

	public static Interface parse(File idlFile)
			throws FandangledBindingException, FileNotFoundException {
		return parse(new FileInputStream(idlFile));
	}

	public static Interface parse(String fileName)
			throws FandangledBindingException, FileNotFoundException {
		return parse(new File(fileName));

	}

}
