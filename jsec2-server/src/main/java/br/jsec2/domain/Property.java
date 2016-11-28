package br.jsec2.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Property implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement
	private Map<String, Object> properties;

	public Property() {
		super();
	}

	public Property(Map<String, Object> properties) {
		super();
		this.properties = properties;
	}

	public Map<String, Object> getProperties() {
		if (this.properties == null) {
			this.properties = new HashMap<>();
		}
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
