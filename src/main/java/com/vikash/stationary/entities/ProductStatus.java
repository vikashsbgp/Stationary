package com.vikash.stationary.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ProductStatus extends AbstractEntity {
	
	@OneToOne
	private Order order;
	
	private Status status;
	
	@OneToOne
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ProductStatus [order=" + order + ", status=" + status + ", product=" + product + "]";
	}

}
