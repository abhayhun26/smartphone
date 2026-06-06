INSERT INTO products (name, brand, model, price, description, stock_quantity, active, created_at)
VALUES
('iPhone 13', 'Apple', 'A2633', 69999.00, 'Apple smartphone with A15 chip', 10, true, CURRENT_TIMESTAMP),

('iPhone 14', 'Apple', 'A2882', 79999.00, 'Latest Apple smartphone', 8, true, CURRENT_TIMESTAMP),

('Galaxy S21', 'Samsung', 'SM-G991B', 59999.00, 'Samsung flagship phone', 15, true, CURRENT_TIMESTAMP),

('Galaxy S22', 'Samsung', 'SM-S901E', 74999.00, 'Next-gen Samsung flagship', 12, true, CURRENT_TIMESTAMP),

('OnePlus 11', 'OnePlus', 'CPH2447', 56999.00, 'Fast and smooth performance', 20, true, CURRENT_TIMESTAMP);

INSERT INTO users (id, name, email, password, role)
VALUES (
    1,
    'Admin',
    'admin@gmail.com',
    '$2a$10$7EqJtq98hPqEX7fNZaFWoOHi8e0nXK2cM4KQbM3eVh5sYh2S8Q5QK',
    'ADMIN'
);