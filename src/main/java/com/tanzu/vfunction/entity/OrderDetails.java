package com.tanzu.vfunction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderDetails {

	@Id
	@GeneratedValue
	private int orderDetailId;
	private int orderId;
	private int productId;
	private int quantity;
	private String status; /* Pending, Shipped, Delivered */
	private double cost;

	public OrderDetails() {

	}

	public OrderDetails(final int orderId, final int productId, final int quantity, final String status,
			final double cost) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.status = status;
		this.cost = cost;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderId(final int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(final int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(final double cost) {
		this.cost = cost;
	}

}
