package com.restaurant.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenusFoods {
     private long id;
     private String menuName;
     private String foodName;
     private int quantity;
     private Timestamp timestamp;

    public MenusFoods(String menuName, String foodName, int quantity) {
        this.menuName = menuName;
        this.foodName = foodName;
        this.quantity = quantity;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
