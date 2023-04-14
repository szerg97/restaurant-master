package com.restaurant.orderservice.controller.dto;

import java.util.List;

public record OrderedDrinksRequest(
        List<String> drinks
) {
}
