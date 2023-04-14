package com.restaurant.orderservice.controller.dto;

import java.util.List;

public record OrderRequest(
    List<String> menuNames
){}
