package com.tanzu.vfunction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzu.vfunction.dto.ProductDTO;
import com.tanzu.vfunction.entity.Product;
import com.tanzu.vfunction.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private LoggerService loggerService;

	public boolean isProductExists(final int productId) {
		return productRepository.findById(productId).isPresent();
	}

	public Product addProduct(final ProductDTO product) {
		loggerService.log("Adding product");
		return productRepository.save(new Product(product.getProductName(), product.getManufacturer(), product.getPrice()));
	}

	public void deleteProduct(final int productId) {
		loggerService.log("deleting product " + productId);
		productRepository.deleteById(productId);
	}

	public Optional<Product> getProduct(final int productId) {
		return productRepository.findById(productId);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public boolean updateProduct(final Product iProduct) {
		if (productRepository.existsById(iProduct.getProductId())) {
			productRepository.save(iProduct);
			return true;
		}

		return false;
	}

	public double getCost(final int productId) {
		return getProduct(productId).get().getPrice();
	}

	public void deleteAll() {
		productRepository.deleteAll();	
	}
}
