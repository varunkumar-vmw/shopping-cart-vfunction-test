package com.tanzu.vfunction.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzu.vfunction.dto.CartDTO;
import com.tanzu.vfunction.dto.CartItemDTO;
import com.tanzu.vfunction.entity.Cart;
import com.tanzu.vfunction.entity.CartDetails;
import com.tanzu.vfunction.entity.Order;
import com.tanzu.vfunction.repository.CartDetailsRepository;
import com.tanzu.vfunction.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartDetailsRepository cartDetailsRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private SharedService sharedService;

	@Autowired
	private LoggerService loggerService;

	private Cart cart;
	private Map<Integer, CartDetails> cartDetailsMap = new HashMap<>();

	public void createCart(final int userId) throws Exception {
		if (cart != null) {
			throw new Exception("Cart already exists, Only one cart for user");
		}
		cart = cartRepository.save(new Cart(userId));
	}

	public void addItemsToCart(final CartItemDTO cartItemDTO) throws Exception {
		loggerService.log("Adding Item to cart");
		if ( ! sharedService.isUserExists(cartItemDTO.getUserId())) {
			throw new Exception("User not found");
		}

		if ( ! sharedService.isProductExists(cartItemDTO.getProductId())) {
			throw new Exception("Product does not exists");
		}

		if (cart == null) {
			createCart(cartItemDTO.getUserId());
		}

		cartDetailsMap.put(cartItemDTO.getProductId(), new CartDetails(cart.getCartId(), 
																	cartItemDTO.getProductId(),
																	cartItemDTO.getQuantity(), 
																	cartItemDTO.getDiscount(), 
																	sharedService.getProductCost(cartItemDTO.getProductId())));
	}

	public CartDTO getCart() throws Exception {
		if (cart == null || cartDetailsMap.size() == 0) {
			throw new Exception("Cart is empty");
		}

		final List<CartDetails> cartDetails = new ArrayList<>();
		cartDetailsMap.values().parallelStream().forEach(detail -> cartDetails.add(detail));
		return new CartDTO(cart, cartDetails);
	}

	public void removeItemFromCart(final int productId) {
		loggerService.log("Removing Item to cart");
		cartDetailsMap.remove(productId);
	}

	public void deleteCart() {
		loggerService.log("Deleting cart");
		cart = null;
		cartDetailsMap.clear();
	}

	public int saveCart() {
		final Order order = orderService.createOrder(cart.getUserId());

		cartDetailsMap.values().parallelStream().forEach(cartDetail -> {
			cartDetailsRepository.save(cartDetail);
			orderService.addOrders(cartDetail.getProductId(), cartDetail.getQuantity(), cartDetail.getCost(), cartDetail.getDiscount());
		});

		orderService.saveOrder();
		return order.getOrderId();
	}
}
