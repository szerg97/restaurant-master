package com.restaurant.orderservice.controller;

import com.restaurant.orderservice.controller.dto.OrderRequest;
import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.service.MenuConfigService;
import com.restaurant.orderservice.service.OrderHandler;
import com.restaurant.orderservice.service.OrderService;
import com.restaurant.orderservice.service.OrdersMenusService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/order-service/orders")
public class OrderController {
    private final OrderService orderService;

    private final OrderHandler orderHandler;

    public OrderController(OrderService orderService, OrdersMenusService omService, MenuConfigService configService, OrderHandler orderHandler) {
        this.orderService = orderService;
        this.orderHandler = orderHandler;
    }

    @GetMapping("")
    public List<OrderResponse> getOrders(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit
    ){
        return orderService.getOrders(offset, limit);
    }

    @PostMapping("")
    public OrderResponse placeNewOrder(@RequestBody OrderRequest order){
        log.info(order.toString());
        return orderHandler.handle(order);
    }
}
