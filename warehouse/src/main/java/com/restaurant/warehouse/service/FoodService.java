package com.restaurant.warehouse.service;

import com.restaurant.warehouse.data.FoodDao;
import com.restaurant.warehouse.exception.ResourceAlreadyExistsException;
import com.restaurant.warehouse.exception.ResourceNotFoundException;
import com.restaurant.warehouse.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodDao foodDao;

    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public List<Food> getFoods(Integer limit) {
        if (limit == null){
            limit = 50;
        }
        return foodDao.selectFoods(limit);
    }

    public void addNewFood(Food food) {
        // TODO: check if food exists
        Optional<Food> optional = foodDao.selectFoodById(food.getId());
        optional.ifPresentOrElse(f -> {
            throw new ResourceAlreadyExistsException(String.format("Food with id %s already exists", food.getId()));
        }, () ->{
            int result = foodDao.insertFood(food);
            if (result != 1) {
                throw new IllegalStateException("oops something went wrong");
            }
        });
    }

    public void deleteFood(long id) {
        Optional<Food> optional = foodDao.selectFoodById(id);
        optional.ifPresentOrElse(food -> {
            int result = foodDao.deleteFood(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete food");
            }
        }, () -> {
            throw new ResourceNotFoundException(String.format("Food with id %s not found", id));
        });
    }

    public Food getFood(long id) {
        return foodDao.selectFoodById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Food with id %s not found", id)));
    }
}
