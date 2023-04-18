package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.CheckedFoodsRequest;
import com.restaurant.orderservice.controller.dto.CheckedFoodsResponse;
import com.restaurant.orderservice.controller.dto.OrderRequest;
import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.controller.dto.OrderedFoodsRequest;
import com.restaurant.orderservice.controller.dto.OrderedFoodsResponse;
import com.restaurant.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderHandler {

    private final OrderService orderService;
    private final OrdersMenusService omService;
    private final MenuConfigService menuConfigService;
    private final WarehouseServiceExternal externalService;

    public OrderHandler(OrderService orderService, OrdersMenusService omService, MenuConfigService menuConfigService, WarehouseServiceExternal externalService) {
        this.orderService = orderService;
        this.omService = omService;
        this.menuConfigService = menuConfigService;
        this.externalService = externalService;
    }

    public OrderResponse handle(OrderRequest order) {
        Map<String, Integer> checkedFoods = checkFoods(order).foods();
        String orderId = orderService.addNewOrder(order.menus());
        omService.addNewOrdersMenus(orderId, order.menus());

        Map<String, Integer> orderedFoods = orderFoods(checkedFoods).foods();

        Order orderById = orderService.getOrderById(orderId);
        return new OrderResponse(
                orderId,
                orderById.getPrice(),
                orderById.getTimestamp(),
                orderedFoods);
    }

    private OrderedFoodsResponse orderFoods(Map<String, Integer> checkedFoods) {
        Map<String, Integer> foodsToOrder = new HashMap<>();
        checkedFoods.forEach((name, quantity) -> {
            if (quantity > 0){
                foodsToOrder.put(name, quantity);
            }
        });
        return externalService.orderFoods(new OrderedFoodsRequest(foodsToOrder));
    }

    private CheckedFoodsResponse checkFoods(OrderRequest order){
        return externalService.checkFoods(new CheckedFoodsRequest(menuConfigService.getFoods(order)));
    }
}
