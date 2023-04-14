package com.restaurant.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Order {
    private String id;
    private double price;
    private Timestamp timestamp;

    public Order(String id, double price) {
        this.id = id;
        this.price = price;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
