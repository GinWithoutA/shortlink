create schema link collate utf8mb4_general_ci;
use link;

create table t_group_0
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_1
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_2
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_3
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_4
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_5
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_6
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_7
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_8
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_9
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_10
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_11
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_12
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_13
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_14
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);
create table t_group_15
(
    id          bigint auto_increment comment 'ID'
        primary key,
    gid         varchar(32)          null comment '短链接分组标识',
    name        varchar(64)          null comment '短链接名称',
    username    varchar(256)         null comment '创建分组的创建人',
    sort_order  int                  null comment '分组排序',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标识位： 0 未删除； 1 已删除',
    constraint idx_unique_username_gid
        unique (gid, username)
);