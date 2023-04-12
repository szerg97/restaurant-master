do
$$
    BEGIN
        FOR r IN 1..10000
            LOOP
                INSERT INTO drinks (name, quantity, timestamp)
                VALUES ('Drink ' || r::text,
                        FLOOR(RANDOM() * 100)::int,
                        (SELECT timestamp '2023-02-01 00:00:00' +
                                RANDOM() * (timestamp '2023-03-01 00:00:00' -
                                            timestamp '2023-02-01 00:00:00')
                                ));
            END LOOP;
    END;
$$;