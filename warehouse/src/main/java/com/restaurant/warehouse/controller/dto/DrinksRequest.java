package com.restaurant.warehouse.controller.dto;

import java.util.List;

public record DrinksRequest(
        List<String> drinks
) {
}
