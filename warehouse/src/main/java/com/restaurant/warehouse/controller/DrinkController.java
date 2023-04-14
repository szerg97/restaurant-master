package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.controller.dto.*;
import com.restaurant.warehouse.service.DrinkService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/warehouse/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("")
    public List<DrinkResponse> listDrinks(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit) {
        return drinkService.getDrinks(offset, limit);
    }

    @GetMapping("/{id}")
    public DrinkResponse getDrinkById(@PathVariable("id") Integer id) {
        return drinkService.getDrink(id);
    }

    @PostMapping("")
    public void addDrink(@RequestBody DrinkRequest drink) {
        drinkService.addNewDrink(drink);
    }

    @PutMapping("/{id}")
    public void updateDrink(
            @PathVariable long id,
            @RequestBody DrinkRequest food) {
        drinkService.updateDrink(id, food);
    }

    @PutMapping("")
    public boolean updateDrinksOnOrder(
            @RequestBody OrderedDrinksRequest request){
        return drinkService.updateDrinksOnOrder(request);
    }

    @DeleteMapping("/{id}")
    public void deleteDrink(@PathVariable("id") Integer id) {
        drinkService.deleteDrink(id);
    }
}
