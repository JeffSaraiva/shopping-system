package com.test.shopping_system.controller;

import org.springframework.web.bind.annotation.*;

import com.test.shopping_system.dto.OrderRequestDTO;
import com.test.shopping_system.model.Order;
import com.test.shopping_system.model.OrderItem;
import com.test.shopping_system.model.Product;
import com.test.shopping_system.repository.OrderRepository;
import com.test.shopping_system.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequestDTO dto) {
        Order order = new Order();
        order.setCustomerName(dto.customerName);
        order.setEmail(dto.email);
        order.setAddress(dto.address);
        order.setPaymentMethod(dto.paymentMethod);

        List<OrderItem> orderItems = dto.items.stream().map(itemDTO -> {
            Product product = productRepository.findById(itemDTO.productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < itemDTO.quantity) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
            }

            product.setStock(product.getStock() - itemDTO.quantity); // 💥 Diminui o estoque
            productRepository.save(product); // 💾 Salva o novo estoque no banco

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.quantity);
            item.setPrice(product.getPrice().multiply(BigDecimal.valueOf(itemDTO.quantity)));
            item.setOrder(order);
            return item;
        }).toList();

        order.setItems(orderItems);
        return orderRepository.save(order);
    }
}
