package com.restaurant.warehouse.controller.dto;

import java.util.List;

public record OrderedFoodsRequest(
        List<String> foods
) {
}
