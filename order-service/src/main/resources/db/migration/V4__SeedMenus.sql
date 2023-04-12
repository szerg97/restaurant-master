INSERT INTO menus (name, order_id, timestamp) VALUES(
    'Menu 1',
    1,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));
INSERT INTO menus (name, order_id, timestamp) VALUES(
    'Menu 2',
    1,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));
INSERT INTO menus (name, order_id, timestamp) VALUES(
    'Menu 3',
    1,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));