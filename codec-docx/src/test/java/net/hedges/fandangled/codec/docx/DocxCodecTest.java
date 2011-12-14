package net.hedges.fandangled.codec.docx;

import java.io.File;

import junit.framework.TestCase;
import net.hedges.fandangled.bindings.builder.InterfaceBuilder;
import net.hedges.fandangled.bindings.domain.Interface;

public class DocxCodecTest extends TestCase {

	public void testDocx() throws Exception{
		DocxCodec codec = new DocxCodec();
		String example = codec.getClass().getClassLoader()
		.getResource("Example.idl").getFile();
		Interface _interface = InterfaceBuilder.parse(example);
		codec.encode(_interface, new File("target"));
	}
	
}
