package com.test.shopping_system.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne
 private Product product;

 private int quantity;
 private BigDecimal price;

 @ManyToOne
 @JoinColumn(name = "order_id")
 private Order order;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public BigDecimal getPrice() {
	return price;
}

public void setPrice(BigDecimal price) {
	this.price = price;
}

public Order getOrder() {
	return order;
}

public void setOrder(Order order) {
	this.order = order;
}

 
}