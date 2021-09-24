package com.tanzu.vfunction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanzu.vfunction.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
