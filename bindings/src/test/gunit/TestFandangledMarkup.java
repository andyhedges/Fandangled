package net.hedges.fandangled.bindings;

import org.antlr.gunit.gUnitBaseTest;

public class TestFandangledMarkup extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./net/hedges/fandangled/bindings";
		this.lexerPath = "net.hedges.fandangled.bindings.FandangledMarkupLexer";
		this.parserPath = "net.hedges.fandangled.bindings.FandangledMarkupParser";
	}

	public void testWORD1() throws Exception {
		// test input: "word"
		Object retval = execLexer("WORD", "word", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"WORD", expecting, actual);
	}



}