DROP DATABASE IF EXISTS mafia_july2017_jd2;

CREATE DATABASE mafia_july2017_jd2;

USE mafia_july2017_jd2;

DROP TABLE IF EXISTS locations;

CREATE TABLE location (
  id        INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  latitude  VARCHAR(20)  NOT NULL,
  longitude VARCHAR(20)  NOT NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
CREATE TABLE addresses (
  id          INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
  country     VARCHAR(30),
  city        VARCHAR(30),
  street      VARCHAR(30),
  house       SMALLINT UNSIGNED,
  location_id INT UNSIGNED UNIQUE,
  PRIMARY KEY (id),
  FOREIGN KEY (location_id) REFERENCES location (id)
)
  DEFAULT CHARSET = utf8;


