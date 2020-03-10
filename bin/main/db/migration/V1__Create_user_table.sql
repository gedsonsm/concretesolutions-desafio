CREATE TABLE user (
    id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    created Date,
    modified Date
);