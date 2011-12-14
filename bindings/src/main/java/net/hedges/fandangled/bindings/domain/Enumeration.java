package net.hedges.fandangled.bindings.domain;

import java.util.ArrayList;
import java.util.List;

public class Enumeration extends AbstractEntity{

	private String name;
	private List<Value> values = new ArrayList<Value>();
	private Version since;
	private String description;

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public Version getSince() {
		return since;
	}

	public void setSince(Version since) {
		this.since = since;
	}

}
