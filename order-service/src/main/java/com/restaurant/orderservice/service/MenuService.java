package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.MenuResponse;
import com.restaurant.orderservice.dao.MenuDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List<MenuResponse> getMenus(Integer offset, Integer limit) {
        if (offset == null){
            offset = 0;
        }
        if (limit == null){
            limit = 50;
        }
        return menuDao.selectMenus(offset, limit).stream()
                .map(f -> new MenuResponse(
                        f.getId(),
                        f.getName(),
                        f.getOrderId(),
                        f.getTimestamp()))
                .collect(Collectors.toList());
    }
}
