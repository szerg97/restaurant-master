package com.restaurant.warehouse.dao;

import com.restaurant.warehouse.model.Drink;

import java.util.List;
import java.util.Optional;

public interface DrinkDao {
    List<Drink> selectDrinks(int offset, int limit);
    int insertDrink(Drink food);
    int deleteDrink(Long id);
    Optional<Drink> selectDrinkById(long id);
    Optional<Drink> selectDrinkByName(String name);
}
