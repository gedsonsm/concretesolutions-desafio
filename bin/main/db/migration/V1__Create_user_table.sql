CREATE TABLE user (
    id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    created Date NOT NULL,
    modified Date NOT NULL,
    last_login Date NOT NULL,
    token VARCHAR(100) NOT NULL
);