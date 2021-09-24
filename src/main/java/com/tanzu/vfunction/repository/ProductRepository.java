package com.tanzu.vfunction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanzu.vfunction.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
