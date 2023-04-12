package com.restaurant.orderservice.dao;

import com.restaurant.orderservice.model.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuDao {
    List<Menu> selectMenus(int offset, int limit);
    int insertMenu(Menu food);
    int deleteMenu(Long id);
    Optional<Menu> selectMenuById(long id);
    Optional<Menu> selectMenuByName(String name);
}
