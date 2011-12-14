package net.hedges.fandangled.bindings.domain;

public class CollectionInfo extends TypeInfo {

	private TypeInfo containedType;

	public TypeInfo getContainedTypeInfo() {
		return containedType;
	}

	public void setContainedTypeInfo(TypeInfo containedType) {
		this.containedType = containedType;
	}

}
