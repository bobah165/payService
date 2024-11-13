--DROP SCHEMA IF EXISTS general CASCADE;

CREATE SCHEMA IF NOT EXISTS general;

--UTILS SCHEMA

CREATE TABLE general.order
(
    id varchar NOT NULL,
    name varchar NOT NULL,
    price bigint NOT NULL,
    status varchar NOT NULL,
    CONSTRAINT pk_order
        PRIMARY KEY (id)
);

CREATE TABLE general.account
(
    id varchar NOT NULL,
    name varchar NOT NULL,
    sum bigint NOT NULL,
    is_withdrawn boolean NOT NULL,
    CONSTRAINT pk_account
        PRIMARY KEY (id)
);


