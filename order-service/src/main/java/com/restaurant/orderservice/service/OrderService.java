package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.dao.OrderDao;
import com.restaurant.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
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
                        f.getTimestamp()))
                .collect(Collectors.toList());
    }

    public String addNewOrder(){
        String id = UUID.randomUUID().toString();
        orderDao.insertOrder(new Order(id));
        return id;
    }
}
