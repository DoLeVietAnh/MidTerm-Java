CREATE TABLE Product (
    id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    discounted_price DECIMAL(10, 2) NOT NULL,
    image VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);