package com.restaurant.orderservice.util;

import com.restaurant.orderservice.model.OrdersMenus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrdersMenusRowMapper implements RowMapper<OrdersMenus> {
    @Override
    public OrdersMenus mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrdersMenus(
                rs.getString("order_id"),
                rs.getLong("menu_id"),
                rs.getInt("quantity"),
                rs.getTimestamp("timestamp")
        );
    }
}
