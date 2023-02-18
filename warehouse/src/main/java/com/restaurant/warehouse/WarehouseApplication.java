package com.restaurant.warehouse;

import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class WarehouseApplication {

    @Autowired(required = false)
    private FoodRepository repository;

    @Value("${spring.datasource.enabled}")
    private boolean dataSourceEnabled;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            if (dataSourceEnabled && repository.findAll().isEmpty()){
                repository.saveAll(List.of(
                        new Food("Bread 80kg", LocalDateTime.of(2023, 1, 27, 0, 0)),
                        new Food("Meat 400kg", LocalDateTime.of(2023, 1, 27, 1, 27)),
                        new Food("Salt 500kg", LocalDateTime.of(2023, 1, 27, 2, 30)),
                        new Food("Sugar 450kg", LocalDateTime.of(2023, 1, 27, 3, 15))
                ));
            }
        };
    }

}
