DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 password VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO employees (id, name, password) VALUES (1, "satoshi", "0000");
