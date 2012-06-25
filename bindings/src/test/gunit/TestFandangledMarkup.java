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

	public void testParagraph3() throws Exception {
		// test input: "This should be ok  "
		Object retval = execParser("paragraph", "This should be ok  ", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}

	public void testParagraph4() throws Exception {
		// test input: "  So should this  "
		Object retval = execParser("paragraph", "  So should this  ", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}

	public void testParagraph5() throws Exception {
		// test input: "  As should this"
		Object retval = execParser("paragraph", "  As should this", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}

	public void testParagraph6() throws Exception {
		// test input: "Two  spaces"
		Object retval = execParser("paragraph", "Two  spaces", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}

	public void testParagraph7() throws Exception {
		// test input: "this should\r\nbe ok"
		Object retval = execParser("paragraph", "this should\r\nbe ok", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}

	public void testParagraph8() throws Exception {
		// test input: "this shouldn't\r\n\r\nbe ok"
		Object retval = execParser("paragraph", "this shouldn't\r\n\r\nbe ok", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"paragraph", expecting, actual);
	}

	public void testDocument1() throws Exception {
		// test input: "\"basic test\""
		Object retval = execParser("document", "\"basic test\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"document", expecting, actual);
	}

	public void testDocument2() throws Exception {
		// test input: "\"\""
		Object retval = execParser("document", "\"\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"document", expecting, actual);
	}

	public void testDocument3() throws Exception {
		// test input: "\"this should\r\n\r\nbe ok\""
		Object retval = execParser("document", "\"this should\r\n\r\nbe ok\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"document", expecting, actual);
	}

	public void testDocument4() throws Exception {
		// test input: "\"This should\r\n\r\nbe\r\n\r\nfine\r\n\r\ntoo\r\n\r\n\""
		Object retval = execParser("document", "\"This should\r\n\r\nbe\r\n\r\nfine\r\n\r\ntoo\r\n\r\n\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"document", expecting, actual);
	}

	public void testDocument5() throws Exception {
		// test input: "\"This\r\nis one\r\npara\""
		Object retval = execParser("document", "\"This\r\nis one\r\npara\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"document", expecting, actual);
	}

	public void testDocument6() throws Exception {
		// test input: "\"\r\n\r\nThis is ok\""
		Object retval = execParser("document", "\"\r\n\r\nThis is ok\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"document", expecting, actual);
	}

	public void testDocument7() throws Exception {
		// test input: "\"Testing out \\\"quotes\\\"\""
		Object retval = execParser("document", "\"Testing out \\\"quotes\\\"\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"document", expecting, actual);
	}



}