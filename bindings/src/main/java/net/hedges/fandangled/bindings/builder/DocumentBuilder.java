package net.hedges.fandangled.bindings.builder;

import net.hedges.fandangled.bindings.*;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.bindings.domain.document.Document;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;

/**
 * Created by IntelliJ IDEA.
 * User: KV49
 * Date: 25/06/12
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class DocumentBuilder {

    public static Document parse(String text) {
        CharStream cs = new ANTLRStringStream(text);
        FandangledMarkupLexer lexer = new FandangledMarkupLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FandangledMarkupParser parser = new FandangledMarkupParser(tokens);
        try {
            FandangledMarkupParser.document_return result = parser.document();

            Tree t = (Tree) result.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            nodes.setTokenStream(tokens);
            FandangledMarkupWalker walker = new FandangledMarkupWalker(nodes);
            Document document = walker.document();
            return document;
        } catch (RecognitionException e) {
            return new Document();
        }
    }
}
