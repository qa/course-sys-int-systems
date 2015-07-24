package com.redhat.brq.integration.exercise.shipment;

import com.redhat.brq.integration.exercise.model.Address;

public class ShipmentService {

	public ShipmentReply ship(Address address) {
		return new ShipmentReply("Gotham".equals(address.getCity()) ? "FAIL" : "OK"); 
	}
}
