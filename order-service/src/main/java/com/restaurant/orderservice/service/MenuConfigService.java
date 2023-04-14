package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.OrderRequest;
import com.restaurant.orderservice.model.MenuConfig;
import com.restaurant.orderservice.util.Resources;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuConfigService {

    private MenuConfig menuConfig;

    @PostConstruct
    public void init() throws IOException {
        menuConfig = Resources.getMenuListResource("menu.json");
        log.info(String.format("Loaded menu config: %s",menuConfig));
    }

    public List<MenuConfig.Menu> getMenus(){
        return menuConfig.getMenus();
    }

    public MenuConfig.Menu getMenuById(long menuId) {
        return menuConfig.getMenus().stream().filter(m -> m.getId() == menuId).findFirst().orElseThrow();
    }

    public List<String> getFoodNames(OrderRequest order) {
        List<String> foods = new ArrayList<>();
        order.menuNames().forEach(n -> menuConfig.getMenus().stream()
                .filter(m -> m.getName().equals(n))
                .forEach(menu -> foods.addAll(menu.getFoods())));
        return foods;
    }

    public MenuConfig.Menu getMenuByName(String menuName) {
        return menuConfig.getMenus().stream()
                .filter(m -> m.getName()
                        .equals(menuName))
                .findFirst()
                .orElseThrow();
    }
}
