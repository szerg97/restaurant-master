package com.restaurant.orderservice.controller.dto;

import java.util.Map;

public record OrderedFoodsResponse(
        Map<String, Integer> foods
) {
}
