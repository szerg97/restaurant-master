package com.restaurant.orderservice.controller.dto;

import java.util.List;

public record MenuResponse(
        long id,
        String name,
        List<String> foods,
        List<String> drinks,
        List<String> allergens,
        double price
){}
