package com.restaurant.orderservice.dao;

import com.restaurant.orderservice.model.OrdersMenus;

import java.util.List;
import java.util.Optional;

public interface OrdersMenusDao {
    List<OrdersMenus> selectAllOrdersMenus(int offset, int limit);
    int insertOrdersMenus(OrdersMenus food);
    int deleteOrdersMenus(Long id);
    Optional<OrdersMenus> selectOrdersMenus(long orderId, long menuId);
}
