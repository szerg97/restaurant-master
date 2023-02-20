package com.restaurant.warehouse.data;

import com.restaurant.warehouse.model.Food;
import java.util.List;
import java.util.Optional;

public interface FoodDao {
    List<Food> selectFoods();
    int insertFood(Food movie);
    int deleteFood(Long id);
    Optional<Food> selectFoodById(long id);
}
