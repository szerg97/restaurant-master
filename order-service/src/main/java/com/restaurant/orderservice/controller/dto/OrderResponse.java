package com.restaurant.orderservice.controller.dto;

import java.sql.Timestamp;

public record OrderResponse(
        String id,
        double price,
        Timestamp timestamp
){}
