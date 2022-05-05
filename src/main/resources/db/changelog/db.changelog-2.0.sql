--liquibase formatted sql

--changeset elias:1
INSERT INTO authorities (authority)
VALUES ('ROLE_USER');
INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN');

--changeset elias:2
INSERT INTO users (username, password, enabled, authority_id)
VALUES ('root', '$2a$10$ZFjH2a9FrbbicgOd32gR6OkP4EGQbbepHSNyUKp/lb0vrTeOy0WTK', true,
        (select id from authorities where authority = 'ROLE_ADMIN'));

--changeset elias:3
INSERT INTO types (name)
VALUES ('Две машины'),
       ('Машина и человек'),
       ('Машина и велосипед');

--changeset elias:4
INSERT INTO rules (id, name)
VALUES (1, 'Статья-1'),
       (2, 'Статья-2'),
       (3, 'Статья-3');

--changeset elias:5
INSERT INTO accidents (name, text, address, accident_type_id)
VALUES ('ДТП', 'Столкновение на кольце', 'г. Москва, Садовое кольцо', 1),
       ('Авария', 'ДТП на пешеходном переходе', 'г. Липецк, проспект 60 лет СССР', 2),
       ('Столкновение автомобиля и велосипедиста', 'Столкновение на перекрестке', 'г. Воронеж, Газовый переулок ', 3);

--changeset elias:6
INSERT INTO accident_rule(accident_id, rule_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1);