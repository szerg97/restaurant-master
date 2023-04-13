package com.restaurant.orderservice.controller;

import com.restaurant.orderservice.controller.dto.OrdersMenusResponse;
import com.restaurant.orderservice.service.OrdersMenusService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/order-service/menus")
@RequiredArgsConstructor
public class MenuController {

    private final OrdersMenusService ordersMenusService;

    @GetMapping("")
    public List<OrdersMenusResponse> getMenus(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit
    ){
        return ordersMenusService.getAllOrdersMenus(offset, limit);
    }
}
