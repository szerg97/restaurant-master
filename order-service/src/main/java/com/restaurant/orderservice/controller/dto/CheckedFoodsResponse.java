package com.restaurant.orderservice.controller.dto;

import java.util.Map;

public record CheckedFoodsResponse(
        Map<String, Integer> foods
) {
}
