package com.restaurant.orderservice.service;

import com.restaurant.orderservice.dao.OrdersMenusDao;
import com.restaurant.orderservice.model.OrdersMenus;
import com.restaurant.orderservice.util.OrdersMenusRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdersMenusAccessService implements OrdersMenusDao {

    private final JdbcTemplate jdbcTemplate;
    private final OrdersMenusRowMapper rowMapper;

    public OrdersMenusAccessService(JdbcTemplate jdbcTemplate, OrdersMenusRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<OrdersMenus> selectAllOrdersMenus(int offset, int limit) {
        String sql = """
                SELECT id, name, order_id, timestamp
                FROM menus
                OFFSET ?
                LIMIT ?
                """;
        return jdbcTemplate.query(sql, rowMapper, offset, limit);
    }

    @Override
    public int insertOrdersMenus(OrdersMenus ordersMenus) {
        String sql = """
                INSERT INTO orders_menus (order_id, menu_id, timestamp)
                VALUES (?, ?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                ordersMenus.getOrderId(), ordersMenus.getMenuId(), ordersMenus.getTimestamp()
        );
    }

    @Override
    public int deleteOrdersMenus(Long id) {
        return 0;
    }

    @Override
    public Optional<OrdersMenus> selectOrdersMenus(long orderId, long menuId) {
        return Optional.empty();
    }
}
