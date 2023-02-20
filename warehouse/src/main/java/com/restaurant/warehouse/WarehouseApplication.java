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

    @Value("${spring.datasource.enabled}")
    private boolean dataSourceEnabled;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            if (dataSourceEnabled && service.getFoods().isEmpty()){
                service.addNewFood(new Food(1L, "Bread 80kg", LocalDateTime.now()));
                service.addNewFood(new Food(2L, "Meat 400kg", LocalDateTime.now()));
                service.addNewFood(new Food(3L, "Salt 500kg", LocalDateTime.now()));
                service.addNewFood(new Food(4L, "Sugar 450kg", LocalDateTime.now()));
            }
        };
    }

}
