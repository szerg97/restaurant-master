package com.restaurant.warehouse.service;

import com.restaurant.warehouse.controller.dto.*;
import com.restaurant.warehouse.dao.FoodDao;
import com.restaurant.warehouse.exception.ResourceAlreadyExistsException;
import com.restaurant.warehouse.exception.ResourceNotFoundException;
import com.restaurant.warehouse.model.Food;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodService {

    private final FoodDao foodDao;

    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public List<FoodResponse> getFoods(Integer offset, Integer limit) {
        if (offset == null){
            offset = 0;
        }
        if (limit == null){
            limit = 50;
        }
        return foodDao.selectFoods(offset, limit).stream()
                .map(f -> new FoodResponse(
                        f.getId(),
                        f.getName(),
                        f.getQuantity(),
                        f.getTimestamp()))
                .collect(Collectors.toList());
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
    public void updateFood(Long id, FoodRequest request) {
        Optional<Food> optional = foodDao.selectFoodByName(request.name());
        optional.ifPresentOrElse(f -> {
            int result = foodDao.updateFood(id, Food.fromRequest(request));
            if (result != 1) {
                throw new IllegalStateException("oops something went wrong");
            }
        }, () ->{
            throw new ResourceAlreadyExistsException(String.format("Food with id %s does not exists", id));
        });
    }

    public OrderedFoodsResponse updateFoodsOnOrder(OrderedFoodsRequest request) {
        request.foods()
                .forEach((name, quantity) -> {
                    Food food = getFoodByName(name);
                    food.decreaseQuantity(quantity);
                    foodDao.updateFood(food.getId(), food);
                });
        return new OrderedFoodsResponse(request.foods());
    }

    public CheckedFoodsResponse checkFoodsOnOrder(CheckedFoodsRequest request) {
        Map<String, Integer> map = new HashMap<>();
        request.foods().forEach((k, v) -> {
            Food food = getFoodByName(k);
            int quantityAvailable;
            if (food.getQuantity() >= v){
                quantityAvailable = v;
            }
            else{
                quantityAvailable = food.getQuantity() - v;
            }
            map.put(food.getName(), quantityAvailable);
        });
        map.forEach((k, v) -> System.out.printf("%s=%s ".formatted(k, v)));
        return new CheckedFoodsResponse(map);
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

    public FoodResponse getFood(long id) {
        return foodDao.selectFoodById(id)
                .map(f -> new FoodResponse(
                        f.getId(),
                        f.getName(),
                        f.getQuantity(),
                        f.getTimestamp()
                ))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Food with id %s not found", id)));
    }

    private Food getFoodByName(String name) {
        return foodDao.selectFoodByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Food with name %s not found", name)));
    }
}
