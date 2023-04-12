package com.restaurant.warehouse.controller.dto;

import java.sql.Timestamp;

public record DrinkResponse(
        Long id,
        String name,
        int quantity,
        Timestamp timestamp
){}
