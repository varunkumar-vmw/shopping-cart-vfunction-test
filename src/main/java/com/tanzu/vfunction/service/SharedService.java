package com.tanzu.vfunction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharedService {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	public boolean isUserExists(final int userId) {
		return userService.isUserExists(userId);
	}

	public boolean isProductExists(final int productId) {
		return productService.isProductExists(productId);
	}
	
	public double getProductCost(final int productId) {
		return productService.getCost(productId);
	}
}
