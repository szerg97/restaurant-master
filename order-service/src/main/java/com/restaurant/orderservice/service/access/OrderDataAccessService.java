package com.restaurant.orderservice.service.access;

import com.restaurant.orderservice.dao.OrderDao;
import com.restaurant.orderservice.model.Order;
import com.restaurant.orderservice.util.OrderRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDataAccessService implements OrderDao {

    private final JdbcTemplate jdbcTemplate;
    private final OrderRowMapper rowMapper;

    public OrderDataAccessService(JdbcTemplate jdbcTemplate, OrderRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Order> selectOrders(int offset, int limit) {
        String sql = """
                SELECT id, price, timestamp
                FROM orders
                OFFSET ?
                LIMIT ?
                """;
        return jdbcTemplate.query(sql, rowMapper, offset, limit);
    }

    @Override
    public int insertOrder(Order order) {
        String sql = """
                INSERT INTO orders (id, price, timestamp)
                VALUES (?, ?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                order.getId(),
                order.getPrice(),
                order.getTimestamp());
    }

    @Override
    public int deleteOrder(String id) {
        return 0;
    }

    @Override
    public Optional<Order> selectOrderById(String id) {
        String sql = """
                SELECT id, price, timestamp
                FROM orders
                WHERE id = ?
                """;
        return jdbcTemplate.queryForStream(
                sql,
                rowMapper,
                id
                ).findFirst();
    }


}
