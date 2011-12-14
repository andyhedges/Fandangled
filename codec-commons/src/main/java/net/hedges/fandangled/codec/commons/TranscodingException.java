package net.hedges.fandangled.codec.commons;


public class TranscodingException extends Exception {

	public TranscodingException(String msg, Throwable t) {
		super(msg, t);
	}

	public TranscodingException(Throwable t) {
		super(t);
	}

}
