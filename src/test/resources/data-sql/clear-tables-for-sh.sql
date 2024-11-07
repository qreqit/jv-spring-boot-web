-- Set shopping_cart_id to NULL in users table before deleting from shopping_carts
UPDATE users SET shopping_cart_id = NULL WHERE shopping_cart_id = 1;

-- Delete from cart_items
DELETE FROM cart_items;

-- Delete from shopping_carts
DELETE FROM shopping_carts;

-- Delete from books
DELETE FROM books;

-- Delete from users;
DELETE FROM users;