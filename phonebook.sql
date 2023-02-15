CREATE TABLE phone_books (
    id UUID NOT NULL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NULL,
    email VARCHAR(100) NULL,
    phone_number VARCHAR(100) NOT NULL,
    address VARCHAR(255) NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NULL,
    is_deleted BOOL DEFAULT FALSE
);