package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.OrdersMenusResponse;
import com.restaurant.orderservice.dao.OrdersMenusDao;
import com.restaurant.orderservice.model.OrdersMenus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersMenusService {

    private final OrdersMenusDao ordersMenusDao;
    private final MenuConfigService menuConfigService;

    public OrdersMenusService(OrdersMenusDao ordersMenusDao, MenuConfigService menuConfigService) {
        this.ordersMenusDao = ordersMenusDao;
        this.menuConfigService = menuConfigService;
    }

    public List<OrdersMenusResponse> getAllOrdersMenus(Integer offset, Integer limit) {
        if (offset == null){
            offset = 0;
        }
        if (limit == null){
            limit = 50;
        }
        return ordersMenusDao.selectAllOrdersMenus(offset, limit).stream()
                .map(f -> new OrdersMenusResponse(
                        f.getOrderId(),
                        menuConfigService.getMenuById(f.getMenuId()).getName(),
                        f.getTimestamp()))
                .collect(Collectors.toList());
    }

    public void addNewOrdersMenus(String orderId, List<String> menuNames) {
        menuNames.forEach(m ->  {
            ordersMenusDao.insertOrdersMenus(new OrdersMenus(orderId, menuConfigService.getMenuByName(m).getId()));
        });
    }
}
