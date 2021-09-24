package com.tanzu.vfunction.dto;

import java.util.List;

import com.tanzu.vfunction.entity.Cart;
import com.tanzu.vfunction.entity.CartDetails;

public class CartDTO {
	private Cart cart;
	private List<CartDetails> cartDetails;
	
	public CartDTO() {
		
	}
	public CartDTO(final Cart cart, final List<CartDetails> cartDetails) {
		super();
		this.cart = cart;
		this.cartDetails = cartDetails;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(final Cart cart) {
		this.cart = cart;
	}

	public List<CartDetails> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(final List<CartDetails> cartDetails) {
		this.cartDetails = cartDetails;
	}
}
