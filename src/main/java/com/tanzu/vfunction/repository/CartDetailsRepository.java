package com.tanzu.vfunction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanzu.vfunction.entity.CartDetails;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {

}
