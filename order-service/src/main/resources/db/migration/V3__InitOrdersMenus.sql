CREATE TABLE orders_menus(
   order_id VARCHAR REFERENCES orders(id),
   menu_id BIGINT,
   quantity INT,
   timestamp TIMESTAMP
);