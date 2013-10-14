DROP SCHEMA PUBLIC CASCADE

CREATE SEQUENCE seq1 AS INTEGER START WITH 1

CREATE TABLE person (
       id BIGINT NOT NULL PRIMARY KEY,
       name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE phone (
       id BIGINT NOT NULL PRIMARY KEY,
       number VARCHAR(255) NOT NULL,
       person_id BIGINT NOT NULL,
       FOREIGN KEY (person_id)
          REFERENCES person ON DELETE RESTRICT
);

CREATE TABLE address (
       id BIGINT NOT NULL PRIMARY KEY,
       address VARCHAR(255) NOT NULL,
       person_id BIGINT NOT NULL,
       FOREIGN KEY (person_id)
          REFERENCES person ON DELETE RESTRICT
);
