package com.restaurant.orderservice.controller.dto;

import com.restaurant.orderservice.model.MenuConfig;

import java.util.List;

public record MenuResponse(
        long id,
        String name,
        List<String> foods,
        List<String> drinks,
        List<MenuConfig.Allergen> allergens,
        double price
){}
