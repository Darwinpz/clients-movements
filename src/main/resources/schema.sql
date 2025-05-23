CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS movement CASCADE;

CREATE TABLE IF NOT EXISTS client (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    identification VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    gender CHAR(1) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(10),
    password VARCHAR(100) NOT NULL,
    state BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS account (
    number SERIAL PRIMARY KEY ,
    type VARCHAR(50) NOT NULL,
    balance NUMERIC(15,2) NOT NULL,
    state BOOLEAN NOT NULL DEFAULT TRUE,
    client UUID NOT NULL,
    CONSTRAINT fkClient FOREIGN KEY (client) REFERENCES client(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS movement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(50) NOT NULL,
    amount NUMERIC(15,2) NOT NULL,
    balance NUMERIC(15,2) NOT NULL,
    account INTEGER NOT NULL,
    marker TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fkAccount FOREIGN KEY (account) REFERENCES account(number) ON DELETE CASCADE ON UPDATE CASCADE
);
