package com.tanzu.vfunction.dto;

public class CartItemDTO {

	private int userId;
	private int productId;
	private int quantity;
	private double discount;
	
	public CartItemDTO() {
		
	}
	
	public CartItemDTO(final int userId, final int productId, final int quantity, final double discount) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.discount = discount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
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

}
