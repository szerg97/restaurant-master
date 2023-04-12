package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.controller.dto.FoodRequest;
import com.restaurant.warehouse.controller.dto.FoodResponse;
import com.restaurant.warehouse.service.DatabaseInfoService;
import com.restaurant.warehouse.service.FoodService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Validated
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
    public List<FoodResponse> listFoods(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit
    ) {
        return foodService.getFoods(offset, limit);
    }

    @GetMapping("/foods/{id}")
    public FoodResponse getFoodId(@PathVariable("id") Integer id) {
        return foodService.getFood(id);
    }

    @PostMapping("/foods")
    public void addFood(@RequestBody FoodRequest food) {
        foodService.addNewFood(food);
    }

    @DeleteMapping("/foods/{id}")
    public void deleteFood(@PathVariable("id") Integer id) {
        foodService.deleteFood(id);
    }

    @GetMapping("/datasource")
    public String getDataSourceUrl(){
        return DATABASE_URL;
    }
}
