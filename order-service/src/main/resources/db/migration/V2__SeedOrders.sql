INSERT INTO orders (id, price, timestamp) VALUES(
    'abcdef123456',
    9999.0,
    (SELECT timestamp '2023-02-01 00:00:00' +
            RANDOM() * (timestamp '2023-03-01 00:00:00' -
                        timestamp '2023-02-01 00:00:00')
    ));