package com.tanzu.vfunction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanzu.vfunction.dto.CartDTO;
import com.tanzu.vfunction.dto.CartItemDTO;
import com.tanzu.vfunction.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping
	public ResponseEntity<?> getCart() {
		CartDTO cart = null;

		try {
			cart = cartService.getCart();
		} catch (final Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addItemToCart(@RequestBody final CartItemDTO cartItemDTO) {
		try {
			cartService.addItemsToCart(cartItemDTO);
		} catch (final Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("{productId}")
	public ResponseEntity<?> deleteCartItem(@PathVariable final int productId) {
		cartService.removeItemFromCart(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteCart() {
		try {
			cartService.deleteCart();
		} catch (final Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("buy")
	public ResponseEntity<?> saveCart() {
		final int orderID = cartService.saveCart();
		return new ResponseEntity<>(orderID, HttpStatus.OK);
	}
}
