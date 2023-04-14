package com.restaurant.warehouse.controller.dto;

import java.util.List;

public record FoodsRequest(
        List<String> foods
) {
}
