package net.hedges.fandangled.bindings.builder;

public class FandangledBindingException extends java.lang.Exception {

	public FandangledBindingException() {
		super();
	}

	public FandangledBindingException(String msg) {
		super(msg);
	}
	
	public FandangledBindingException(Throwable t){
		super(t);
	}

	public FandangledBindingException(String msg, Throwable t) {
		super(msg, t);
	}

}
