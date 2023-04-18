package com.restaurant.warehouse.model;

import com.restaurant.warehouse.controller.dto.FoodRequest;
import com.restaurant.warehouse.controller.dto.FoodResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private Long id;
    private String name;
    private int quantity;
    private Timestamp timestamp;

    public Food(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public Food(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public int decreaseQuantity(int value){
        return quantity -= value;
    }

    public static Food fromRequest(FoodRequest request) {
        return new Food(request.name(), request.quantity());
    }

    public static Food fromResponse(FoodResponse response) {
        return new Food(response.id(), response.name(), response.quantity(), response.timestamp());
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id) && Objects.equals(name, food.name) && Objects.equals(quantity, food.quantity) && Objects.equals(timestamp, food.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, timestamp);
    }
}
