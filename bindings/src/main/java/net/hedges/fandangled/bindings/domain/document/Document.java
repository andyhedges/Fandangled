package net.hedges.fandangled.bindings.domain.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: KV49
 * Date: 25/06/12
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class Document {

    private List<Paragraph> paragraphs = new ArrayList<Paragraph>();

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
