package com.restaurant.orderservice.dao;

import com.restaurant.orderservice.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    List<Order> selectOrders(int offset, int limit);
    int insertOrder(Order order);
    int deleteOrder(String id);
    Optional<Order> selectOrderById(String id);
}
