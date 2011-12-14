package net.hedges.fandangled.codec.commons;

import java.io.File;

import net.hedges.fandangled.bindings.domain.Interface;

public interface Codec {

	public void encode(Interface _interface, File outputDirectory)
			throws TranscodingException;

}
