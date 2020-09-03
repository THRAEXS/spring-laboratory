DROP DATABASE IF EXISTS `thraex-admin`;

CREATE DATABASE IF NOT EXISTS `thraex-admin` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS `thraex-admin`.tbl_user;
CREATE TABLE `thraex-admin`.tbl_user (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nickname VARCHAR(100),
    username VARCHAR(100),
    password VARCHAR(200),
    enabled BOOLEAN,
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
);

DROP TABLE IF EXISTS `thraex-admin`.tbl_menu;
CREATE TABLE `thraex-admin`.tbl_menu (
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
);

DROP TABLE IF EXISTS `thraex-admin`.tbl_role;
CREATE TABLE `thraex-admin`.tbl_role (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    code VARCHAR(100),
    remark VARCHAR(200),
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
);

DROP TABLE IF EXISTS `thraex-admin`.tbl_user_role;
CREATE TABLE `thraex-admin`.tbl_user_role (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    uid VARCHAR(36),
    rid VARCHAR(36)
);

DROP TABLE IF EXISTS `thraex-admin`.tbl_role_menu;
CREATE TABLE `thraex-admin`.tbl_role_menu (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    rid VARCHAR(36),
    mid VARCHAR(36)
);

DROP TABLE IF EXISTS `thraex-admin`.tbl_file_descriptor;
CREATE TABLE `thraex-admin`.tbl_file_descriptor (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(200),
    path VARCHAR(500),
    content_type VARCHAR(50),
    size BIGINT,
    create_by VARCHAR(36),
    create_time DATETIME,
    update_by VARCHAR(36),
    update_time DATETIME
);

-- Initialization data
INSERT INTO `thraex-admin`.tbl_menu (id, name, code, url, pid, level_code, remark, disabled, create_by, create_time, update_by, update_time) VALUES ('a10a60d2a8e7fd120970efa28bc6883b', '系统设置', 'MENU_SETTING', '', null, '00000001', null, 0, 'admin', '2020-08-28 13:07:51', null, null);
INSERT INTO `thraex-admin`.tbl_menu (id, name, code, url, pid, level_code, remark, disabled, create_by, create_time, update_by, update_time) VALUES ('1b54507585eeca8f746aa1ec26c29d5c', '菜单管理', 'MENU_MENU', '/menu', 'a10a60d2a8e7fd120970efa28bc6883b', '0000000100000001', null, 0, 'admin', '2020-08-28 13:08:09', null, null);
INSERT INTO `thraex-admin`.tbl_menu (id, name, code, url, pid, level_code, remark, disabled, create_by, create_time, update_by, update_time) VALUES ('a2ebef150e63efc88b6bf2f45af0d4aa', '用户管理', 'MENU_USER', '/user', 'a10a60d2a8e7fd120970efa28bc6883b', '0000000100000002', null, 0, 'admin', '2020-08-28 13:08:54', null, null);
INSERT INTO `thraex-admin`.tbl_menu (id, name, code, url, pid, level_code, remark, disabled, create_by, create_time, update_by, update_time) VALUES ('0aaab910cde409ca44073704672ab041', '角色管理', 'MENU_ROLE', '/role', 'a10a60d2a8e7fd120970efa28bc6883b', '0000000100000003', null, 0, 'admin', '2020-08-28 13:09:11', null, null);
INSERT INTO `thraex-admin`.tbl_menu (id, name, code, url, pid, level_code, remark, disabled, create_by, create_time, update_by, update_time) VALUES ('8ea43cfc20e3de5b4bcd157efb0a5f55', '授权管理', 'MENU_GRANT', '/grant', 'a10a60d2a8e7fd120970efa28bc6883b', '0000000100000004', null, 0, 'admin', '2020-08-28 13:09:48', null, null);

INSERT INTO `thraex-admin`.tbl_role (id, name, code, remark, create_by, create_time, update_by, update_time) VALUES ('f99c160529467ebcbbdf9853ed000515', '管理员', 'ADMIN', '', 'admin', '2020-08-28 14:43:33', null, null);
INSERT INTO `thraex-admin`.tbl_role (id, name, code, remark, create_by, create_time, update_by, update_time) VALUES ('58ddea5881aa61470c3eef3b67922541', '维护人员', 'MAINTAINERS', '', 'admin', '2020-08-28 15:06:04', null, null);

INSERT INTO `thraex-admin`.tbl_user (id, nickname, username, password, enabled, create_by, create_time, update_by, update_time) VALUES ('49803ceafc6124c50d908c08dba2d799', 'Editor', 'editor', '$2a$10$oO6CEpQ7ewnTbyh6y3FE5uyO.WgfE/BBftZplci4MWg/FQI3d9ScW', 1, 'admin', '2020-08-31 01:36:08', null, null);
