CREATE DATABASE myblog;

USE myblog;

CREATE TABLE user (
  user_id    INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(20) NOT NULL UNIQUE,
  password   VARCHAR(50) NOT NULL,
  image      VARCHAR(50)          DEFAULT NULL
  COMMENT '头像',
  background VARCHAR(50)          DEFAULT NULL
  COMMENT '背景图片',
  biography  VARCHAR(50)          DEFAULT NULL
  COMMENT '个人简介'
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE category (
  category_id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(20) NOT NULL UNIQUE
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE article (
  article_id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_id  INT,
  user_id      INT,
  title        VARCHAR(50) NOT NULL,
  content      MEDIUMTEXT,
  publish_time DATETIME    NOT NULL
  COMMENT '发布日期',
  update_time  DATETIME    NOT NULL
  COMMENT '最后更新日期',
  clicks       INT                  DEFAULT 0
  COMMENT '点击率',
  image        VARCHAR(50)          DEFAULT NULL
  COMMENT '封面图片',
  is_about     TINYINT(1)           DEFAULT 0
  COMMENT '0代表普通文章，1代表关于',
  FOREIGN KEY (category_id)
  REFERENCES category (category_id)
    ON DELETE SET NULL,
  FOREIGN KEY (user_id)
  REFERENCES user (user_id)
    ON DELETE SET NULL
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE link (
  link_id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  link_name VARCHAR(20) NOT NULL UNIQUE,
  url       VARCHAR(50) NOT NULL
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

INSERT INTO user VALUES (NULL, 'admin', 'ISMvKXpXpadDiUoOSoAfww==', 'ted.jpg', 'mountain.jsp', 'Introduce yourself...');
INSERT INTO article (content, publish_time, update_time, is_about) VALUES ("about", now(), now(), 1);