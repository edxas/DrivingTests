DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tests;
DROP TABLE IF EXISTS question;
CREATE TABLE users
(
    id           INTEGER GENERATED BY DEFAULT AS IDENTITY,
    Email        VARCHAR(255),
    Name         VARCHAR(255),
    Password     VARCHAR(255),
    Role         ENUM('admin', 'user', 'guest'),
    Surname      VARCHAR(255),
    Username     VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE tests
(
    id                      INTEGER GENERATED BY DEFAULT AS IDENTITY,
    userId                  INTEGER,
    questionIdList          VARCHAR(255),
    userChosenAnswerList    VARCHAR(255),
    score                   INTEGER,
    topic                   ENUM('random', 'signs'),
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users(id)
);

CREATE TABLE question
(
    id              INTEGER GENERATED BY DEFAULT AS IDENTITY,
    topic           ENUM('random', 'signs','driving_priority'),
    question        VARCHAR(255),
    answers         VARCHAR(255),
    correct_answers  VARCHAR(255),
    PRIMARY KEY (id)
);

