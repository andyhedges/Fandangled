package net.hedges.fandangled.bindings.domain;

public class ThrownException {
	private String description;
	private String typeName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "ThrownException [description=" + description + ", typeName=" + typeName + "]";
	}
}
