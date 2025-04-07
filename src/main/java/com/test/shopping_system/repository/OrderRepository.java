package com.test.shopping_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.shopping_system.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

