package com.restaurant.warehouse.util;

import com.restaurant.warehouse.model.Drink;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DrinkRowMapper implements RowMapper<Drink> {
    @Override
    public Drink mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Drink(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("quantity"),
                resultSet.getTimestamp("timestamp")
        );
    }
}
