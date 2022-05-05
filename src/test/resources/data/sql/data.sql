CREATE TABLE types
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE rules
(
    id   INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE accidents
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255) UNIQUE NOT NULL,
    text             VARCHAR(255) NOT NULL,
    address          VARCHAR(255) NOT NULL,
    accident_type_id INT REFERENCES types (id)
);

CREATE TABLE accident_rule
(
    id          SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accidents (id),
    rule_id     INT NOT NULL REFERENCES rules (id),
    UNIQUE (accident_id, rule_id)
);

INSERT INTO types (name)
VALUES ('Две машины'),
       ('Машина и человек'),
       ('Машина и велосипед');

INSERT INTO rules (id, name)
VALUES (1, 'Статья-1'),
       (2, 'Статья-2'),
       (3, 'Статья-3');

INSERT INTO accidents (name, text, address, accident_type_id)
VALUES ('ДТП', 'Столкновение на кольце', 'г. Москва, Садовое кольцо', 1),
       ('ДТП', 'ДТП на пешеходном переходе', 'г. Липецк, проспект 60 лет СССР', 2),
       ('ДТП', 'Столкновение на перекрестке', 'г. Воронеж, Газовый переулок ', 3);

INSERT INTO accident_rule(accident_id, rule_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1);