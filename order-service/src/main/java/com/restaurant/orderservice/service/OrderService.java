package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.dao.OrderDao;
import com.restaurant.orderservice.model.MenuConfig;
import com.restaurant.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderDao orderDao;
    private final MenuConfigService menuConfigService;

    public OrderService(OrderDao orderDao, MenuConfigService menuConfigService) {
        this.orderDao = orderDao;
        this.menuConfigService = menuConfigService;
    }

    public List<OrderResponse> getOrders(Integer offset, Integer limit) {
        if (offset == null){
            offset = 0;
        }
        if (limit == null){
            limit = 50;
        }
        return orderDao.selectOrders(offset, limit).stream()
                .map(f -> new OrderResponse(
                        f.getId(),
                        f.getPrice(),
                        f.getTimestamp(),
                        null))
                .collect(Collectors.toList());
    }

    public String addNewOrder(Map<String, Integer> orderedMenus){
        final String id = UUID.randomUUID().toString();
        double price = 0;
        for (Map.Entry<String, Integer> entry: orderedMenus.entrySet()) {
            final MenuConfig.Menu menu = menuConfigService.getMenus().stream()
                    .filter(m -> entry.getKey().equals(m.getName()))
                    .findFirst()
                    .orElseThrow();
            price += (menu.getPrice() * entry.getValue());
        }

        orderDao.insertOrder(new Order(id, price));
        return id;
    }

    public Order getOrderById(String orderId) {
        return orderDao.selectOrderById(orderId)
                .orElseThrow(() -> new IllegalStateException(String.format("Order with id %s not found", orderId)));
    }
}
