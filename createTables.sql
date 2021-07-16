CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username varchar(20) NOT NULL,
    password varchar(200) NOT NULL
);

CREATE TABLE boats (
    id SERIAL PRIMARY KEY,
    class varchar(20) NOT NULL,
    access varchar(20) NOT NULL,
    side varchar(20) DEFAULT NULL,
    borrowerfn varchar(50) DEFAULT NULL,
    borrowerno varchar(20) DEFAULT NULL
);