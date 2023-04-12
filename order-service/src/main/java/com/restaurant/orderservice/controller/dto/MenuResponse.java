package com.restaurant.orderservice.controller.dto;

import java.sql.Timestamp;

public record MenuResponse(
        long id,
        String name,
        long orderId,
        Timestamp timestamp
){}
