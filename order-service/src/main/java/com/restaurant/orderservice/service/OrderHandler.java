package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.*;
import com.restaurant.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderHandler {

    @Value("${warehouse.order-foods-url}")
    private String orderUrl;
    @Value("${warehouse.check-foods-url}")
    private String checkUrl;
    private final OrderService orderService;
    private final OrdersMenusService omService;
    private final MenuConfigService configService;

    public OrderHandler(OrderService orderService, OrdersMenusService omService, MenuConfigService configService) {
        this.orderService = orderService;
        this.omService = omService;
        this.configService = configService;
    }

    public OrderResponse handle(OrderRequest order) {
        Map<String, Integer> checkedFoods = checkFoods(order).foods();
        String orderId = orderService.addNewOrder(order.menus());
        omService.addNewOrdersMenus(orderId, order.menus());

        //updateFoods(order);

        Order orderById = orderService.getOrderById(orderId);
        return new OrderResponse(orderId, orderById.getPrice(), orderById.getTimestamp());
    }

    private CheckedFoodsResponse checkFoods(OrderRequest order){
        return new RestTemplate()
                .postForEntity(checkUrl,
                        new CheckedFoodsRequest(configService.getFoods(order)),
                        CheckedFoodsResponse.class)
                .getBody();
    }
}
