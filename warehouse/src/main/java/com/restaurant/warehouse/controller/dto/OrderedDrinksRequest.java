package com.restaurant.warehouse.controller.dto;

import java.util.List;

public record OrderedDrinksRequest(
        List<String> drinks
) {
}
