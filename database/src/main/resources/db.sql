DROP DATABASE IF EXISTS mafia_july2017_jd2;

CREATE DATABASE mafia_july2017_jd2;

USE mafia_july2017_jd2;

DROP TABLE IF EXISTS locations;

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
  PRIMARY KEY (id),
  FOREIGN KEY (address_id) REFERENCES addresses (id)
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
  PRIMARY KEY (id),
  FOREIGN KEY (clan_id) REFERENCES clans (id)
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
CREATE TABLE users (
  id        INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  login     VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(60)  NOT NULL,
  role      VARCHAR(50)  NOT NULL,
  member_id INT UNSIGNED NOT NULL UNIQUE,
  PRIMARY KEY (id),
  FOREIGN KEY (member_id) REFERENCES members (id)
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


