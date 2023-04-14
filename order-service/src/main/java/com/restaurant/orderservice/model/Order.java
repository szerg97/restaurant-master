package com.restaurant.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Order {
    private String id;
    private Timestamp timestamp;

    public Order(String id) {
        this.id = id;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
