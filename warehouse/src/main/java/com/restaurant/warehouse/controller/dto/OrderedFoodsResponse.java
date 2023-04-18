package com.restaurant.warehouse.controller.dto;

import java.util.Map;

public record OrderedFoodsResponse(
        Map<String, Integer> foods
) {
}
