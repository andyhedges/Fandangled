package net.hedges.fandangled.bindings;

import junit.framework.TestCase;
import net.hedges.fandangled.bindings.builder.DocumentBuilder;
import net.hedges.fandangled.bindings.builder.FandangledBindingException;

/**
 * Created by IntelliJ IDEA.
 * User: KV49
 * Date: 25/06/12
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class TestMarkupWalker extends TestCase {

    public void testWalker() {
        try {
            DocumentBuilder.parse("This is a test\n\nso is this");
        } catch (FandangledBindingException e) {
            assertTrue("Shouldn't have thrown an exception", false);
        }
    }

}
