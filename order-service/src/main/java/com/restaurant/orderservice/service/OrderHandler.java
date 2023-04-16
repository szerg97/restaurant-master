package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.OrderRequest;
import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.controller.dto.OrderedFoodsRequest;
import com.restaurant.orderservice.exception.ServiceNotAvailableException;
import com.restaurant.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderHandler {

    @Value("${warehouse.foods-url}")
    private String url;
    private final OrderService orderService;
    private final OrdersMenusService omService;
    private final MenuConfigService configService;

    public OrderHandler(OrderService orderService, OrdersMenusService omService, MenuConfigService configService) {
        this.orderService = orderService;
        this.omService = omService;
        this.configService = configService;
    }

    public OrderResponse handle(OrderRequest order) {
        boolean isSuccessful = Boolean.TRUE.equals(new RestTemplate()
                .postForEntity(url,
                        new OrderedFoodsRequest(configService.getFoodNames(order)),
                        Boolean.class)
                .getBody());
        if (isSuccessful){
            String orderId = orderService.addNewOrder(order.menuNames());
            omService.addNewOrdersMenus(orderId, order.menuNames());
            Order orderById = orderService.getOrderById(orderId);
            return new OrderResponse(orderId, orderById.getPrice(), orderById.getTimestamp());
        }
        throw new ServiceNotAvailableException("Could not reach service on URL: " + url);
    }
}
