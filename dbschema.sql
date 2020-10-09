CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS account_info
(
    id            UUID      DEFAULT uuid_generate_v4(),
    name          TEXT NOT NULL,
    surname       TEXT NOT NULL,
    login         TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    created_at    TIMESTAMP DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT unique_login_constraint UNIQUE (login)
);

CREATE TABLE IF NOT EXISTS access_token
(
    id         UUID      DEFAULT uuid_generate_v4(),
    token      UUID      DEFAULT uuid_generate_v4(),
    user_id    UUID NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT user_id_constraint FOREIGN KEY (user_id) REFERENCES account_info (id) ON DELETE CASCADE
);