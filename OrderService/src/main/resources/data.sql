SET REFERENTIAL_INTEGRITY TRUE;

-- eUsers Table
CREATE TABLE eUsers (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Items Table
CREATE TABLE items (
                       item_id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       description VARCHAR(255),
                       price DECIMAL(10, 2) NOT NULL,
                       stock INT DEFAULT 0,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Orders Table
CREATE TABLE orders (
                        order_id INT PRIMARY KEY AUTO_INCREMENT,
                        user_id INT NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(20) DEFAULT 'PENDING',
                        FOREIGN KEY (user_id) REFERENCES eUsers(user_id)
);

-- Orders_Items Table (Many-to-Many relationship between orders and items)
CREATE TABLE orders_items (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              order_id INT NOT NULL,
                              item_id INT NOT NULL,
                              quantity INT DEFAULT 1,
                              FOREIGN KEY (order_id) REFERENCES orders(order_id),
                              FOREIGN KEY (item_id) REFERENCES items(item_id)
);

-- Payment Table (Each order can have multiple payments)
CREATE TABLE payment (
                         payment_id INT PRIMARY KEY AUTO_INCREMENT,
                         order_id INT NOT NULL,
                         amount DECIMAL(10, 2) NOT NULL,
                         payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         payment_status VARCHAR(20) DEFAULT 'PENDING',
                         payment_method VARCHAR(50),
                         FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

-- Notification Table (Each payment can have multiple notifications)
CREATE TABLE notification (
                              notification_id INT PRIMARY KEY AUTO_INCREMENT,
                              payment_id INT NOT NULL,
                              user_id INT NOT NULL,
                              notification_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              message TEXT,
                              status VARCHAR(20) DEFAULT 'SENT',
                              FOREIGN KEY (payment_id) REFERENCES payment(payment_id),
                              FOREIGN KEY (user_id) REFERENCES eUsers(user_id)
);

-- Sample Indexes
CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_items_order_id ON orders_items(order_id);
CREATE INDEX idx_orders_items_item_id ON orders_items(item_id);
CREATE INDEX idx_payment_order_id ON payment(order_id);
CREATE INDEX idx_notification_payment_id ON notification(payment_id);


INSERT INTO items (name, description, price, stock) VALUES
                                                        ('Wireless Mouse', 'Ergonomic wireless mouse with adjustable DPI', 15.99, 150),
                                                        ('Mechanical Keyboard', 'RGB backlit mechanical keyboard with blue switches', 45.99, 100),
                                                        ('Gaming Monitor', '27-inch 144Hz gaming monitor with 1ms response time', 249.99, 50),
                                                        ('USB-C Hub', '6-in-1 USB-C hub with HDMI, USB 3.0, and SD card reader', 29.99, 200),
                                                        ('Laptop Stand', 'Aluminum laptop stand with adjustable height and angle', 19.99, 120),
                                                        ('Bluetooth Earbuds', 'Wireless earbuds with noise cancellation and charging case', 39.99, 180),
                                                        ('Smartphone Holder', 'Adjustable smartphone holder for car dashboard', 9.99, 250),
                                                        ('Portable Charger', '10000mAh portable charger with fast charging', 24.99, 300),
                                                        ('External Hard Drive', '1TB USB 3.0 external hard drive', 54.99, 75),
                                                        ('Wireless Charger', 'Fast wireless charger for Qi-enabled devices', 17.99, 130);


INSERT INTO eUsers (username, email, password) VALUES
                                                  ('john_doe', 'john.doe@example.com', 'password123'),
                                                  ('jane_smith', 'jane.smith@example.com', 'securepass456'),
                                                  ('michael_brown', 'michael.brown@example.com', 'mypassword789'),
                                                  ('emily_davis', 'emily.davis@example.com', 'emilysecure012'),
                                                  ('daniel_wilson', 'daniel.wilson@example.com', 'danielpass345'),
                                                  ('sophia_martin', 'sophia.martin@example.com', 'martinpass678'),
                                                  ('james_clark', 'james.clark@example.com', 'clarkpass901'),
                                                  ('olivia_harris', 'olivia.harris@example.com', 'oliviasecure234'),
                                                  ('liam_moore', 'liam.moore@example.com', 'moorepass567'),
                                                  ('ava_wright', 'ava.wright@example.com', 'wrightpass890');