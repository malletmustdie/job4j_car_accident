--liquibase formatted sql

--changeset elias:1
CREATE TABLE IF NOT EXISTS authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(255) NOT NULL UNIQUE
);
--rollback DROP TABLE authorities;

--changeset elias:2
CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    password     VARCHAR(255),
    username     VARCHAR(255) NOT NULL UNIQUE,
    enabled      BOOLEAN,
    authority_id INT REFERENCES authorities (id)
);

--changeset elias:3
CREATE TABLE IF NOT EXISTS types
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

--changeset elias:4
CREATE TABLE IF NOT EXISTS rules
(
    id   INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

--changeset elias:5
CREATE TABLE IF NOT EXISTS accidents
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    text             VARCHAR(255) NOT NULL,
    address          VARCHAR(255) NOT NULL,
    accident_type_id INT REFERENCES types (id)
);

--changeset elias:6
CREATE TABLE IF NOT EXISTS accident_rule
(
    id          SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accidents (id),
    rule_id     INT NOT NULL REFERENCES rules (id),
    UNIQUE (accident_id, rule_id)
);