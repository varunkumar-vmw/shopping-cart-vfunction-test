package com.tanzu.vfunction;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tanzu.vfunction.controller.ProductController;
import com.tanzu.vfunction.dto.ProductDTO;
import com.tanzu.vfunction.entity.Product;
import com.tanzu.vfunction.entity.User;

public class ProductContollerTest extends ShoppingCartDemoApplicationTests {

	@Autowired
	private ProductController productController;

	@AfterEach
	public void afterEach() {
		productController.deleteAll();
	}

	@Test
	public void contextTest() {
		assertThat(productController).isNotNull();
	}

	@Test
	public void productCreationAndGetProduct() {
		final ProductDTO product = new ProductDTO("Xperia", "Sony", 14500);
		final ResponseEntity<Product> createdProduct = productController.createProduct(product);
		final ResponseEntity<?> getProductByID = productController.getProduct(createdProduct.getBody().getProductId());

		assertThat(createdProduct.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
		assertThat(getProductByID.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		@SuppressWarnings("unchecked")
		final Product response = ((Optional<Product>) getProductByID.getBody()).get();
		assertThat(response.getProductName()).isEqualTo("Xperia");
		assertThat(response.getManufacturer()).isEqualTo("Sony");
		assertThat(response.getPrice()).isEqualTo(14500);
	}

	@Test
	public void productCreationAndGetAllProduct() {
		final ProductDTO product1 = new ProductDTO("Xperia", "Sony", 14500);
		final ProductDTO product2 = new ProductDTO("Pixel", "Google", 26000);

		productController.createProduct(product1);
		productController.createProduct(product2);

		final ResponseEntity<?> products = productController.getProducts();

		assertThat(products.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		@SuppressWarnings("unchecked")
		final List<User> response = ((List<User>) products.getBody());
		assertThat(response.size()).isEqualTo(2);
	}

	@Test
	public void deleteProduct() {
		final ProductDTO product = new ProductDTO("Xperia", "Sony", 14500);
		final ResponseEntity<Product> createdProduct = productController.createProduct(product);

		@SuppressWarnings("unchecked")
		final Product getProductByID = ((Optional<Product>) productController
				.getProduct(createdProduct.getBody().getProductId())
				.getBody()).get();
		assertThat(getProductByID.getProductName()).isEqualTo("Xperia");

		/* Call delete */
		productController.deleteUser(createdProduct.getBody().getProductId());
		final ResponseEntity<?> getProductByID2 = productController.getProduct(createdProduct.getBody().getProductId());
		assertThat(getProductByID2.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void updateProduct() {
		final ProductDTO product = new ProductDTO("Xperia", "Sony", 14500);
		final Product createdProduct = productController.createProduct(product).getBody();

		createdProduct.setPrice(14000);

		productController.updateProduct(createdProduct);

		@SuppressWarnings("unchecked")
		final Product getProductByID = ((Optional<Product>) productController
				.getProduct(createdProduct.getProductId())
				.getBody()).get();
		assertThat(getProductByID.getPrice()).isEqualTo(14000);
	}
}
