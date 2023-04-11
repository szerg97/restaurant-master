package com.restaurant.warehouse.model;

import com.restaurant.warehouse.controller.dto.FoodRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Food {

    private Long id;
    private String name;
    private Timestamp timestamp;

    public Food(Long id, String name, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
    }

    public Food(Long id, String name) {
        this.id = id;
        this.name = name;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public Food(String name) {
        this.name = name;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public static Food fromRequest(FoodRequest request) {
        return new Food(request.name());
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id) && Objects.equals(name, food.name) && Objects.equals(timestamp, food.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, timestamp);
    }
}
