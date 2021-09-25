package com.tanzu.vfunction.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanzu.vfunction.dto.ProductDTO;
import com.tanzu.vfunction.entity.Product;
import com.tanzu.vfunction.entity.User;
import com.tanzu.vfunction.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("{productid}")
	public ResponseEntity<?> getProduct(@PathVariable final int productid) {
		final Optional<Product> product = productService.getProduct(productid);

		if (product.isEmpty()) {
			return new ResponseEntity<>(productid + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("all")
	public ResponseEntity<?> getProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody final ProductDTO product) {
		final Product productCreated = productService.addProduct(product);
		return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
	}

	@DeleteMapping("{productid}")
	public ResponseEntity<?> deleteUser(@PathVariable final int productid) {
		productService.deleteProduct(productid);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> updateProduct(@RequestBody final Product product) {
		final boolean isUpdated = productService.updateProduct(product);
		return new ResponseEntity<>(isUpdated ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("deleteall")
	public ResponseEntity<?> deleteAll() {
		productService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
