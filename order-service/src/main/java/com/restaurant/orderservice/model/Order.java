package com.restaurant.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Order {
    private long id;
    private Timestamp timestamp;

    public Order() {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
