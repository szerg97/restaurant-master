package com.restaurant.warehouse.controller.dto;

import java.util.Map;

public record CheckedFoodsRequest(
        Map<String, Integer> foods
) {
}
