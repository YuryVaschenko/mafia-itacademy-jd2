DROP DATABASE IF EXISTS mafia_july2017_jd2;

CREATE DATABASE mafia_july2017_jd2;

USE mafia_july2017_jd2;

DROP TABLE IF EXISTS locations;
CREATE TABLE users (
  id       INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  login    VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(60)  NOT NULL,
  role     VARCHAR(50)  NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE locations (
  id        INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  latitude  VARCHAR(20)  NOT NULL,
  longitude VARCHAR(20)  NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE addresses (
  id          INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  country     VARCHAR(50),
  city        VARCHAR(50),
  street      VARCHAR(50),
  house       VARCHAR(10),
  location_id INT UNSIGNED UNIQUE,
  PRIMARY KEY (id),
  FOREIGN KEY (location_id) REFERENCES locations (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE clans (
  id          INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  name        VARCHAR(30)  NOT NULL UNIQUE,
  location_id INT UNSIGNED,
  PRIMARY KEY (id),
  FOREIGN KEY (location_id) REFERENCES locations (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE debtors (
  id              INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  first_name      VARCHAR(50),
  middle_name     VARCHAR(50),
  last_name       VARCHAR(50),
  nickname        VARCHAR(50),
  debt_amount     BIGINT       NOT NULL,
  exp_date        DATE         NOT NULL,
  percent_per_day SMALLINT     NOT NULL        DEFAULT 10,
  frequency       VARCHAR(30)  NOT NULL,
  address_id      INT UNSIGNED,
  clan_id         INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (address_id) REFERENCES addresses (id),
  FOREIGN KEY (clan_id) REFERENCES clans (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE members (
  id          INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  first_name  VARCHAR(50),
  middle_name VARCHAR(50),
  last_name   VARCHAR(50),
  nickname    VARCHAR(50),
  status      VARCHAR(50)  NOT NULL,
  clan_id     INT UNSIGNED NOT NULL,
  user_id     INT UNSIGNED NOT NULL UNIQUE,
  PRIMARY KEY (id),
  FOREIGN KEY (clan_id) REFERENCES clans (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE groups (
  id      INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  clan_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (clan_id) REFERENCES clans (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE caporegimes (
  member_id INT UNSIGNED NOT NULL,
  email     VARCHAR(50)  NOT NULL,
  group_id  INT UNSIGNED,
  FOREIGN KEY (member_id) REFERENCES members (id),
  FOREIGN KEY (group_id) REFERENCES groups (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE soldiers (
  member_id      INT UNSIGNED NOT NULL,
  specialization VARCHAR(50)  NOT NULL,
  group_id       INT UNSIGNED,
  FOREIGN KEY (member_id) REFERENCES members (id),
  FOREIGN KEY (group_id) REFERENCES groups (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE authorities (
  member_id INT UNSIGNED      NOT NULL,
  is_boss   SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (member_id) REFERENCES members (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE black_list (
  id          INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  first_name  VARCHAR(50),
  middle_name VARCHAR(50),
  last_name   VARCHAR(50),
  nickname    VARCHAR(50),
  status      VARCHAR(40),
  location_id INT UNSIGNED,
  PRIMARY KEY (id),
  FOREIGN KEY (location_id) REFERENCES locations (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE affairs (
  id            INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  title         VARCHAR(50)  NOT NULL,
  description   TEXT,
  exp_date      DATE,
  status        VARCHAR(40)  NOT NULL,
  debtor_id     INT UNSIGNED,
  black_list_id INT UNSIGNED,
  location_id   INT UNSIGNED,
  PRIMARY KEY (id),
  FOREIGN KEY (debtor_id) REFERENCES debtors (id),
  FOREIGN KEY (black_list_id) REFERENCES black_list (id),
  FOREIGN KEY (location_id) REFERENCES locations (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE affairs_in_groups (
  affair_id INT UNSIGNED NOT NULL,
  group_id  INT UNSIGNED NOT NULL,
  PRIMARY KEY (affair_id, group_id),
  FOREIGN KEY (affair_id) REFERENCES affairs (id),
  FOREIGN KEY (group_id) REFERENCES groups (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE reports (
  id            INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  affair_id     INT UNSIGNED NOT NULL,
  caporegime_id INT UNSIGNED NOT NULL,
  content       TEXT         NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (affair_id) REFERENCES affairs (id),
  FOREIGN KEY (caporegime_id) REFERENCES caporegimes (member_id)
)
  DEFAULT CHARSET = utf8;

INSERT INTO locations (latitude, longitude) VALUES ('27.525773', '53.89079');
INSERT INTO locations (latitude, longitude) VALUES ('23.832701', '53.656356');
INSERT INTO locations (latitude, longitude) VALUES ('30.201058', '55.191204');
INSERT INTO locations (latitude, longitude) VALUES ('31.016844', '52.421810');

INSERT INTO clans (name, location_id) VALUES ('Corleone', 1);
INSERT INTO clans (name, location_id) VALUES ('Benedetti', 2);
INSERT INTO clans (name, location_id) VALUES ('Alcamo', 3);
INSERT INTO clans (name, location_id) VALUES ('Trapani', 4);

INSERT INTO users (login, password, role)
VALUES ('root', '$2a$10$HyYGyqAgSplcIpFuhTk/4eVsbfpcETSgCAHJMcyZkIG/841rIn4fq', 'AUTHORITY');

INSERT INTO members (first_name, last_name, status, clan_id, user_id)
VALUES ('Leonardo', 'Ortolano', 'AVAILABLE', 4, 1);

INSERT INTO authorities (member_id, is_boss) VALUES (1, 1);

INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Kolasa', '55');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Pushkina', '20');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Vitebsk', 'Babushkina', '3');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Grodno', 'Pobedy', '9');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Grodno', 'Zavodskaya', '12');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Gagarina', '30');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Nemanskaya', '23');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Gurskogo', '8');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Gurskogo', '15');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Alibegova', '5');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Nahimova', '33');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Storozhevskaya', '7');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Golodeda', '12');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Minsk', 'Golodeda', '35');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Gomel', 'Svetlaya', '4');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Gomel', 'Lenina', '19');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Mogilev', 'Lenina', '56');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Lida', 'Sovetskaya', '64');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Vitebsk', 'Nagornaya', '37');
INSERT INTO addresses (country, city, street, house) VALUES ('Belarus', 'Vitebsk', 'Lidskaya', '41');

INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Pietro', '', 'Diliberto', '', 100000, '13.10.2017', 10, 'ONCE', 1, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Ciro', '', 'Coniglio', '', 350000, '13.12.2017', 5, 'ONCE', 2, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Mensel', '', 'Jusuph', '', 50000, '06.09.2017', 5, 'ONCE', 3, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Alfonso', '', '', '', 75000, '01.03.2018', 5, 'ONCE', 4, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Stefano', '', 'Bontade', '', 45000, '02.02.2018', 10, 'ONCE', 5, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Francesco', 'Paolo', 'Bontade', '', 45000, '15.10.2017', 10, 'ONCE', 6, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Pietro', '', 'Scaglione', '', 120000, '25.09.2017', 10, 'ONCE', 7, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('John', '', 'Gambino', '', 90000, '10.08.2017', 10, 'ONCE', 8, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Angelo', '', 'Bruno', '', 30000, '10.08.2017', 15, 'ONCE', 9, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Boris', '', 'Giuliano', '', 15000, '26.08.2017', 10, 'ONCE', 10, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Giulio', '', 'Andreotti', '', 7000, '29.08.2017', 15, 'ONCE', 11, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Croceverde', '', 'Giardini', '', 39000, '15.08.2017', 20, 'ONCE', 12, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Pietro', '', 'Greco', 'the engineer', 17000, '09.09.2017', 15, 'ONCE', 13, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Salvatore', '', 'Pisa', '', 45000, '14.08.2017', 10, 'ONCE', 14, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Tommaso', '', 'Buscetta', 'toto the tall', 60000, '23.08.2017', 10, 'ONCE', 15, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Gaetano', '', 'Badalametti', '', 86000, '19.08.2017', 10, 'ONCE', 16, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Salvatore', '', 'Inzerillo', '', 95000, '14.07.2017', 10, 'ONCE', 17, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Vincenzo', '', 'Puccio', '', 65000, '16.07.2017', 10, 'ONCE', 18, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Bernardo', '', 'Povenzano', '', 45000, '04.07.2017', 5, 'ONCE', 19, 4);
INSERT INTO debtors (first_name, middle_name, last_name, nickname, debt_amount, exp_date, percent_per_day, frequency, address_id, clan_id)
VALUES ('Salvatore', '', 'Riina', '', 34000, '06.07.2017', 10, 'ONCE', 20, 4);
