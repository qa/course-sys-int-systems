package com.redhat.brq.integration.exercise.shipment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "delivery")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentReply {
	@XmlAttribute
	private String status;

	public ShipmentReply() {
	}
	
	public ShipmentReply(String status) {
		this();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ShipmentReply [status=" + status + "]";
	}
	
}
