#  DROP DATABASE practice10;
#  CREATE DATABASE practice10;
#  USE practice10;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

# GRANT ALL ON practice10.* TO 'blackwell'@'localhost' IDENTIFIED BY 'salt';
CREATE TABLE roles(
  id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(32)
) ENGINE=InnoDB;

CREATE TABLE users(
  login VARCHAR(32) UNIQUE NOT NULL,
  password VARCHAR(32),
  role_id INTEGER,
  FOREIGN KEY (role_id) REFERENCES roles(id)
) ENGINE=InnoDB;



INSERT INTO roles VALUES  (DEFAULT, 'admin'),
                          (DEFAULT, 'client');

INSERT INTO users VALUES  ('ivanov', 'ivanovrules', 2),
                          ('admin', 'iamadminhaha', 1);
