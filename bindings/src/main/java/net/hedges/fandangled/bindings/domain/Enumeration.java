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
