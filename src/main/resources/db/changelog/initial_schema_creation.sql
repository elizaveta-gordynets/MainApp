--liquibase formatted sql

--changeset egordinets:1
CREATE table user_info (
    id TEXT PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    pass TEXT NOT NULL
);

--changeset liz:2
CREATE TABLE IF NOT EXISTS vacancy (
    id TEXT PRIMARY KEY NOT NULL,
    header TEXT,
    description TEXT
);

--changeset liz:3
ALTER TABLE user_info ADD gender TEXT;
ALTER TABLE user_info ADD email TEXT;
ALTER TABLE user_info ADD userpic TEXT;
ALTER TABLE user_info DROP COLUMN pass;

--changeset liz:4 runOnChange:true
--ALTER TABLE user_info ADD provider TEXT;
ALTER TABLE user_info ADD pass TEXT;
ALTER TABLE user_info ADD token_id TEXT;

--changeset liz:5
CREATE TABLE token (
    id TEXT NOT NULL PRIMARY KEY,
    user_id TEXT NOT NULL
);
ALTER TABLE token ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user_info;
ALTER TABLE user_info ADD CONSTRAINT token_id FOREIGN KEY (token_id) REFERENCES token;

--changeset liz:6
CREATE TABLE IF NOT EXISTS authority (
    id TEXT NOT NULL PRIMARY KEY ,
    name TEXT NOT NULL ,
    user_id TEXT
);

ALTER TABLE authority ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user_info;

--changeset liz:7 runOnChange:true
CREATE TABLE IF NOT EXISTS user_authorities (
    user_id TEXT NOT NULL,
    role_id TEXT NOT NULL

);
ALTER TABLE user_authorities ADD CONSTRAINT user_role_pk PRIMARY KEY (user_id, role_id);
ALTER TABLE user_authorities ADD CONSTRAINT user_role_fk FOREIGN KEY (user_id) REFERENCES user_info (id);
ALTER TABLE user_authorities ADD CONSTRAINT user_role_fk1 FOREIGN KEY (role_id) REFERENCES authority (id);