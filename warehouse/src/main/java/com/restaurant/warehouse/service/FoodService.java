package com.restaurant.warehouse.service;

import com.restaurant.warehouse.controller.dto.FoodRequest;
import com.restaurant.warehouse.dao.FoodDao;
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

    public List<Food> getFoods(Integer offset, Integer limit) {
        if (offset == null){
            offset = 0;
        }
        if (limit == null){
            limit = 50;
        }
        return foodDao.selectFoods(offset, limit);
    }

    public void addNewFood(FoodRequest request) {
        Optional<Food> optional = foodDao.selectFoodByName(request.name());
        optional.ifPresentOrElse(f -> {
            throw new ResourceAlreadyExistsException(String.format("Food with name %s already exists", request.name()));
        }, () ->{
            int result = foodDao.insertFood(Food.fromRequest(request));
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
                throw new IllegalStateException("oops could not delete food: " + food);
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
