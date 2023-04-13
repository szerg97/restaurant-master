package com.restaurant.orderservice.controller.dto;

import java.sql.Timestamp;

public record OrdersMenusResponse(
        long orderId,
        String menuName,
        Timestamp timestamp
){}
