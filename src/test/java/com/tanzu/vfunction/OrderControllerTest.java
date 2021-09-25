package com.tanzu.vfunction;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tanzu.vfunction.controller.CartController;
import com.tanzu.vfunction.controller.OrderController;
import com.tanzu.vfunction.controller.ProductController;
import com.tanzu.vfunction.controller.UserController;
import com.tanzu.vfunction.dto.CartItemDTO;
import com.tanzu.vfunction.dto.ProductDTO;
import com.tanzu.vfunction.dto.UserDTO;
import com.tanzu.vfunction.entity.Order;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderControllerTest extends ShoppingCartDemoApplicationTests {

	@Autowired
	private ProductController productController;

	@Autowired
	private UserController userController;

	@Autowired
	private CartController cartController;
	
	@Autowired
	private OrderController orderController;

	private static int userid;

	private static int productid1;
	private static int productid2;

	@Test
	public void contextTest() {
		assertThat(orderController).isNotNull();
	}
	
	@AfterEach
	public void afterEach() {
		orderController.deleteOrder();
	}

	@BeforeAll
	public void beforeAll() {
		final UserDTO user = new UserDTO();

		user.setName("Test");
		user.setAddress("Karnataka");

		userid = userController.createUser(user).getBody().getUserId();

		final ProductDTO product1 = new ProductDTO("Xperia", "Sony", 14500);
		final ProductDTO product2 = new ProductDTO("Pixel", "Google", 26000);

		productid1 = productController.createProduct(product1).getBody().getProductId();
		productid2 = productController.createProduct(product2).getBody().getProductId();
		
		final CartItemDTO cartItemDTO1 = new CartItemDTO(userid, productid1, 10, 10);
		final CartItemDTO cartItemDTO2 = new CartItemDTO(userid, productid2, 20, 20);
		
		cartController.addItemToCart(cartItemDTO1);
		cartController.addItemToCart(cartItemDTO2);
	}

	@Test
	public void saveCartAndGetOrder() {
		final ResponseEntity<?> save = cartController.saveCart();
		assertThat(save.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		final int orderId = (int) save.getBody();

		final ResponseEntity<?> response = orderController.getOrder(orderId);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		
		@SuppressWarnings("unchecked")
		final Optional<Order> order = (Optional<Order>) response.getBody();
		System.out.println(order.get());
		assertThat(order.get().getCost()).isEqualTo(546500.0);
		assertThat(order.get().getPaymentType()).isEqualTo("CASH");
		assertThat(order.get().getStatus()).isEqualTo("Ordered");
	}
}
