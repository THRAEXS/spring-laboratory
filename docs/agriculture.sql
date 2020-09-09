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

DROP TABLE IF EXISTS agriculture.tbl_project;
CREATE TABLE agriculture.tbl_project (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    item_no VARCHAR(100) COMMENT '项目编号',
    name VARCHAR(100) COMMENT '项目名称',
    industry VARCHAR(36) COMMENT '所属行业',
    stage VARCHAR(36) COMMENT '进展阶段',
    investment DECIMAL(20, 6) COMMENT '总投资(万)',
    fund VARCHAR(36) COMMENT '资金分类',
    year BIGINT COMMENT '年度',
    company VARCHAR(100) COMMENT '工期建设单位',
    approval_no VARCHAR(36) COMMENT '审批文号',
    approval_office VARCHAR(100) COMMENT '审批机关',
    place VARCHAR(100) COMMENT '建设地点',
    region VARCHAR(100) COMMENT '所属地区',
    unit VARCHAR(100) COMMENT '主管单位',
    leader VARCHAR(50) COMMENT '项目负责人',
    phone VARCHAR(50) COMMENT '电话',
    deleted INT COMMENT '是否删除[0:未删除;1:已删除]',
    create_by VARCHAR(36) COMMENT '填报人',
    create_time DATETIME COMMENT '填报时间',
    update_by VARCHAR(36) COMMENT '修改人',
    update_time DATETIME COMMENT '修改时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
