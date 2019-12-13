package com.vikash.stationary.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Order {

	private long id;
	private Map<Status, HashSet<Product>> products;
	private Date date;
	private String userId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Map<Status, HashSet<Product>> getProducts() {
		return products;
	}
	public void setProducts(Map<Status, HashSet<Product>> products) {
		this.products = products;
	}

	
}
