CREATE TABLE user (
    user_id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    created DATETIME NOT NULL,
    modified DATETIME NOT NULL,
    last_login DATETIME NOT NULL,
    token VARCHAR(60) NOT NULL
);


CREATE TABLE phone (
    phone_number NUMBER(10) NOT NULL,
    ddd NUMBER(3) NOT NULL,
    user_id VARCHAR(32),
    PRIMARY KEY (phone_number, ddd),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);