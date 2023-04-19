package com.restaurant.apigateway.controller.dto;

import java.sql.Timestamp;

public record DrinkResponse(
        Long id,
        String name,
        int quantity,
        Timestamp timestamp
){}
