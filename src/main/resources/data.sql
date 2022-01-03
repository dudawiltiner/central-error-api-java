CREATE TABLE users(
    id INT NOT NULL,
  	login VARCHAR(255) NOT NULL,
  	password VARCHAR(255) NOT NULL,
  	PRIMARY KEY(id)
);

INSERT INTO users
VALUES (1, 'duda@gmail.com', '123');

--INSERT INTO levelerrors
--VALUES
--(1, 'error', 0);
--(2, 'warning', 0);
--(3, 'info', 0);