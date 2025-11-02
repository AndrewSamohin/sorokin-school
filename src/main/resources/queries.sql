--SELECT и WHERE
SELECT b.book_id, b.book_name, b.price
FROM books AS b
WHERE price BETWEEN 500 AND 1000;

SELECT b.book_id, b.book_name, b.book_count
FROM books AS b
WHERE book_name LIKE '%Гарри%';

--ORDER BY и GROUP BY/HAVING
SELECT author_id, COUNT(*) AS total_books
FROM books
GROUP BY author_id
ORDER BY author_id ASC;

SELECT order_id, SUM(quantity) AS total_books
FROM order_items
GROUP BY order_id
HAVING SUM(quantity) > 2;

--JOIN
INSERT INTO authors (author_id, author_first_name, author_last_name)
VALUES (6, 'Виктор', 'Гюго'),
       (7, 'Чак','Паланик');

SELECT a.author_first_name, a.author_last_name, b.book_name
FROM books AS b
INNER JOIN authors AS a ON b.author_id = a.author_id
ORDER BY a.author_first_name ASC;

SELECT a.author_first_name, a.author_last_name, b.book_name
FROM books AS b
RIGHT JOIN authors AS a ON b.author_id = a.author_id;

SELECT c.client_name, o.order_id, o.order_date
FROM orders AS o
RIGHT JOIN clients AS c ON o.client_id = c.client_id;

--UPDATE/DELETE
UPDATE books
SET price = 400.00
WHERE book_name = 'По ком звонит колокол';

DELETE FROM order_items
WHERE order_id = 6;

DELETE FROM orders
WHERE order_id = 6;