CREATE TABLE campaign (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    keywords VARCHAR(255) NOT NULL,
    bid_amount DECIMAL(10, 2) NOT NULL,
    campaign_fund DECIMAL(10, 2) NOT NULL,
    status BOOLEAN NOT NULL,
    city VARCHAR(255),
    radius INT NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE
);

CREATE TABLE company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    email VARCHAR(255),
    phoneNumber VARCHAR(255) NOT NULL
);