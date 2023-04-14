package com.restaurant.warehouse.dao;

import com.restaurant.warehouse.model.Food;
import java.util.List;
import java.util.Optional;

public interface FoodDao {
    List<Food> selectFoods(int offset, int limit);
    int insertFood(Food food);
    int updateFood(long id, Food food);
    int deleteFood(Long id);
    Optional<Food> selectFoodById(long id);
    Optional<Food> selectFoodByName(String name);
}
