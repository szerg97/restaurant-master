package com.restaurant.orderservice.controller.dto;

import java.util.Map;

public record CheckedFoodsRequest(
        Map<String, Integer> foods
) {
}
