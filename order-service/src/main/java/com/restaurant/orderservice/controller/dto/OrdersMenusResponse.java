package com.restaurant.orderservice.controller.dto;

import java.sql.Timestamp;

public record OrdersMenusResponse(
        String orderId,
        String menuName,
        Timestamp timestamp
){}
