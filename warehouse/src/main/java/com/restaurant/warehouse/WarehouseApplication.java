package com.restaurant.warehouse;

import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class WarehouseApplication {

    @Autowired(required = false)
    private FoodService service;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
