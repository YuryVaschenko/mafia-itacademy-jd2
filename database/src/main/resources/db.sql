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
  id         INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  name       VARCHAR(30)  NOT NULL,
  address_id INT UNSIGNED UNIQUE,
  PRIMARY KEY (id),
  FOREIGN KEY (address_id) REFERENCES addresses (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE debtors (
  id              INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  first_name      VARCHAR(50),
  middle_name     VARCHAR(50),
  last_name       VARCHAR(50),
  nickname        VARCHAR(50),
  debt_amount     INT          NOT NULL,
  exp_date        DATE,
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
CREATE TABLE caporegimes (
  member_id INT UNSIGNED NOT NULL,
  email     VARCHAR(50)  NOT NULL,
  FOREIGN KEY (member_id) REFERENCES members (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE groups (
  id            INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  clan_id       INT UNSIGNED NOT NULL,
  caporegime_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (clan_id) REFERENCES clans (id),
  FOREIGN KEY (caporegime_id) REFERENCES caporegimes (member_id)
)
  DEFAULT CHARSET = utf8;


