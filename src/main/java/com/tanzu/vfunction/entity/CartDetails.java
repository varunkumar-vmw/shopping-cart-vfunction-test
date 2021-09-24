package com.tanzu.vfunction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CartDetails {

	@Id
	@GeneratedValue
	private int cartDetailId;
	private int cartId;
	private int productId;
	private double cost;
	private int quantity;
	private double discount;

	public CartDetails() {
	}

	public CartDetails(final int cartId, final int productId, final int quantity, final double discount, final double cost) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
		this.discount = discount;
		this.cost = cost;
	}

	public int getCartDetailId() {
		return cartDetailId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(final int cartId) {
		this.cartId = cartId;
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(final double discount) {
		this.discount = discount;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(final double cost) {
		this.cost = cost;
	}

}
