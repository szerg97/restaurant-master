package com.restaurant.orderservice.controller.dto;

import java.sql.Timestamp;

public record OrderResponse(
        long id,
        Timestamp timestamp
){}
