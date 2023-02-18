package com.restaurant.warehouse.service;

import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    public Food getById(Long id){
        Optional<Food> food = repository.findById(id);
        if (food.isPresent()){
            return food.get();
        }
        throw new IllegalStateException("Food was not found");
    }

    public Food getByName(String name){
        Optional<Food> food = repository.findByName(name);
        if (food.isPresent()){
            return food.get();
        }
        throw new IllegalStateException("Food was not found");
    }

    public List<Food> getAll(){
        System.out.println("from DB");
        return repository.findAll();
    }

    public Food save(Food food, Long id){
        Food saved = repository.saveAndFlush(food);
        if (saved.getId() != null){
            return saved;
        }
        throw new IllegalStateException("Food could not be saved");
    }

    public Food delete(Long id){
        Optional<Food> food = repository.findById(id);
        if (food.isPresent()){
            repository.delete(food.get());
            return food.get();
        }
        throw new IllegalStateException("Food could not be deleted");
    }
}
