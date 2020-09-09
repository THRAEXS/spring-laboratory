DROP DATABASE IF EXISTS agriculture;

CREATE DATABASE IF NOT EXISTS agriculture DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS agriculture.tbl_user;
CREATE TABLE agriculture.tbl_user (
     id VARCHAR(36) NOT NULL PRIMARY KEY,
     nickname VARCHAR(100),
     username VARCHAR(100),
     password VARCHAR(200),
     enabled BOOLEAN,
     create_by VARCHAR(36),
     create_time DATETIME,
     update_by VARCHAR(36),
     update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS agriculture.tbl_menu;
CREATE TABLE agriculture.tbl_menu (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    code VARCHAR(100),
    url VARCHAR(200),
    pid VARCHAR(36),
    level_code VARCHAR(200),
    remark VARCHAR(200),
    disabled BOOLEAN,
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS agriculture.tbl_dict;
CREATE TABLE agriculture.tbl_dict (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    code VARCHAR(100),
    pid VARCHAR(36),
    level_code VARCHAR(200),
    remark VARCHAR(200),
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS agriculture.tbl_role;
CREATE TABLE agriculture.tbl_role (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    code VARCHAR(100),
    remark VARCHAR(200),
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS agriculture.tbl_user_role;
CREATE TABLE agriculture.tbl_user_role (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    uid VARCHAR(36),
    rid VARCHAR(36)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS agriculture.tbl_role_menu;
CREATE TABLE agriculture.tbl_role_menu (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    rid VARCHAR(36),
    mid VARCHAR(36)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS agriculture.tbl_file_descriptor;
CREATE TABLE agriculture.tbl_file_descriptor (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(200),
    path VARCHAR(500),
    content_type VARCHAR(50),
    size BIGINT,
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
