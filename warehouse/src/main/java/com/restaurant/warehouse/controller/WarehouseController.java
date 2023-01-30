package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    @Autowired(required = false)
    private FoodService foodService;

    @Value("${spring.datasource.url}")
    private static String DATABASE_URL;

    @GetMapping("/meal")
    public String getMeal(){
        return "Cheese Burger XXL";
    }

    @GetMapping("/foods")
    public List<Food> getAll(){
        return foodService.getAll();
    }

    @PostMapping("/foods")
    public Food save(@RequestBody Food food){
        return foodService.save(food, food.getId());
    }

    @GetMapping("/datasource")
    public String getDataSourceUrl(){
        return DATABASE_URL;
    }
}
