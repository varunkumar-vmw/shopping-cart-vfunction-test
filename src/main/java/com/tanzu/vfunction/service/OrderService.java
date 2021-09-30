package com.tanzu.vfunction.service;

import static com.tanzu.vfunction.util.Utility.getPriceAfterDiscount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzu.vfunction.dto.OrderDTO;
import com.tanzu.vfunction.entity.Order;
import com.tanzu.vfunction.entity.OrderDetails;
import com.tanzu.vfunction.repository.OrderDetailsRepository;
import com.tanzu.vfunction.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private LoggerService loggerService;

	private Order order;
	private Map<Integer, OrderDetails> orderDetailsMap = new HashMap<>();
	
	public Order createOrder(final int userId) {
		loggerService.log("Create new order");
		deleteOrder();
		order = orderRepository.save(new Order(userId));
		return order;
	}
	
	public void addOrders(final int productId, final int quantity, final double cost, final double discount) {
		orderDetailsMap.put(productId, new OrderDetails(order.getOrderId(), productId, quantity, "Pending", getPriceAfterDiscount(cost, discount) * quantity));
	}
	
	public OrderDTO getOrderDTO() throws Exception {
		if (order == null || orderDetailsMap.size() == 0) {
			throw new Exception("Order is empty");
		}

		final List<OrderDetails> orderDetails = new ArrayList<>();
		orderDetailsMap.values().parallelStream().forEach(detail -> orderDetails.add(detail));
		return new OrderDTO(order, orderDetails);
	}
	
	public Optional<Order> getOrder(final int orderId) throws Exception {
		loggerService.log("Get order for id "+ orderId);
		return orderRepository.findById(orderId);
	}

	public void removeItemFromOrder(final int productId) {
		orderDetailsMap.remove(productId);
	}
	
	public void deleteOrder() {
		order = null;
		orderDetailsMap.clear();
	}

	public void saveOrder() {
		final double[] totalCost = new double[1];

		orderDetailsMap.values().parallelStream().forEach(orderDetail -> {
			orderDetailsRepository.save(orderDetail);
			totalCost[0] = totalCost[0] + orderDetail.getCost();
		});
		
		order.setPaymentType("CASH");
		order.setStatus("Ordered");
		order.setCost(totalCost[0]);
		order = orderRepository.save(order);
	}
	
}
