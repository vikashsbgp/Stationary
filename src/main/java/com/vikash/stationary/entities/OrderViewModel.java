package com.vikash.stationary.entities;

public class OrderViewModel {

	private long id;
	private Product product;
	private Status status;
	private Status prevStatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Status getPrevStatus() {
		return prevStatus;
	}
	public void setPrevStatus(Status prevStatus) {
		this.prevStatus = prevStatus;
	}
	
}
