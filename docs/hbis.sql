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
