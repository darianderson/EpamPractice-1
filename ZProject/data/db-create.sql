
##CREATE DATABASE railways;
##GRANT ALL ON railways.* TO 'blackwell'@'localhost' IDENTIFIED BY 'salt';
##use railways;

DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS train_carriage;
DROP TABLE IF EXISTS carriages;
DROP TABLE IF EXISTS carriage_class;
DROP TABLE IF EXISTS carriage_type;
DROP TABLE IF EXISTS routes;
DROP TABLE IF EXISTS stations;
DROP TABLE IF EXISTS countries;

######################################################
## trains and there models
######################################################
CREATE TABLE models(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  model VARCHAR(32)
) ENGINE=InnoDB;

INSERT INTO models (model)
  VALUES ('Hyundai Rotem'), ('Bombardier AVE S#102'), ('Taggart comet');


CREATE TABLE trains(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  model_id INTEGER UNSIGNED NOT NULL,
  since DATE,

  FOREIGN KEY (model_id) REFERENCES models(id)
) ENGINE=InnoDB;

INSERT INTO trains (model_id, since)
  VALUES (1, '20120618'), (2, '20060123'), (3, '20170305'), (2, '20150223');


######################################################
## stations and routes
######################################################
CREATE TABLE countries(
  id INTEGER UNSIGNED NOT NULL UNIQUE PRIMARY KEY,
  full_name VARCHAR(32),
  short_name VARCHAR(4)
) ENGINE=InnoDB;

INSERT INTO countries
  VALUES (724, 'Spain', 'ES'), (826, 'United Kingdom', 'GB'), (578, 'Norway', 'NO');

CREATE TABLE stations(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  name VARCHAR(32),
  country_id INTEGER UNSIGNED NOT NULL,

  FOREIGN KEY (country_id) REFERENCES countries(id)
) ENGINE=InnoDB;

INSERT INTO stations (name, country_id)
  VALUES ('Arc de Triomf', 724), ('Muntaner', 724), ('Sants', 724), ('Sarrià', 724), ('Arc de Triomf station', 724),
  ('Abbey Wood', 826), ('Lambeg', 826), ('Old Hill', 826), ('Roby', 826), ('Roose', 826),
  ('Arna', 578), ('Dale', 578), ('Myrdal', 578), ('Urdland', 578);


CREATE TABLE routes(
  id INTEGER UNSIGNED NOT NULL,
  train_id INTEGER UNSIGNED NOT NULL,
  station_id INTEGER UNSIGNED NOT NULL,
  arrival DATE,
  departure DATE,

  FOREIGN KEY (station_id) REFERENCES stations(id),
  FOREIGN KEY (train_id) REFERENCES trains(id)
) ENGINE=InnoDB;

INSERT INTO routes VALUES
  (345, 2, 1, '20180709 10:35:00 AM', '20180709 10:55:00 AM'), (345, 2, 4, '20180709 11:48:00 AM', '20180709 11:58:00 AM'),
  (345, 2, 2, '20180709 02:18:00 PM', '20180709 02:25:00 AM'), (345, 2, 5, '20180709 04:48:00 PM', '20180709 04:35:00 AM'),
  (784, 4, 6, '20180710 02:00:00 AM', '20180710 02:10:00 AM'), (784, 4, 8, '20180710 04:39:00 AM', '20180710 04:50:00 AM'),
  (784, 4, 10, '20180710 05:21:00 AM', '20180710 06:00:00 AM'), (784, 4, 7, '20180710 08:00:00 AM', '20180710 08:12:00 AM'),
  (784, 4, 9, '20180710 09:57:00 AM', '20180710 10:34:00 AM');


######################################################
## carriage
######################################################

CREATE TABLE carriage_type(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  type VARCHAR(32)
) ENGINE=InnoDB;

INSERT INTO carriage_type(type) VALUES
  ('compartment'), ('berth'), ('common');


CREATE TABLE carriage_class(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  class VARCHAR(32)
) ENGINE=InnoDB;

INSERT INTO carriage_class(class) VALUES
  ('economy'), ('business'), ('premium');


CREATE TABLE carriages(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  type INTEGER UNSIGNED NOT NULL,
  class INTEGER UNSIGNED NOT NULL,
  total_places INTEGER UNSIGNED NOT NUll,

  FOREIGN KEY (type) REFERENCES carriage_type(id),
  FOREIGN KEY (class) REFERENCES carriage_class(id)
) ENGINE=InnoDB;

INSERT INTO carriages(type, class, total_places) VALUES
  (1, 1, 50), (1, 3, 25), (3, 2, 20), (3, 3, 10);


CREATE TABLE train_carriage(
  train_id INTEGER UNSIGNED NOT NULL,
  carriage_id INTEGER UNSIGNED NOT NULL,
  carriage_no INTEGER UNSIGNED NOT NULL,
  price INTEGER,

  FOREIGN KEY (train_id) REFERENCES trains(id),
  FOREIGN KEY (carriage_id) REFERENCES carriages(id)
) ENGINE=InnoDB;

INSERT INTO train_carriage VALUES
  (1, 1, 1, 20), (1, 1, 2, 20), (1, 3, 4, 50), (1, 2, 5, 35),
  (2, 1, 1, 20), (2, 1, 2, 20), (2, 3, 4, 50), (2, 2, 5, 35),
  (3, 2, 3, 20), (3, 3, 2, 20), (3, 3, 4, 50), (2, 2, 5, 35),
  (4, 1, 1, 20), (4, 1, 1, 20), (4, 3, 4, 50), (4, 2, 5, 35);


######################################################
## users
######################################################


CREATE TABLE roles(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  role VARCHAR(32)
) ENGINE=InnoDB;
INSERT INTO roles(role) VALUES ('admin'), ('client');

CREATE TABLE users(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT KEY,
  login VARCHAR(32) UNIQUE NOT NULL,
  password VARCHAR(32) NOT NULL,
  name VARCHAR(32),
  surname VARCHAR(32),
  role_id INTEGER UNSIGNED NOT NULL,

  FOREIGN KEY (role_id) REFERENCES roles(id)
) ENGINE=InnoDB;

INSERT INTO users VALUES (DEFAULT, 'bombardier', 'canadarules', 'Joseph#Armand', 'Bombardier', 1),
  (DEFAULT, 'preston', 'prestonpass', 'Jeffrey', 'Preston', 2);


CREATE TABLE tickets(
  user_id INTEGER UNSIGNED NOT NULL,
  route_id INTEGER UNSIGNED NOT NULL,
  carriage_no INTEGER UNSIGNED NOT NULL,
  place INTEGER,

  FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB;

INSERT INTO tickets VALUE (2, 345, 2, 32);


SELECT * FROM countries;
SELECT * FROM stations;
SELECT * FROM routes;
SELECT * FROM carriage_type;
SELECT * FROM carriage_class;
SELECT * FROM carriages;
SELECT * FROM train_carriage;
SELECT * FROM roles;
SELECT * FROM users;
SELECT * FROM tickets;