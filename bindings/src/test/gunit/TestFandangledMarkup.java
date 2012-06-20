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

	public void testWORD2() throws Exception {
		// test input: "not ok"
		Object retval = execLexer("WORD", "not ok", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"WORD", expecting, actual);
	}

	public void testParagraph1() throws Exception {
		// test input: "this should be OK"
		Object retval = execParser("paragraph", "this should be OK", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}

	public void testParagraph2() throws Exception {
		// test input: "this should also be OK."
		Object retval = execParser("paragraph", "this should also be OK.", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}



}