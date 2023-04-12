package com.restaurant.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private long id;
    private String name;
    private long orderId;
    private Timestamp timestamp;

    public Menu(String name, long orderId, Timestamp timestamp) {
        this.name = name;
        this.orderId = orderId;
        this.timestamp = timestamp;
    }

    public Menu(String name, long orderId) {
        this.name = name;
        this.orderId = orderId;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
