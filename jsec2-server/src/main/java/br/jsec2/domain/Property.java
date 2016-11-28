package br.jsec2.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Property implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name="property")
	private List<PropertyItem> properties;

	public Property() {
		super();
	}

	public Property(List<PropertyItem> properties) {
		super();
		this.properties = properties;
	}

	public List<PropertyItem> getProperties() {
		if (this.properties == null) {
			this.properties = new ArrayList<>();
		}
		return properties;
	}

	public void setProperties(List<PropertyItem> properties) {
		this.properties = properties;
	}

}
