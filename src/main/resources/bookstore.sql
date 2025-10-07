DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS app_user;

CREATE TABLE category (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(250) NOT NULL
);

INSERT INTO category (name) VALUES ('thriller'),
('fantasy'),
('music'),
('children'),
('technology');

SELECT * FROM category;

CREATE TABLE book (
id BIGSERIAL PRIMARY KEY,
title VARCHAR(250) NOT NULL,
author VARCHAR(250) NOT NULL,
publication_year INT NOT NULL,
isbn VARCHAR(250),
price FLOAT,
category_id BIGINT REFERENCES category(id)
);

SELECT * FROM book;

INSERT INTO book (title, author, publication_year, isbn, price, category_id) 
VALUES ('Julia Kaupungilla', 'Eva Eriksson', 2010, '11111-22222-33', 9.50, 4),
('Spindeln', 'Lars Kepler', 2022, '978-91-0-016711-0', 15.95, 1),
('Kaninjägaren', 'Lars Kepler', 2016, '978-91-0-013677-2', 14.50, 1),
('Harry Potter ja kirottu lapsi', 'J.K. Rowling', 2016, '978-951-31-9276-1', 21.95, 2),
('Opi soittamaan kitaraa', 'Phil Capone', 2010, '111-333-4444-1', 11.99, 3);

SELECT * FROM book;

CREATE TABLE app_user (
id BIGSERIAL PRIMARY KEY,
username VARCHAR(250) NOT NULL,
app_password VARCHAR(250) NOT NULL,
app_role VARCHAR(250) NOT NULL
);

INSERT INTO app_user (username, app_password, app_role) 
VALUES ('user', '$2a$10$OigcyiZvjaOWJtoTe6WW6u3pNafFLCdxGMTEtuvvUyhg1AyHHNMRe', 'USER'),
('admin', '$2a$10$xEOX0/xeBWMLkkfqNACJnukTaXe3tdF2JacCLKjMXDtgzHBeF9X4i', 'ADMIN');

SELECT * FROM app_user;
