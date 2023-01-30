package com.restaurant.warehouse.reflection;

import com.restaurant.warehouse.model.Food;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionTest {

    @Test
    public void whenNonPublicField_thenReflectionTestUtilsSetField() {
        Food food = new Food();
        ReflectionTestUtils.setField(food, "name", "BurgerXXL");

        assertEquals("BurgerXXL", food.getName());
    }

    @Test
    public void whenNonPublicMethod_thenReflectionTestUtilsInvokeMethod() {
        Food food = new Food();
        LocalDateTime now = LocalDateTime.now();
        ReflectionTestUtils.setField(food, "name", "BurgerXXL");
        ReflectionTestUtils.setField(food, "timestamp", now);
        food.setId(1L);

        assertEquals("Food{" +
                "id=" + 1L +
                ", name='" + "BurgerXXL" + '\'' +
                ", timestamp=" + now +
                '}', ReflectionTestUtils.invokeMethod(food, "toString"));
    }
}
