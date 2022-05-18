CREATE TABLE user
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);