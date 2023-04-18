package com.restaurant.orderservice.controller.dto;

import java.sql.Timestamp;
import java.util.Map;

public record OrderResponse(
        String id,
        double price,
        Timestamp timestamp,
        Map<String, Integer> foods
){}
