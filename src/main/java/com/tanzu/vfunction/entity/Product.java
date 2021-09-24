package com.tanzu.vfunction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private String manufacturer;
	private double price;

	public Product() { }

	public Product(final String productName, final String manufacturer, final double price) {
		super();
		this.productName = productName;
		this.manufacturer = manufacturer;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(final String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

}
