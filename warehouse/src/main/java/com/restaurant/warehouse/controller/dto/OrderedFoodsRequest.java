package com.restaurant.warehouse.controller.dto;

import java.util.Map;

public record OrderedFoodsRequest(
        Map<String, Integer> foods
) {
}
