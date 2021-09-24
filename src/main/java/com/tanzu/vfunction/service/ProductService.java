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

	public boolean isProductExists(final int productId) {
		return productRepository.findById(productId).isPresent();
	}

	public Product addProduct(final ProductDTO product) {
		return productRepository.save(new Product(product.getProductName(), product.getManufacturer(), product.getPrice()));
	}

	public void deleteProduct(final int productId) {
		final Optional<Product> product = getProduct(productId);

		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
	}

	public Optional<Product> getProduct(final int productId) {
		return productRepository.findById(productId);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public boolean updateProduct(final Product iProduct) {
		final Optional<Product> product = getProduct(iProduct.getProductId());

		if (product.isPresent()) {
			productRepository.save(iProduct);
			return true;
		}

		return false;
	}

	public double getCost(final int productId) {
		return getProduct(productId).get().getPrice();
	}
}
