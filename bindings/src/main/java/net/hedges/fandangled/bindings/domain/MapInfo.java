package net.hedges.fandangled.bindings.domain;

public class MapInfo extends TypeInfo {

	private TypeInfo containedNameTypeInfo;
	private TypeInfo containedValueTypeInfo;

	public TypeInfo getContainedNameTypeInfo() {
		return containedNameTypeInfo;
	}

	public void setContainedNameTypeInfo(TypeInfo containedNameTypeInfo) {
		this.containedNameTypeInfo = containedNameTypeInfo;
	}

	public TypeInfo getContainedValueTypeInfo() {
		return containedValueTypeInfo;
	}

	public void setContainedValueTypeInfo(TypeInfo containedValueTypeInfo) {
		this.containedValueTypeInfo = containedValueTypeInfo;
	}

}
