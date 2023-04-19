package com.restaurant.apigateway.controller.dto;

import java.sql.Timestamp;

public record FoodResponse(
        Long id,
        String name,
        int quantity,
        Timestamp timestamp
){}
