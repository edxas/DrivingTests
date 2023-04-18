DROP TABLE IF EXISTS salt cascade;
DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS tests cascade;
DROP TABLE IF EXISTS questions cascade;

DROP TABLE IF EXISTS question_test cascade;
CREATE TABLE salt
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY,
    Salt          BINARY VARYING(MAX) ,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id           INTEGER GENERATED BY DEFAULT AS IDENTITY,
    Email        VARCHAR(255),
    Name         VARCHAR(255),
    Password     VARCHAR(255),
    Role         ENUM('admin', 'user', 'guest'),
    Surname      VARCHAR(255),
    Username     VARCHAR(255),
    HashPassword VARCHAR(255),
    saltid                  INTEGER,

    PRIMARY KEY (id),
    FOREIGN KEY (saltid) REFERENCES salt(id)
);

CREATE TABLE tests
(
    id                      INTEGER GENERATED BY DEFAULT AS IDENTITY,
    userid                  INTEGER,
    user_chosen_answer_list    VARCHAR(255),
    score                   DOUBLE PRECISION,
    topic                   ENUM('random', 'signs','driving_priority'),
    PRIMARY KEY (id),
    FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE questions
(
    id                  INTEGER GENERATED BY DEFAULT AS IDENTITY,
    topic               ENUM('random', 'signs','driving_priority'),
    question            VARCHAR(255),
    answers             VARCHAR(1000),
    correct_answers     VARCHAR(1000),
    hint                VARCHAR(255),
    question_photo      BLOB,
    PRIMARY KEY (id)
);

CREATE TABLE question_test (

 test_id INTEGER,
  question_id INTEGER
,CONSTRAINT question_test_pk PRIMARY KEY ( test_id,question_id)

,
  FOREIGN KEY (test_id) REFERENCES tests (id),
  FOREIGN KEY (question_id) REFERENCES questions (id)
);

