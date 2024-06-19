CREATE TABLE Users (
    username VARCHAR(50) PRIMARY KEY,
    nomUser VARCHAR(50) NOT NULL UNIQUE,
    communes VARCHAR(140)
);

CREATE USER "user" IDENTIFIED BY "user";
GRANT ALL ON bdSAE.* TO "user";

CREATE USER "user2" IDENTIFIED BY "user2";
GRANT ALL ON bdSAE.* TO "user2";

INSERT INTO Users (username, nomUser, communes) VALUES
("user", "Utilisateur 1", "56260;56091;56070;29188"),
("user2", "Utilisateur 2", "29151");
