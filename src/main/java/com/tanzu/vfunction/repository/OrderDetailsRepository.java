package com.tanzu.vfunction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanzu.vfunction.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
