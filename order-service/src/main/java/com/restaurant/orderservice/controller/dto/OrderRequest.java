package com.restaurant.orderservice.controller.dto;

import java.util.Map;

public record OrderRequest(
    Map<String, Integer> menus
){}
