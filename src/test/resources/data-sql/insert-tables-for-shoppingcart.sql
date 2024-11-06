-- Вставка категорії (якщо таблиця категорій існує)
INSERT INTO categories (id, name, description) VALUES (1, 'Fiction', 'Fiction books');

-- Вставка книги
INSERT INTO books (id, title, author, isbn, price, description, cover_image)
VALUES (1, 'Sample Book', 'John Author', '1234567890', 10.99, 'A sample book description.', 'https://example.com/book-cover.jpg');

-- Вставка користувача
INSERT INTO users (id, first_name, last_name, password)
VALUES (1, 'john', 'doe', 'password');

-- Вставка зв'язку книги з категорією (якщо таблиця books_categories існує)
INSERT INTO books_categories (book_id, category_id)
VALUES (1, 1);

-- Вставка товару в кошик (якщо є така таблиця для кошика покупок)
INSERT INTO shopping_cart (user_id, book_id, quantity)
VALUES (1, 1, 2);  -- Для тесту додавання книги з кількістю 2
