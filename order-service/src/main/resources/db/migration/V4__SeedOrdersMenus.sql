INSERT INTO orders_menus (order_id, menu_id, quantity, timestamp) VALUES(
    'abcdef123456',
    1,
                                                               99,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));
INSERT INTO orders_menus (order_id, menu_id, quantity, timestamp) VALUES(
    'abcdef123456',
    2,
                                                               99,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));
INSERT INTO orders_menus (order_id, menu_id, quantity, timestamp) VALUES(
    'abcdef123456',
    3,
                                                               99,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));