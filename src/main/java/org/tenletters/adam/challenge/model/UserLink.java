package org.tenletters.adam.challenge.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name","id"})
public class UserLink {
	public String name;
	public String id;
	
	public UserLink() {
		this(null, null);
	}
	
	public UserLink(final String name, final String id) {
		this.name = name;
		this.id = id;
	}
}
