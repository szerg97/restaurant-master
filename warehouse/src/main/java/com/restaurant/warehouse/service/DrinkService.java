package com.restaurant.warehouse.service;

import com.restaurant.warehouse.controller.dto.DrinkRequest;
import com.restaurant.warehouse.controller.dto.DrinkResponse;
import com.restaurant.warehouse.controller.dto.OrderedDrinksRequest;
import com.restaurant.warehouse.dao.DrinkDao;
import com.restaurant.warehouse.exception.ResourceAlreadyExistsException;
import com.restaurant.warehouse.exception.ResourceNotFoundException;
import com.restaurant.warehouse.model.Drink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    private final DrinkDao drinkDao;

    public DrinkService(DrinkDao drinkDao) {
        this.drinkDao = drinkDao;
    }

    public List<DrinkResponse> getDrinks(Integer offset, Integer limit) {
        if (offset == null){
            offset = 0;
        }
        if (limit == null){
            limit = 50;
        }
        return drinkDao.selectDrinks(offset, limit).stream()
                .map(f -> new DrinkResponse(
                        f.getId(),
                        f.getName(),
                        f.getQuantity(),
                        f.getTimestamp()))
                .collect(Collectors.toList());
    }

    public void addNewDrink(DrinkRequest request) {
        final Optional<Drink> optional = drinkDao.selectDrinkByName(request.name());
        optional.ifPresentOrElse(f -> {
            throw new ResourceAlreadyExistsException(String.format("Drink with name %s already exists", request.name()));
        }, () ->{
            final int result = drinkDao.insertDrink(Drink.fromRequest(request));
            if (result != 1) {
                throw new IllegalStateException("oops something went wrong");
            }
        });
    }

    public void updateDrink(Long id, DrinkRequest request) {
        final Optional<Drink> optional = drinkDao.selectDrinkByName(request.name());
        optional.ifPresentOrElse(f -> {
            final int result = drinkDao.updateDrink(id, Drink.fromRequest(request));
            if (result != 1) {
                throw new IllegalStateException("oops something went wrong");
            }
        }, () ->{
            throw new ResourceAlreadyExistsException(String.format("Drink with id %s does not exists", id));
        });
    }

    public boolean updateDrinksOnOrder(OrderedDrinksRequest request) {
        final List<String> foodNames = request.drinks();
        foodNames.forEach(f -> {
            final Drink drink = getDrinkByName(f);
            drink.decreaseQuantity();
            drinkDao.updateDrink(drink.getId(), drink);
        });
        return true;
    }

    public void deleteDrink(long id) {
        final Optional<Drink> optional = drinkDao.selectDrinkById(id);
        optional.ifPresentOrElse(food -> {
            final int result = drinkDao.deleteDrink(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete drink: " + food);
            }
        }, () -> {
            throw new ResourceNotFoundException(String.format("Drink with id %s not found", id));
        });
    }

    public DrinkResponse getDrink(long id) {
        return drinkDao.selectDrinkById(id)
                .map(f -> new DrinkResponse(
                        f.getId(),
                        f.getName(),
                        f.getQuantity(),
                        f.getTimestamp()
                ))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Drink with id %s not found", id)));
    }

    private Drink getDrinkByName(String name) {
        return drinkDao.selectDrinkByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Drink with name %s not found", name)));
    }
}
