package com.restaurant.orderservice.controller;

import com.restaurant.orderservice.controller.dto.OrderRequest;
import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.controller.dto.OrderedFoodsRequest;
import com.restaurant.orderservice.service.MenuConfigService;
import com.restaurant.orderservice.service.OrderService;
import com.restaurant.orderservice.service.OrdersMenusService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/order-service/orders")
public class OrderController {


    private static final String HOST = "localhost";
    private static final String RESOURCE_URL = "http://" + HOST + ":8081/api/v1/warehouse/foods";
    private final OrderService orderService;
    private final OrdersMenusService omService;
    private final MenuConfigService configService;

    public OrderController(OrderService orderService, OrdersMenusService omService, MenuConfigService configService) {
        this.orderService = orderService;
        this.omService = omService;
        this.configService = configService;
    }

    @GetMapping("")
    public List<OrderResponse> getOrders(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit
    ){
        return orderService.getOrders(offset, limit);
    }

    @PostMapping("")
    public Boolean placeOrder(@RequestBody OrderRequest order){
        log.info(order.toString());
        OrderedFoodsRequest request = new OrderedFoodsRequest(configService.getFoodNames(order));
        RestTemplate restTemplate = new RestTemplate();
        boolean isSuccessful = restTemplate.postForEntity(RESOURCE_URL + "/order", request, Boolean.class).getBody();
        if (isSuccessful){
            String orderId = orderService.addNewOrder(order.menuNames());
            omService.addNewOrdersMenus(orderId, order.menuNames());
            return true;
        }
        return false;
    }
}
