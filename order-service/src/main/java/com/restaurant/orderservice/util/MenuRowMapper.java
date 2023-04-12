package com.restaurant.orderservice.util;

import com.restaurant.orderservice.model.Menu;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class MenuRowMapper implements RowMapper<Menu> {
    @Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Menu(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("order_id"),
                rs.getTimestamp("timestamp")
        );
    }
}
