package com.tanzu.vfunction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanzu.vfunction.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
