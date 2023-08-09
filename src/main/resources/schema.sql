CREATE TABLE IF NOT EXISTS stock (
                                     id SERIAL PRIMARY KEY,
                                     create_date TIMESTAMP,
                                     last_update TIMESTAMP,
                                     name VARCHAR(255),
    current_price NUMERIC(10, 2)
    );
