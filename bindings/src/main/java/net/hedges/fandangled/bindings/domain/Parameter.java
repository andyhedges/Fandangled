package net.hedges.fandangled.bindings.domain;

public class Parameter extends AbstractEntity {

	private TypeInfo typeInfo;
	private boolean mandatory;

	public TypeInfo getTypeInfo() {
		return typeInfo;
	}

	public void setTypeInfo(TypeInfo typeInfo) {
		this.typeInfo = typeInfo;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

}
