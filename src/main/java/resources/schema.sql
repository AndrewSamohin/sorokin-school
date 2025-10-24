--Таблица с атворами
CREATE TABLE authors
(
    author_id         SERIAL PRIMARY KEY,
    author_first_name VARCHAR(100) NOT NULL,
    author_last_name  VARCHAR(100)
);
--Таблица с книгами
CREATE TABLE books
(
    book_id    SERIAL PRIMARY KEY,
    book_name  VARCHAR(100) NOT NULL,
    book_count INT CHECK (book_count >= 0),
    price      NUMERIC(10, 2),
    author_id  INT REFERENCES authors (author_id)
);
--Таблица с клиентами
CREATE TABLE clients
(
    client_id   SERIAL PRIMARY KEY,
    client_name VARCHAR(100) NOT NULL
);
--Таблица с заказами
CREATE TABLE orders
(
    order_id   SERIAL PRIMARY KEY,
    client_id  INT REFERENCES clients (client_id),
    order_date DATE
);
--Таблица состав заказа
CREATE TABLE order_items
(
    order_id INT REFERENCES orders (order_id),
    book_id  INT REFERENCES books (book_id),
    quantity INT CHECK (quantity > 0),
    PRIMARY KEY (order_id, book_id)
);