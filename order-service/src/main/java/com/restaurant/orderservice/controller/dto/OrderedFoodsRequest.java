package com.restaurant.orderservice.controller.dto;

import java.util.List;

public record OrderedFoodsRequest(
        List<String> foods
) {
}
