DROP TABLE IF EXISTS `thraex-admin`.tbl_advert;
CREATE TABLE `thraex-admin`.tbl_advert (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    fid VARCHAR(36)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
