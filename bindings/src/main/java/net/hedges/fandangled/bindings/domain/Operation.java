/*
   Copyright 2010 Andy Hedges <andy@hedges.net>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package net.hedges.fandangled.bindings.domain;

import java.util.ArrayList;
import java.util.List;

public class Operation extends AbstractEntity{

	private List<Parameter> parameters = new ArrayList<Parameter>();
	private Return _return;
	private Since since;
	private List<ThrownException> exceptions = new ArrayList<ThrownException>();
    private boolean async = false;

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public Return getReturn() {
		return _return;
	}

	public void setReturn(Return $return) {
		this._return = $return;
	}

	public Since getSince() {
		return since;
	}

	public void setSince(Since since) {
		this.since = since;
	}

	public List<ThrownException> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<ThrownException> exceptions) {
		this.exceptions = exceptions;
	}

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

}
