package com.redhat.brq.integration.exercise.model;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ";")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = -8796388121191014358L;

	@DataField(pos = 1)
	private long articleId;

	@DataField(pos = 2)
	private int count;
	
	@DataField(pos = 3)
	private double unitPrice;

	public long getArticleId() {
		return articleId;
	}
	
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public double getPrice() {
		return count * getUnitPrice();
	}

	@Override
	public String toString() {
		return "OrderItem [articleId=" + articleId + ", count=" + count
				+ ", unitPrice=" + unitPrice + "]";
	}
}
