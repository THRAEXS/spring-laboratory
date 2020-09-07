DROP TABLE IF EXISTS `thraex-admin`.tbl_advert;
CREATE TABLE `thraex-admin`.tbl_advert (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    fid VARCHAR(36)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `thraex-admin`.tbl_case;
CREATE TABLE `thraex-admin`.tbl_case (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    fid VARCHAR(36),
    name VARCHAR(200),
    cover VARCHAR(500),
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `thraex-admin`.tbl_company;
CREATE TABLE `thraex-admin`.tbl_company (
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

DROP TABLE IF EXISTS `thraex-admin`.tbl_news;
CREATE TABLE `thraex-admin`.tbl_news (
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
