package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.controller.dto.FoodRequest;
import com.restaurant.warehouse.controller.dto.FoodResponse;
import com.restaurant.warehouse.service.FoodService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/warehouse/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("")
    public List<FoodResponse> listFoods(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit) {
        return foodService.getFoods(offset, limit);
    }

    @GetMapping("/{id}")
    public FoodResponse getFoodById(@PathVariable("id") Integer id) {
        return foodService.getFood(id);
    }

    @PostMapping("")
    public void addFood(@RequestBody FoodRequest food) {
        foodService.addNewFood(food);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable("id") Integer id) {
        foodService.deleteFood(id);
    }
}
