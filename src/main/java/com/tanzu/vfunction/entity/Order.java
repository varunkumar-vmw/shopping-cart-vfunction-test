package com.tanzu.vfunction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue
	private int orderId;
	private int userId;
	private String status; /* Pending, Shipped, Delivered */
	private double cost;
	private String paymentType; /* Cash, Card, UPI, NetBanking */

	public Order() {
	}

	public Order(final int userId) {
		super();
		this.userId = userId;
	}

	public Order(final int userId, final String status, final double cost, final String paymentType) {
		super();
		this.userId = userId;
		this.status = status;
		this.cost = cost;
		this.paymentType = paymentType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(final double cost) {
		this.cost = cost;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(final String paymentType) {
		this.paymentType = paymentType;
	}

	public int getOrderId() {
		return orderId;
	}

}
