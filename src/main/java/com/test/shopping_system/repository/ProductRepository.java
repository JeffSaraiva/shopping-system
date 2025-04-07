package com.test.shopping_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.shopping_system.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
    Optional<Product> findByName(String name);

}