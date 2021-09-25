package com.tanzu.vfunction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanzu.vfunction.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("{orderid}")
	public ResponseEntity<?> getOrder(final int orderid) {
		try {
			return new ResponseEntity<>(orderService.getOrder(orderid), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No order is created", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteOrder() {
		orderService.deleteOrder();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
