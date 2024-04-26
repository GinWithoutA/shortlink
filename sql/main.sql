CREATE TABLE `t_group_0`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_1`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_2`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_3`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_4`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_5`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_6`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_7`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_8`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_9`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_10`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_11`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_12`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_13`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_14`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_group_15`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`         varchar(32)  DEFAULT NULL COMMENT '分组标识',
    `name`        varchar(64)  DEFAULT NULL COMMENT '分组名称',
    `username`    varchar(256) DEFAULT NULL COMMENT '创建分组用户名',
    `sort_order`  int(3)       DEFAULT NULL COMMENT '分组排序',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time`   datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time`   datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_1`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time`   datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time`   datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_2`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_3`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_4`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_5`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_6`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_7`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_8`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_9`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_10`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_11`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_12`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_13`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_14`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';

CREATE TABLE `t_user_15`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(256) DEFAULT NULL COMMENT '用户名',
    `password`      varchar(512) DEFAULT NULL COMMENT '密码',
    `real_name`     varchar(256) DEFAULT NULL COMMENT '真实姓名',
    `phone`         varchar(128) DEFAULT NULL COMMENT '手机号',
    `mail`          varchar(512) DEFAULT NULL COMMENT '邮箱',
    `deletion_time` bigint(20)   DEFAULT NULL COMMENT '注销时间戳',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`      tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1716344307570487299 DEFAULT CHARSET=utf8mb4 COMMENT 'username数据库唯一性兜底策略';


CREATE TABLE `t_link_goto_0`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_1`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_2`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_3`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_4`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_5`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_6`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_7`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_8`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_9`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_10`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_11`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_12`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_13`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_14`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';

CREATE TABLE `t_link_goto_15`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `gid`            varchar(32)  DEFAULT 'default' COMMENT '分组标识',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接和分组标识路由表';


CREATE TABLE `t_link_0`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_1`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_2`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_3`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`          tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_4`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_5`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_6`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_7`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_8`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_9`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_10`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_11`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_12`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_13`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_14`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_15`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',
    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',
    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',
    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接（短链接跳转的目标链接）',
    `click_num`       int(11)                                        DEFAULT '0' COMMENT '点击量 默认是0',
    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',
    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',
    `enable_status`   tinyint(1)                                     DEFAULT '1' COMMENT '短链接启用标识 0 未启用 1 已启用 默认启用',
    `created_type`    tinyint(1)                                     DEFAULT NULL COMMENT '创建类型 0 接口创建 1 平台创建',
    `valid_date_type` tinyint(1)                                     DEFAULT NULL COMMENT '有效期类型 0 永久有效 1 临时有效',
    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',
    `description`     varchar(1024)                                  DEFAULT NULL COMMENT '短链接描述',
    `total_pv`        int(11)                                        DEFAULT '0' COMMENT '历史pv',
    `total_uv`        int(11)                                        DEFAULT '0' COMMENT '历史uv',
    `total_uip`       int(11)                                        DEFAULT '0' COMMENT '历史uip',
    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                       DEFAULT NULL COMMENT '更新时间',
    `del_flag`        tinyint(1)                                     DEFAULT '0' COMMENT '逻辑删除标识 0 未删除 1 已删除',
    `del_time`        bigint(20)                                     DEFAULT '0' COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE `idx_unique_full-short-url` (`full_short_url`, `del_time`) USING BTREE COMMENT '短链接唯一索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_access_logs`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    `user`           varchar(64)  DEFAULT NULL COMMENT '用户信息',
    `ip`             varchar(64)  DEFAULT NULL COMMENT 'IP',
    `browser`        varchar(64)  DEFAULT NULL COMMENT '浏览器',
    `os`             varchar(64)  DEFAULT NULL COMMENT '操作系统',
    `network`        varchar(64)  DEFAULT NULL COMMENT '访问网络',
    `device`         varchar(64)  DEFAULT NULL COMMENT '访问设备',
    `locale`         varchar(256) DEFAULT NULL COMMENT '地区',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY              `idx_full_short_url` (`full_short_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_link_browser_stats`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    `date`           date         DEFAULT NULL COMMENT '日期',
    `cnt`            int(11)      DEFAULT '0' COMMENT '访问量',
    `browser`        varchar(64)  DEFAULT NULL COMMENT '浏览器',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除' COMMENT '统计条目日期唯一索引',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_browser_stats` (`full_short_url`,`date`,`browser`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接访问浏览器统计表';

CREATE TABLE `t_link_device_stats`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    `date`           date         DEFAULT NULL COMMENT '日期',
    `cnt`            int(11)      DEFAULT '0' COMMENT '访问量',
    `device`         varchar(64)  DEFAULT NULL COMMENT '访问设备',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_browser_stats` (`full_short_url`,`date`,`device`) USING BTREE COMMENT '统计条目日期唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接访问设备统计表';

CREATE TABLE `t_link_locale_stats`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    `date`           date         DEFAULT NULL COMMENT '日期',
    `cnt`            int(11) DEFAULT '0' COMMENT '访问量',
    `province`       varchar(64)  DEFAULT NULL COMMENT '省份名称',
    `city`           varchar(64)  DEFAULT NULL COMMENT '市名称',
    `adcode`         varchar(64)  DEFAULT NULL COMMENT '城市编码',
    `country`        varchar(64)  DEFAULT NULL COMMENT '国家标识',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1) DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_locale_stats` (`full_short_url`,`date`,`adcode`,`province`) USING BTREE COMMENT '统计条目日期唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接地区访问统计表';

CREATE TABLE `t_link_network_stats`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    `date`           date         DEFAULT NULL COMMENT '日期',
    `cnt`            int(11)      DEFAULT '0' COMMENT '访问量',
    `network`        varchar(64)  DEFAULT NULL COMMENT '访问网络',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1)   DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_browser_stats` (`full_short_url`,`date`,`network`) USING BTREE COMMENT '统计条目日期唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '短链接访问网络统计表';

CREATE TABLE `t_link_os_stats`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    `date`           date         DEFAULT NULL COMMENT '日期',
    `cnt`            int(11) DEFAULT '0' COMMENT '访问量',
    `os`             varchar(64)  DEFAULT NULL COMMENT '操作系统',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1) DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_os_stats` (`full_short_url`,`date`,`os`) USING BTREE COMMENT '统计条目日期唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '统计条目日期唯一索引';

CREATE TABLE `t_link_stats`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '完整短链接',
    `date`           date         DEFAULT NULL COMMENT '日期',
    `pv`             int(11) DEFAULT '0' COMMENT '访问量',
    `uv`             int(11) DEFAULT '0' COMMENT '独立访客数',
    `uip`            int(11) DEFAULT '0' COMMENT '独立IP数',
    `hour`           int(3) DEFAULT NULL COMMENT '小时',
    `weekday`        int(3) DEFAULT NULL COMMENT '星期',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1) DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_access_stats` (`full_short_url`,`date`,`hour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接访问监控表';

CREATE TABLE `t_link_stats_today`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `full_short_url` varchar(128) DEFAULT NULL COMMENT '短链接',
    `date`           date         DEFAULT NULL COMMENT '日期',
    `today_pv`       int(11) DEFAULT '0' COMMENT '今日PV',
    `today_uv`       int(11) DEFAULT '0' COMMENT '今日UV',
    `today_uip`      int(11) DEFAULT '0' COMMENT '今日IP数',
    `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
    `update_time` datetime     DEFAULT NOW() COMMENT '修改时间',
    `del_flag`       tinyint(1) DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_unique_today_stats` (`full_short_url`,`date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
