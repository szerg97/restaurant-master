package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.OrderResponse;
import com.restaurant.orderservice.dao.OrderDao;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
