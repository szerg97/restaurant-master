package com.restaurant.orderservice.controller.dto;

import java.util.Map;

public record OrderedFoodsRequest(
        Map<String, Integer> foods
) {
}
