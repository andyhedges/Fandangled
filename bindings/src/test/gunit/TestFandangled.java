package net.hedges.fandangled.bindings;

import org.antlr.gunit.gUnitBaseTest;

public class TestFandangled extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./net/hedges/fandangled/bindings";
		this.lexerPath = "net.hedges.fandangled.bindings.FandangledLexer";
		this.parserPath = "net.hedges.fandangled.bindings.FandangledParser";
	}

	public void testVERSION1() throws Exception {
		// test input: "a"
		Object retval = execLexer("VERSION", "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testVERSION2() throws Exception {
		// test input: "a.b"
		Object retval = execLexer("VERSION", "a.b", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testVERSION3() throws Exception {
		// test input: "1"
		Object retval = execLexer("VERSION", "1", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testVERSION4() throws Exception {
		// test input: "1.2"
		Object retval = execLexer("VERSION", "1.2", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testVERSION5() throws Exception {
		// test input: "1.2.3"
		Object retval = execLexer("VERSION", "1.2.3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testVERSION6() throws Exception {
		// test input: "1.2.3.4"
		Object retval = execLexer("VERSION", "1.2.3.4", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testVERSION7() throws Exception {
		// test input: "1.2.3."
		Object retval = execLexer("VERSION", "1.2.3.", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testVERSION8() throws Exception {
		// test input: "12.34.567"
		Object retval = execLexer("VERSION", "12.34.567", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"VERSION", expecting, actual);
	}

	public void testID1() throws Exception {
		// test input: "a"
		Object retval = execLexer("ID", "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID2() throws Exception {
		// test input: "1"
		Object retval = execLexer("ID", "1", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID3() throws Exception {
		// test input: "_"
		Object retval = execLexer("ID", "_", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID4() throws Exception {
		// test input: "ab1"
		Object retval = execLexer("ID", "ab1", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID5() throws Exception {
		// test input: "a1b"
		Object retval = execLexer("ID", "a1b", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID6() throws Exception {
		// test input: "-ab"
		Object retval = execLexer("ID", "-ab", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID7() throws Exception {
		// test input: "_1"
		Object retval = execLexer("ID", "_1", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID8() throws Exception {
		// test input: "Bob"
		Object retval = execLexer("ID", "Bob", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testID9() throws Exception {
		// test input: "bob"
		Object retval = execLexer("ID", "bob", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"ID", expecting, actual);
	}

	public void testMANDATORY1() throws Exception {
		// test input: "mandatory"
		Object retval = execLexer("MANDATORY", "mandatory", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"MANDATORY", expecting, actual);
	}

	public void testMANDATORY2() throws Exception {
		// test input: "something else"
		Object retval = execLexer("MANDATORY", "something else", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"MANDATORY", expecting, actual);
	}

	public void testTypeName1() throws Exception {
		// test input: "list<Test>"
		Object retval = execParser("typeName", "list<Test>", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"typeName", expecting, actual);
	}

	public void testTypeName2() throws Exception {
		// test input: "set<Test>"
		Object retval = execParser("typeName", "set<Test>", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"typeName", expecting, actual);
	}

	public void testTypeName3() throws Exception {
		// test input: "map<Name, Value>"
		Object retval = execParser("typeName", "map<Name, Value>", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"typeName", expecting, actual);
	}

	public void testTypeName4() throws Exception {
		// test input: "list<set<Test>>"
		Object retval = execParser("typeName", "list<set<Test>>", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"typeName", expecting, actual);
	}

	public void testTypeName5() throws Exception {
		// test input: "map<map<Something, Whatever>, list<IfYouDoThisYouAreCrazy>>"
		Object retval = execParser("typeName", "map<map<Something, Whatever>, list<IfYouDoThisYouAreCrazy>>", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"typeName", expecting, actual);
	}

	public void testTypeName6() throws Exception {
		// test input: "Test"
		Object retval = execParser("typeName", "Test", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"typeName", expecting, actual);
	}

	public void testTypeName7() throws Exception {
		// test input: "set<Test,<>Something>"
		Object retval = execParser("typeName", "set<Test,<>Something>", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"typeName", expecting, actual);
	}

	public void testOperation1() throws Exception {
		// test input: "async void updateIndex (){\r\n\t@description \"suggests that the index should be updates as soon as possible\";\r\n\t@since 1.0.2;\r\n\t@return \"no return type as async\";\r\n}\r\n"
		Object retval = execParser("operation", "async void updateIndex (){\r\n\t@description \"suggests that the index should be updates as soon as possible\";\r\n\t@since 1.0.2;\r\n\t@return \"no return type as async\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"operation", expecting, actual);
	}

	public void testOperation2() throws Exception {
		// test input: "Customer retrieveCustomer(mandatory String guid) throws CustomerNotFoundException, SomeOtherException {\r\n\t@description \"an operation that returns a customer for a given guid\";\r\n\t@since 1.0.1;\r\n\t@return \"the customer that we wanted to retrieve\";\r\n\t@parameter guid, \"the guid of the customer that we want to retrieve\";\r\n\t@exception CustomerNotFoundException, \"when the customer isn't found\";\r\n\t@exception SomeOtherException, \"something else has gone wrong\";\r\n}\r\n"
		Object retval = execParser("operation", "Customer retrieveCustomer(mandatory String guid) throws CustomerNotFoundException, SomeOtherException {\r\n\t@description \"an operation that returns a customer for a given guid\";\r\n\t@since 1.0.1;\r\n\t@return \"the customer that we wanted to retrieve\";\r\n\t@parameter guid, \"the guid of the customer that we want to retrieve\";\r\n\t@exception CustomerNotFoundException, \"when the customer isn't found\";\r\n\t@exception SomeOtherException, \"something else has gone wrong\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"operation", expecting, actual);
	}

	public void testOperation3() throws Exception {
		// test input: "list<Product> searchProduct(mandatory String query, int start, int length) {\r\n\t@description \"allows you to find customers based on boolean search queries on specific fields or, by default, all fields\";\r\n\t@since 0.0.0;\r\n\t@return \"list of products\";\r\n\t@parameter query,\r\n\t\t\"the quesry string for the search. Logical operators and, or, xor can be used between query terms\r\n\t\tterms can specify a field to apply to such as description:bike and the terms values can be surounded\r\n\t\tby quotes to specify multi word phrased such as description:\\\"mens bikes\\\"\";\r\n\t@parameter start, \"for pagination, the first return to be contrained in the return list\";\r\n\t@parameter length, \"the number of results returned in the list\";\r\n}\r\n"
		Object retval = execParser("operation", "list<Product> searchProduct(mandatory String query, int start, int length) {\r\n\t@description \"allows you to find customers based on boolean search queries on specific fields or, by default, all fields\";\r\n\t@since 0.0.0;\r\n\t@return \"list of products\";\r\n\t@parameter query,\r\n\t\t\"the quesry string for the search. Logical operators and, or, xor can be used between query terms\r\n\t\tterms can specify a field to apply to such as description:bike and the terms values can be surounded\r\n\t\tby quotes to specify multi word phrased such as description:\\\"mens bikes\\\"\";\r\n\t@parameter start, \"for pagination, the first return to be contrained in the return list\";\r\n\t@parameter length, \"the number of results returned in the list\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"operation", expecting, actual);
	}

	public void testOperation4() throws Exception {
		// test input: "list<Something> doStuff(mandatory String query, int start, int length) {\r\n\t@description \"allows you to find customers based on boolean search queries on specific fields or, by default, all fields\";\r\n\t@since 0.0.0;\r\n\t@return \"list of stuff\";\r\n\t@parameter query,\r\n\t\t\"the query string for the search. Logical operators and, or, xor can be used between query terms\r\n\t\tterms can specify a field to apply to such as description:bike and the terms values can be surounded\r\n\t\tby quotes to specify multi word phrased such as description:\\\"mens bikes\\\"\";\r\n\t@parameter start, \"for pagination, the first return to be contrained in the return list\";\r\n\t@parameter length, \"the number of results returned in the list\";\r\n}\r\n"
		Object retval = execParser("operation", "list<Something> doStuff(mandatory String query, int start, int length) {\r\n\t@description \"allows you to find customers based on boolean search queries on specific fields or, by default, all fields\";\r\n\t@since 0.0.0;\r\n\t@return \"list of stuff\";\r\n\t@parameter query,\r\n\t\t\"the query string for the search. Logical operators and, or, xor can be used between query terms\r\n\t\tterms can specify a field to apply to such as description:bike and the terms values can be surounded\r\n\t\tby quotes to specify multi word phrased such as description:\\\"mens bikes\\\"\";\r\n\t@parameter start, \"for pagination, the first return to be contrained in the return list\";\r\n\t@parameter length, \"the number of results returned in the list\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"operation", expecting, actual);
	}

	public void testEvent1() throws Exception {
		// test input: "event CustomerRetrieved(mandatory String guid){\r\n\t\t@description \"this is a silly example however the idea is that each time a customer is retrieve an event is raised to show that it has been retrieved\";\r\n\t\t@parameter guid, \"the id of the customer that was retrieved\";\r\n}\r\n"
		Object retval = execParser("event", "event CustomerRetrieved(mandatory String guid){\r\n\t\t@description \"this is a silly example however the idea is that each time a customer is retrieve an event is raised to show that it has been retrieved\";\r\n\t\t@parameter guid, \"the id of the customer that was retrieved\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"event", expecting, actual);
	}

	public void testEvent2() throws Exception {
		// test input: "\tevent SearchIndexUpdated(){\r\n\t\t@description \"this event is raised after the search index has been updated\";\r\n}\r\n"
		Object retval = execParser("event", "\tevent SearchIndexUpdated(){\r\n\t\t@description \"this event is raised after the search index has been updated\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"event", expecting, actual);
	}

	public void testType1() throws Exception {
		// test input: "type Customer (mandatory String guid,\r\n\t\tString firstname,\r\n\t\tString lastname,\r\n\t\tDate dob,\r\n\t\tGender gender){\r\n\t@description \"a Customer is an entity that represents a person who does business with us\";\r\n\t@parameter guid,\t\"the globally unique identifier for the customer\";\r\n\t@parameter firstname,\t\"the first name of the customer\";\r\n\t@parameter lastname,\t\"the last name of the customer\";\r\n\t@parameter dob,\t\t\"the date of birth of the customer\";\r\n\t@parameter gender,\t\"the identified gender of the customer (i.e. the gender they specify for themselves)\";\r\n}\r\n"
		Object retval = execParser("type", "type Customer (mandatory String guid,\r\n\t\tString firstname,\r\n\t\tString lastname,\r\n\t\tDate dob,\r\n\t\tGender gender){\r\n\t@description \"a Customer is an entity that represents a person who does business with us\";\r\n\t@parameter guid,\t\"the globally unique identifier for the customer\";\r\n\t@parameter firstname,\t\"the first name of the customer\";\r\n\t@parameter lastname,\t\"the last name of the customer\";\r\n\t@parameter dob,\t\t\"the date of birth of the customer\";\r\n\t@parameter gender,\t\"the identified gender of the customer (i.e. the gender they specify for themselves)\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testException1() throws Exception {
		// test input: "exception CustomerNotFoundException(String guid, String message){\r\n\t@description \"Used to show a customer wasn't found for a given operation\";\r\n\t@parameter guid, \"the guid of the customer that wasn't found\";\r\n\t@parameter message, \"a message about why the customer wasn't found\";\r\n}\r\n"
		Object retval = execParser("exception", "exception CustomerNotFoundException(String guid, String message){\r\n\t@description \"Used to show a customer wasn't found for a given operation\";\r\n\t@parameter guid, \"the guid of the customer that wasn't found\";\r\n\t@parameter message, \"a message about why the customer wasn't found\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"exception", expecting, actual);
	}

	public void testEnumeration1() throws Exception {
		// test input: "enumeration Gender (MALE, FEMALE, OTHER) {\r\n\t@value MALE;\r\n\t@value FEMALE;\r\n\t@value OTHER, \"when the user doesn't wish to specify or doesn't relate to any other option\";\r\n}\r\n"
		Object retval = execParser("enumeration", "enumeration Gender (MALE, FEMALE, OTHER) {\r\n\t@value MALE;\r\n\t@value FEMALE;\r\n\t@value OTHER, \"when the user doesn't wish to specify or doesn't relate to any other option\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"enumeration", expecting, actual);
	}

	public void testServiceInterface1() throws Exception {
		// test input: "interface CustomerCrud exposes Customer {\r\n\t@author \"Andy Hedges\", \"andy@hedges.net\";\r\n\t@version 1.0.1;\r\n\t@description\r\n\t\t\"Here is a super long description\r\n\t\tThat will test just how good our\r\n\t\tparser and lexer really is! For\r\n\t\tsome reason I call it a \\\"test\\\";\r\n\t\tok, that seemed to work\";\r\n}\r\n"
		Object retval = execParser("serviceInterface", "interface CustomerCrud exposes Customer {\r\n\t@author \"Andy Hedges\", \"andy@hedges.net\";\r\n\t@version 1.0.1;\r\n\t@description\r\n\t\t\"Here is a super long description\r\n\t\tThat will test just how good our\r\n\t\tparser and lexer really is! For\r\n\t\tsome reason I call it a \\\"test\\\";\r\n\t\tok, that seemed to work\";\r\n}\r\n", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"serviceInterface", expecting, actual);
	}

	public void testServiceInterface2() throws Exception {
		// test input: "ExampleSimple.idl"
		Object retval = execParser("serviceInterface", "ExampleSimple.idl", true);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"serviceInterface", expecting, actual);
	}

	public void testServiceInterface3() throws Exception {
		// test input: "Example.idl"
		Object retval = execParser("serviceInterface", "Example.idl", true);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.OK, retval);
		Object expecting = "OK";

		assertEquals("testing rule "+"serviceInterface", expecting, actual);
	}



}