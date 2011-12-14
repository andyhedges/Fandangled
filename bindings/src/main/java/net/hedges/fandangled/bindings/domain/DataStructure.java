package net.hedges.fandangled.bindings.domain;

import java.util.ArrayList;
import java.util.List;

public class DataStructure extends AbstractEntity {

	private List<Parameter> parameters = new ArrayList<Parameter>();
	private Since since;

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public Since getSince() {
		return since;
	}

	public void setSince(Since since) {
		this.since = since;
	}

}
