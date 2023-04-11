package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.controller.dto.OrderDto;
import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.service.DatabaseInfoService;
import com.restaurant.warehouse.service.FoodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    @Value("${spring.datasource.url}")
    private static String DATABASE_URL;

    private final FoodService foodService;
    private final DatabaseInfoService databaseInfoService;

    public WarehouseController(FoodService foodService, DatabaseInfoService databaseInfoService) {
        this.foodService = foodService;
        this.databaseInfoService = databaseInfoService;
    }

    @GetMapping("/tables")
    public List<DatabaseInfoService.DatabaseTable> getTables(){
        return databaseInfoService.getTables("public");
    }

    @GetMapping("/foods")
    public List<Food> listFoods(@RequestParam(name = "limit", required = false) Integer limit) {
        return foodService.getFoods(limit);
    }

    @GetMapping("/foods/{id}")
    public Food getFoodId(@PathVariable("id") Integer id) {
        return foodService.getFood(id);
    }

    @PostMapping("/foods")
    public void addFood(@RequestBody Food movie) {
        foodService.addNewFood(movie);
    }

    @DeleteMapping("{id}")
    public void deleteFood(@PathVariable("id") Integer id) {
        foodService.deleteFood(id);
    }

    @PostMapping("/serve")
    public List<Food> serve(@RequestBody OrderDto dto){
        List<Food> foods = new ArrayList<>();
        dto.getFoods().forEach(f -> foods.add(foodService.getFood(f.getId())));
        return foods;
    }

    @GetMapping("/datasource")
    public String getDataSourceUrl(){
        return DATABASE_URL;
    }
}
