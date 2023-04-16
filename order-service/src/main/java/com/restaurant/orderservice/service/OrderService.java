package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.dao.OrderDao;
import com.restaurant.orderservice.model.MenuConfig;
import com.restaurant.orderservice.model.Order;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderDao orderDao;
    private final MenuConfigService configService;

    public OrderService(OrderDao orderDao, MenuConfigService configService) {
        this.orderDao = orderDao;
        this.configService = configService;
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
                        f.getTimestamp()))
                .collect(Collectors.toList());
    }

    public String addNewOrder(List<String> menuNames){
        String id = UUID.randomUUID().toString();
        double price = 0;
        for (String n: menuNames) {
            price += configService.getMenus().stream()
                    .filter(m -> m.getName().equals(n))
                    .mapToDouble(MenuConfig.Menu::getPrice)
                    .sum();
        }

        orderDao.insertOrder(new Order(id, price));
        return id;
    }

    public Order getOrderById(String orderId) {
        return orderDao.selectOrderById(orderId)
                .orElseThrow(() -> new IllegalStateException(String.format("Order with id %s not found", orderId)));
    }
}
