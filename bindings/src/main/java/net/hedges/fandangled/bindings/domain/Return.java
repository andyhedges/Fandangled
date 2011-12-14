package net.hedges.fandangled.bindings.domain;

public class Return {

	private TypeInfo typeInfo;
	private String description;

	public TypeInfo getTypeInfo() {
		return typeInfo;
	}

	public void setTypeInfo(TypeInfo typeInfo) {
		this.typeInfo = typeInfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Return [typeInfo=" + typeInfo + ", description=" + description
				+ "]";
	}
	
	

}
