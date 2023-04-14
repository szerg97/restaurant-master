package com.restaurant.orderservice.controller;

import com.restaurant.orderservice.controller.dto.OrderRequest;
import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.service.OrderService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/order-service/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public List<OrderResponse> getOrders(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit
    ){
        return orderService.getOrders(offset, limit);
    }

    @PostMapping("")
    public void placeOrder(@RequestBody OrderRequest order){
        log.info(order.toString());
        //orderService.placeOrder();
    }
}
