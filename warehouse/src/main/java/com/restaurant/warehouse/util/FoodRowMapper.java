package com.restaurant.warehouse.util;

import com.restaurant.warehouse.model.Food;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FoodRowMapper implements RowMapper<Food> {
    @Override
    public Food mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Food(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                LocalDateTime.parse(resultSet.getString("timestamp"))
        );
    }
}
