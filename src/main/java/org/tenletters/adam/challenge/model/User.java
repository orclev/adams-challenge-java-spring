package org.tenletters.adam.challenge.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name","id","otherField"})
public class User {
	public String name;
	public String id;
	public String otherField;
	
	public User() {
		this(null,null,null);
	}
	
	public User(final String name, final String id, final String otherField) {
		this.name = name;
		this.id = id;
		this.otherField = otherField;
	}
}
