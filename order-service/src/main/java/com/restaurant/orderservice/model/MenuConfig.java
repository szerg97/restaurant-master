package com.restaurant.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuConfig {
    private List<Menu> menus;

    @Data
    public static class Menu{
        private long id;
        private String name;
        private List<Long> foods;
        private List<Long> drinks;
        private List<Allergen> allergens;
        private double price;
    }

    public enum Allergen{
        GLUTEN(1),
        CRUSTACEANS(2),
        EGGS(3),
        FISH(4),
        PEANUTS(5),
        SOYBEANS(6),
        MILK(7),
        NUTS(8),
        CELERY(9),
        MUSTARD(10),
        SESAME(11),
        SULPHUR(12),
        LUPIN(13),
        MOLLUSCS(14);

        private final int value;

        Allergen(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
