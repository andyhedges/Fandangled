package net.hedges.fandangled.bindings.domain;


public class Version {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Version [value=" + value + "]";
	}

}
