CREATE TABLE orders_menus(
   order_id BIGINT REFERENCES orders(id),
   menu_id BIGINT,
   timestamp TIMESTAMP
);