package com.restaurant.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersMenus {
    private String orderId;
    private long menuId;
    private Timestamp timestamp;

    public OrdersMenus(String orderId, long menuId) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
