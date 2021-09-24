package com.tanzu.vfunction.dto;

import java.util.List;

import com.tanzu.vfunction.entity.Order;
import com.tanzu.vfunction.entity.OrderDetails;

public class OrderDTO {

	private Order order;
	private List<OrderDetails> orderDetails;
	
	public OrderDTO() {
		
	}
	public OrderDTO(final Order order, final List<OrderDetails> orderDetails) {
		super();
		this.order = order;
		this.orderDetails = orderDetails;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(final Order Order) {
		this.order = Order;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(final List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
