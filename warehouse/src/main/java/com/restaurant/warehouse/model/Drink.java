package com.restaurant.warehouse.model;

import com.restaurant.warehouse.controller.dto.DrinkRequest;
import com.restaurant.warehouse.controller.dto.DrinkResponse;
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
public class Drink {

    private Long id;
    private String name;
    private int quantity;
    private Timestamp timestamp;

    public Drink(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public Drink(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public int decreaseQuantity(){
        return quantity -= 1;
    }

    public static Drink fromRequest(DrinkRequest request) {
        return new Drink(request.name(), request.quantity());
    }

    public static Drink fromResponse(DrinkResponse response) {
        return new Drink(response.id(), response.name(), response.quantity(), response.timestamp());
    }

    @Override
    public String toString() {
        return "Drink{" +
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
        Drink drink = (Drink) o;
        return Objects.equals(id, drink.id) && Objects.equals(name, drink.name) && Objects.equals(quantity, drink.quantity) && Objects.equals(timestamp, drink.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, timestamp);
    }
}
