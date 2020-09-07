DROP DATABASE IF EXISTS hbis;

CREATE DATABASE IF NOT EXISTS hbis DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS hbis.tbl_user;
CREATE TABLE hbis.tbl_user (
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

DROP TABLE IF EXISTS hbis.tbl_menu;
CREATE TABLE hbis.tbl_menu (
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

DROP TABLE IF EXISTS hbis.tbl_role;
CREATE TABLE hbis.tbl_role (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    code VARCHAR(100),
    remark VARCHAR(200),
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS hbis.tbl_user_role;
CREATE TABLE hbis.tbl_user_role (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    uid VARCHAR(36),
    rid VARCHAR(36)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS hbis.tbl_role_menu;
CREATE TABLE hbis.tbl_role_menu (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    rid VARCHAR(36),
    mid VARCHAR(36)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS hbis.tbl_file_descriptor;
CREATE TABLE hbis.tbl_file_descriptor (
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

DROP TABLE IF EXISTS hbis.tbl_advert;
CREATE TABLE hbis.tbl_advert (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    fid VARCHAR(36)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS hbis.tbl_case;
CREATE TABLE hbis.tbl_case (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    fid VARCHAR(36),
    name VARCHAR(200),
    cover VARCHAR(500),
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS hbis.tbl_company;
CREATE TABLE hbis.tbl_company (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(200),
    address VARCHAR(200),
    zip_code VARCHAR(100),
    phone VARCHAR(100),
    fax VARCHAR(100),
    contact VARCHAR(100),
    mobile VARCHAR(100),
    cover_id VARCHAR(36),
    cover_path VARCHAR(500),
    map_id VARCHAR(36),
    map_path VARCHAR(500),
    introduction MEDIUMTEXT,
    situation MEDIUMTEXT,
    organization MEDIUMTEXT,
    scope MEDIUMTEXT,
    personnel MEDIUMTEXT,
    history MEDIUMTEXT,
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS hbis.tbl_news;
CREATE TABLE hbis.tbl_news (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    title VARCHAR(200),
    author VARCHAR(100),
    hits BIGINT,
    content MEDIUMTEXT,
    type INT COMMENT '0: company; 10: industry',
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
