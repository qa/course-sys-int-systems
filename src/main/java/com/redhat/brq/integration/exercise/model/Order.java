package com.redhat.brq.integration.exercise.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 9025355997861450821L;

	private long id;
	private List<OrderItem> items;
	private Address address;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}
	
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public double getTotalPrice() {
		return items.stream().mapToDouble(OrderItem::getPrice).sum();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", items=" + items + ", address=" + address
				+ "]";
	}
}
