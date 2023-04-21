package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.controller.dto.*;
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
            @RequestParam(name = "offset", required = false) @Min(0) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(0) @Max(100) Integer limit) {
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

    @PutMapping("/{id}")
    public void updateFood(
            @PathVariable long id,
            @RequestBody FoodRequest food) {
        foodService.updateFood(id, food);
    }

    @PostMapping("/order")
    public OrderedFoodsResponse updateFoodsOnOrder(
            @RequestBody OrderedFoodsRequest request){
        return foodService.updateFoodsOnOrder(request);
    }

    @PostMapping("/check")
    public CheckedFoodsResponse checkFoodsOnOrder(
            @RequestBody CheckedFoodsRequest request){
        return foodService.checkFoodsOnOrder(request);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable("id") Integer id) {
        foodService.deleteFood(id);
    }
}
