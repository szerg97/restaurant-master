package com.restaurant.orderservice.controller;

import com.restaurant.orderservice.controller.dto.MenuResponse;
import com.restaurant.orderservice.model.MenuConfig;
import com.restaurant.orderservice.service.MenuConfigService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/v1/order-service/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuConfigService menuConfigService;

    @GetMapping("")
    public List<MenuResponse> getMenus(){
        return menuConfigService.getMenus().stream()
                .map(m -> new MenuResponse(
                        m.getId(),
                        m.getName(),
                        m.getFoods(),
                        m.getDrinks(),
                        m.getAllergens(),
                        m.getPrice()
                ))
                .collect(Collectors.toList());
    }
}
