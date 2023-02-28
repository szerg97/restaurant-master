package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.controller.dto.OrderDto;
import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.service.DatabaseInfoService;
import com.restaurant.warehouse.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    @Autowired(required = false)
    private FoodService foodService;

    @Value("${spring.datasource.url}")
    private static String DATABASE_URL;

    private final FoodService movieService;
    private final DatabaseInfoService databaseInfoService;

    public WarehouseController(FoodService movieService, DatabaseInfoService databaseInfoService) {
        this.movieService = movieService;
        this.databaseInfoService = databaseInfoService;
    }

    @GetMapping("/tables")
    public List<DatabaseInfoService.DatabaseTable> getTables(){
        return databaseInfoService.getTables("public");
    }

    @GetMapping("/foods")
    public List<Food> listFoods(@RequestParam(name = "limit") int limit) {
        return movieService.getFoods(limit);
    }

    @GetMapping("{id}")
    public Food getFoodId(@PathVariable("id") Integer id) {
        return movieService.getFood(id);
    }

    @PostMapping
    public void addFood(@RequestBody Food movie) {
        movieService.addNewFood(movie);
    }

    @DeleteMapping("{id}")
    public void deleteFood(@PathVariable("id") Integer id) {
        movieService.deleteFood(id);
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
