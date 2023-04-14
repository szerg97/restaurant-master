INSERT INTO orders_menus (order_id, menu_id, timestamp) VALUES(
    'abcdef123456',
    1,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));
INSERT INTO orders_menus (order_id, menu_id, timestamp) VALUES(
    'abcdef123456',
    2,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));
INSERT INTO orders_menus (order_id, menu_id, timestamp) VALUES(
    'abcdef123456',
    3,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));