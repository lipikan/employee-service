DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  emp_id VARCHAR(30) PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  gender VARCHAR(250) NOT NULL,
  title VARCHAR(250) NOT NULL
);

CREATE TABLE address (
  emp_id VARCHAR(30),
  street VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL,
  state VARCHAR(250) NOT NULL,
  post_code INT NOT NULL,
  PRIMARY KEY (emp_id),
  FOREIGN KEY (emp_id) REFERENCES employees (emp_id) ON DELETE CASCADE
);

INSERT INTO employees (emp_id, first_name, last_name, gender, title) VALUES
('12231', 'Aliko', 'Dangote', 'male', 'Mr'),
('12232', 'Bill', 'Gates', 'male', 'Mr'),
('12233','Folrunsho', 'Alakija', 'female', 'Ms'),
('12234','Oprah', 'Winfrey', 'female', 'Ms');

INSERT INTO address (emp_id, street, city, state, post_code) VALUES
('12231', '1 Market Street', 'San Francisco', 'CA', 1122),
('12232', '2 Elm Street', 'San Francisco', 'CA', 1221),
('12233', '3 Main Street', 'Boston', 'MA', 1226),
('12234', '3 Phillip Street', 'Miami', 'Florida', 1213);