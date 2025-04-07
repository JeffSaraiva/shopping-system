package com.test.shopping_system.dto;

import java.util.List;

public class OrderRequestDTO {
    public String customerName;
    public String email;
    public String address;
    public String paymentMethod;
    public List<OrderItemDTO> items;

    public static class OrderItemDTO {
        public Long productId;
        public int quantity;
    }
}
