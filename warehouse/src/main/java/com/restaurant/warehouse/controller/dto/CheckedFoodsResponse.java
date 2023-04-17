package com.restaurant.warehouse.controller.dto;

import java.util.Map;

public record CheckedFoodsResponse(
        Map<String, Integer> foods
) {
}
