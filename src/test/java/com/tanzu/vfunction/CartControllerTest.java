package com.tanzu.vfunction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tanzu.vfunction.controller.CartController;
import com.tanzu.vfunction.controller.ProductController;
import com.tanzu.vfunction.controller.UserController;
import com.tanzu.vfunction.dto.CartDTO;
import com.tanzu.vfunction.dto.CartItemDTO;
import com.tanzu.vfunction.dto.ProductDTO;
import com.tanzu.vfunction.dto.UserDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartControllerTest extends ShoppingCartDemoApplicationTests {

	@Autowired
	private ProductController productController;

	@Autowired
	private UserController userController;

	@Autowired
	private CartController cartController;

	private static int userid;

	private static int productid1;
	private static int productid2;

	@Test
	public void contextTest() {
		assertThat(cartController).isNotNull();
	}

	@AfterEach
	public void afterEach() {
		cartController.deleteCart();
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
	}

	@Test
	public void addItemsToCartAndGetItems() {
		final CartItemDTO cartItemDTO = new CartItemDTO(userid, productid1, 10, 10);
		final ResponseEntity<?> cartItem = cartController.addItemToCart(cartItemDTO);

		assertThat(cartItem.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		final ResponseEntity<?> response = cartController.getCart();
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		final CartDTO cart = (CartDTO) response.getBody();
		assertThat(cart.getCartDetails().size()).isEqualTo(1);
		assertThat(cart.getCart().getUserId()).isEqualTo(userid);
		assertThat(cart.getCartDetails().get(0).getProductId()).isEqualTo(productid1);
		assertThat(cart.getCartDetails().get(0).getCost()).isEqualTo(14500);
		assertThat(cart.getCartDetails().get(0).getDiscount()).isEqualTo(10);
		assertThat(cart.getCartDetails().get(0).getQuantity()).isEqualTo(10);
	}
	
	@Test
	public void addItemsToCartWithInvalidUserId() {
		final CartItemDTO cartItemDTO = new CartItemDTO(999, productid1, 10, 10);
		final ResponseEntity<?> cartItem = cartController.addItemToCart(cartItemDTO);
		assertThat(cartItem.getStatusCode()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(cartItem.getBody()).isEqualTo("User not found");
	}
	
	@Test
	public void addItemsToCartWithInvalidProductId() {
		final CartItemDTO cartItemDTO = new CartItemDTO(userid, 999, 10, 10);
		final ResponseEntity<?> cartItem = cartController.addItemToCart(cartItemDTO);
		assertThat(cartItem.getStatusCode()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(cartItem.getBody()).isEqualTo("Product does not exists");
	}
	
	@Test
	public void getCartEmptyException() {
		final ResponseEntity<?> response = cartController.getCart();
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(response.getBody()).isEqualTo("Cart is empty");
	}
	
	@Test
	public void deleteCartItem() {
		final CartItemDTO cartItemDTO = new CartItemDTO(userid, productid1, 10, 10);
		cartController.addItemToCart(cartItemDTO);
		
		final ResponseEntity<?> deletedCartItem = cartController.deleteCartItem(productid1);
		assertThat(deletedCartItem.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		
		final ResponseEntity<?> response = cartController.getCart();
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(response.getBody()).isEqualTo("Cart is empty");
	}
	
	@Test
	public void getCartItems() {
		final CartItemDTO cartItemDTO1 = new CartItemDTO(userid, productid1, 10, 10);
		final CartItemDTO cartItemDTO2 = new CartItemDTO(userid, productid2, 20, 20);
		
		cartController.addItemToCart(cartItemDTO1);
		cartController.addItemToCart(cartItemDTO2);
		
		final ResponseEntity<?> response = cartController.getCart();
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		final CartDTO cart = (CartDTO) response.getBody();
		assertThat(cart.getCartDetails().size()).isEqualTo(2);
	}
}
