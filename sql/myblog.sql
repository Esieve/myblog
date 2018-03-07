CREATE DATABASE myblog;

USE myblog;

CREATE TABLE user (
  user_id  INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  #   user_type INT DEFAULT 0 COMMENT '0代表普通用户，1代表管理员',
  username VARCHAR(20) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  image    VARCHAR(50)          DEFAULT NULL
  COMMENT '头像'
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

#todo
# INSERT INTO category VALUES(NULL,"about");
# INSERT INTO user VALUES(NULL,1,'admin','ICy5YqxZB1uWSwcVLSNLcA==','ted.jpg');
# INSERT INTO article VALUES(NULL,1,1,"about",1,NOW(),0,"root");