DROP TABLE IF EXISTS cats_image;
DROP TABLE IF EXISTS cats;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS likes;

CREATE TABLE IF NOT EXISTS cats
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name     VARCHAR     NOT NULL,
    email    VARCHAR     NOT NULL,
    password VARCHAR     NOT NULL,
    rate_cat BIGINT DEFAULT 0,
    role     VARCHAR(10) NOT NULL,
    state    VARCHAR(15) NOT NULL,
    CONSTRAINT uq_email_cat UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS cats_image
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    content BYTEA  NOT NULL,
    cat_id  BIGINT NOT NULL,
    FOREIGN KEY (cat_id) REFERENCES cats (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS persistent_logins
(
    username  varchar(64) NOT NULL,
    series    varchar(64) NOT NULL PRIMARY KEY,
    token     varchar(64) NOT NULL,
    last_used timestamp   NOT NULL
);

CREATE TABLE IF NOT EXISTS likes
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    cat_id       BIGINT NOT NULL REFERENCES cats (id) ON DELETE CASCADE,
    voted_cat_id BIGINT NOT NULL REFERENCES cats (id) ON DELETE CASCADE
);

INSERT INTO cats (id, name, email, password, role, state)
VALUES (1, 'Cat 1', 'cat1@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (2, 'Cat 2', 'cat2@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (3, 'Cat 3', 'cat3@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (4, 'Cat 4', 'cat4@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (5, 'Cat 5', 'cat5@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (6, 'Cat 6', 'cat6@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (7, 'Cat 7', 'cat7@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (8, 'Cat 8', 'cat8@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (9, 'Cat 9', 'cat9@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED'),
       (10, 'Cat 10', 'cat10@example.com', '$2a$10$05jxSboGD3xCuqn5sC352OduWM0A9mQjP6cFFU5LFQLxvC.tjZr.e', 'USER',
        'CONFIRMED')
    ON CONFLICT DO NOTHING;

INSERT INTO cats_image (id, content, cat_id)
SELECT generate_series, pg_read_binary_file('/resources/images/cat'|| generate_series || '.jpg'), generate_series
FROM generate_series(1, 10)
ON CONFLICT DO NOTHING;