create database shortlink;
use shortlink;
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
create table t_user_0
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_1
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_2
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_3
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_4
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_5
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_6
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_7
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_8
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_9
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_10
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_11
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_12
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_13
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_14
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_user_15
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256)         null comment '用户名',
    password      varchar(512)         null comment '密码',
    real_name     varchar(256)         null comment '真实姓名',
    phone         varchar(128)         null comment '手机号',
    mail          varchar(512)         null comment '邮箱',
    deletion_time bigint               null comment '注销时间戳',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
    del_flag      tinyint(1) default 0 null comment '删除标识：0：未删除：1：已删除',
    constraint idx_unique_username
        unique (username) comment 'username数据库唯一性兜底策略'
);
create table t_link_goto_0
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_1
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_2
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_3
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_4
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_5
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_6
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_7
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_8
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_9
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_10
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_11
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_12
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_13
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_14
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_goto_15
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '短链接分组标识',
    full_short_url varchar(128)                  null comment '完整短链接'
)
    comment '短链接和分组标识路由表';
create table t_link_0
(
    id              bigint auto_increment comment 'ID'
        primary key,
    domain          varchar(128)                   null comment '域名',
    short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
    full_short_url  varchar(128)                   null comment '完整短链接',
    origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
    click_num       int         default 0          null comment '点击量 默认是0',
    gid             varchar(32) default 'default'  not null comment '分组标识',
    favicon         varchar(256)                   null comment '网站图标',
    enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
    created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
    valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
    valid_date      datetime                       null comment '有效期',
    description     varchar(1024)                  null comment '短链接描述',
    total_pv        int         default 0          null comment '历史pv',
    total_uv        int         default 0          null comment '历史uv',
    total_uip       int         default 0          null comment '历史uip',
    create_time     datetime                       null comment '创建时间',
    update_time     datetime                       null comment '更新时间',
    del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
    del_time        bigint                         null comment '删除时间',
    constraint idx_unique_full_short_url
        unique (full_short_url, del_time) comment '短链接唯一索引'
);create table t_link_1
  (
      id              bigint auto_increment comment 'ID'
          primary key,
      domain          varchar(128)                   null comment '域名',
      short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
      full_short_url  varchar(128)                   null comment '完整短链接',
      origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
      click_num       int         default 0          null comment '点击量 默认是0',
      gid             varchar(32) default 'default'  not null comment '分组标识',
      favicon         varchar(256)                   null comment '网站图标',
      enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
      created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
      valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
      valid_date      datetime                       null comment '有效期',
      description     varchar(1024)                  null comment '短链接描述',
      total_pv        int         default 0          null comment '历史pv',
      total_uv        int         default 0          null comment '历史uv',
      total_uip       int         default 0          null comment '历史uip',
      create_time     datetime                       null comment '创建时间',
      update_time     datetime                       null comment '更新时间',
      del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
      del_time        bigint                         null comment '删除时间',
      constraint idx_unique_full_short_url
          unique (full_short_url, del_time) comment '短链接唯一索引'
  );create table t_link_2
    (
        id              bigint auto_increment comment 'ID'
            primary key,
        domain          varchar(128)                   null comment '域名',
        short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
        full_short_url  varchar(128)                   null comment '完整短链接',
        origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
        click_num       int         default 0          null comment '点击量 默认是0',
        gid             varchar(32) default 'default'  not null comment '分组标识',
        favicon         varchar(256)                   null comment '网站图标',
        enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
        created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
        valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
        valid_date      datetime                       null comment '有效期',
        description     varchar(1024)                  null comment '短链接描述',
        total_pv        int         default 0          null comment '历史pv',
        total_uv        int         default 0          null comment '历史uv',
        total_uip       int         default 0          null comment '历史uip',
        create_time     datetime                       null comment '创建时间',
        update_time     datetime                       null comment '更新时间',
        del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
        del_time        bigint                         null comment '删除时间',
        constraint idx_unique_full_short_url
            unique (full_short_url, del_time) comment '短链接唯一索引'
    );create table t_link_3
      (
          id              bigint auto_increment comment 'ID'
              primary key,
          domain          varchar(128)                   null comment '域名',
          short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
          full_short_url  varchar(128)                   null comment '完整短链接',
          origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
          click_num       int         default 0          null comment '点击量 默认是0',
          gid             varchar(32) default 'default'  not null comment '分组标识',
          favicon         varchar(256)                   null comment '网站图标',
          enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
          created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
          valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
          valid_date      datetime                       null comment '有效期',
          description     varchar(1024)                  null comment '短链接描述',
          total_pv        int         default 0          null comment '历史pv',
          total_uv        int         default 0          null comment '历史uv',
          total_uip       int         default 0          null comment '历史uip',
          create_time     datetime                       null comment '创建时间',
          update_time     datetime                       null comment '更新时间',
          del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
          del_time        bigint                         null comment '删除时间',
          constraint idx_unique_full_short_url
              unique (full_short_url, del_time) comment '短链接唯一索引'
      );create table t_link_4
        (
            id              bigint auto_increment comment 'ID'
                primary key,
            domain          varchar(128)                   null comment '域名',
            short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
            full_short_url  varchar(128)                   null comment '完整短链接',
            origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
            click_num       int         default 0          null comment '点击量 默认是0',
            gid             varchar(32) default 'default'  not null comment '分组标识',
            favicon         varchar(256)                   null comment '网站图标',
            enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
            created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
            valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
            valid_date      datetime                       null comment '有效期',
            description     varchar(1024)                  null comment '短链接描述',
            total_pv        int         default 0          null comment '历史pv',
            total_uv        int         default 0          null comment '历史uv',
            total_uip       int         default 0          null comment '历史uip',
            create_time     datetime                       null comment '创建时间',
            update_time     datetime                       null comment '更新时间',
            del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
            del_time        bigint                         null comment '删除时间',
            constraint idx_unique_full_short_url
                unique (full_short_url, del_time) comment '短链接唯一索引'
        );create table t_link_5
          (
              id              bigint auto_increment comment 'ID'
                  primary key,
              domain          varchar(128)                   null comment '域名',
              short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
              full_short_url  varchar(128)                   null comment '完整短链接',
              origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
              click_num       int         default 0          null comment '点击量 默认是0',
              gid             varchar(32) default 'default'  not null comment '分组标识',
              favicon         varchar(256)                   null comment '网站图标',
              enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
              created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
              valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
              valid_date      datetime                       null comment '有效期',
              description     varchar(1024)                  null comment '短链接描述',
              total_pv        int         default 0          null comment '历史pv',
              total_uv        int         default 0          null comment '历史uv',
              total_uip       int         default 0          null comment '历史uip',
              create_time     datetime                       null comment '创建时间',
              update_time     datetime                       null comment '更新时间',
              del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
              del_time        bigint                         null comment '删除时间',
              constraint idx_unique_full_short_url
                  unique (full_short_url, del_time) comment '短链接唯一索引'
          );create table t_link_6
            (
                id              bigint auto_increment comment 'ID'
                    primary key,
                domain          varchar(128)                   null comment '域名',
                short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                full_short_url  varchar(128)                   null comment '完整短链接',
                origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                click_num       int         default 0          null comment '点击量 默认是0',
                gid             varchar(32) default 'default'  not null comment '分组标识',
                favicon         varchar(256)                   null comment '网站图标',
                enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                valid_date      datetime                       null comment '有效期',
                description     varchar(1024)                  null comment '短链接描述',
                total_pv        int         default 0          null comment '历史pv',
                total_uv        int         default 0          null comment '历史uv',
                total_uip       int         default 0          null comment '历史uip',
                create_time     datetime                       null comment '创建时间',
                update_time     datetime                       null comment '更新时间',
                del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                del_time        bigint                         null comment '删除时间',
                constraint idx_unique_full_short_url
                    unique (full_short_url, del_time) comment '短链接唯一索引'
            );create table t_link_7
              (
                  id              bigint auto_increment comment 'ID'
                      primary key,
                  domain          varchar(128)                   null comment '域名',
                  short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                  full_short_url  varchar(128)                   null comment '完整短链接',
                  origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                  click_num       int         default 0          null comment '点击量 默认是0',
                  gid             varchar(32) default 'default'  not null comment '分组标识',
                  favicon         varchar(256)                   null comment '网站图标',
                  enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                  created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                  valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                  valid_date      datetime                       null comment '有效期',
                  description     varchar(1024)                  null comment '短链接描述',
                  total_pv        int         default 0          null comment '历史pv',
                  total_uv        int         default 0          null comment '历史uv',
                  total_uip       int         default 0          null comment '历史uip',
                  create_time     datetime                       null comment '创建时间',
                  update_time     datetime                       null comment '更新时间',
                  del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                  del_time        bigint                         null comment '删除时间',
                  constraint idx_unique_full_short_url
                      unique (full_short_url, del_time) comment '短链接唯一索引'
              );create table t_link_8
                (
                    id              bigint auto_increment comment 'ID'
                        primary key,
                    domain          varchar(128)                   null comment '域名',
                    short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                    full_short_url  varchar(128)                   null comment '完整短链接',
                    origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                    click_num       int         default 0          null comment '点击量 默认是0',
                    gid             varchar(32) default 'default'  not null comment '分组标识',
                    favicon         varchar(256)                   null comment '网站图标',
                    enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                    created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                    valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                    valid_date      datetime                       null comment '有效期',
                    description     varchar(1024)                  null comment '短链接描述',
                    total_pv        int         default 0          null comment '历史pv',
                    total_uv        int         default 0          null comment '历史uv',
                    total_uip       int         default 0          null comment '历史uip',
                    create_time     datetime                       null comment '创建时间',
                    update_time     datetime                       null comment '更新时间',
                    del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                    del_time        bigint                         null comment '删除时间',
                    constraint idx_unique_full_short_url
                        unique (full_short_url, del_time) comment '短链接唯一索引'
                );create table t_link_9
                  (
                      id              bigint auto_increment comment 'ID'
                          primary key,
                      domain          varchar(128)                   null comment '域名',
                      short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                      full_short_url  varchar(128)                   null comment '完整短链接',
                      origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                      click_num       int         default 0          null comment '点击量 默认是0',
                      gid             varchar(32) default 'default'  not null comment '分组标识',
                      favicon         varchar(256)                   null comment '网站图标',
                      enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                      created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                      valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                      valid_date      datetime                       null comment '有效期',
                      description     varchar(1024)                  null comment '短链接描述',
                      total_pv        int         default 0          null comment '历史pv',
                      total_uv        int         default 0          null comment '历史uv',
                      total_uip       int         default 0          null comment '历史uip',
                      create_time     datetime                       null comment '创建时间',
                      update_time     datetime                       null comment '更新时间',
                      del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                      del_time        bigint                         null comment '删除时间',
                      constraint idx_unique_full_short_url
                          unique (full_short_url, del_time) comment '短链接唯一索引'
                  );create table t_link_10
                    (
                        id              bigint auto_increment comment 'ID'
                            primary key,
                        domain          varchar(128)                   null comment '域名',
                        short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                        full_short_url  varchar(128)                   null comment '完整短链接',
                        origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                        click_num       int         default 0          null comment '点击量 默认是0',
                        gid             varchar(32) default 'default'  not null comment '分组标识',
                        favicon         varchar(256)                   null comment '网站图标',
                        enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                        created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                        valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                        valid_date      datetime                       null comment '有效期',
                        description     varchar(1024)                  null comment '短链接描述',
                        total_pv        int         default 0          null comment '历史pv',
                        total_uv        int         default 0          null comment '历史uv',
                        total_uip       int         default 0          null comment '历史uip',
                        create_time     datetime                       null comment '创建时间',
                        update_time     datetime                       null comment '更新时间',
                        del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                        del_time        bigint                         null comment '删除时间',
                        constraint idx_unique_full_short_url
                            unique (full_short_url, del_time) comment '短链接唯一索引'
                    );create table t_link_11
                      (
                          id              bigint auto_increment comment 'ID'
                              primary key,
                          domain          varchar(128)                   null comment '域名',
                          short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                          full_short_url  varchar(128)                   null comment '完整短链接',
                          origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                          click_num       int         default 0          null comment '点击量 默认是0',
                          gid             varchar(32) default 'default'  not null comment '分组标识',
                          favicon         varchar(256)                   null comment '网站图标',
                          enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                          created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                          valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                          valid_date      datetime                       null comment '有效期',
                          description     varchar(1024)                  null comment '短链接描述',
                          total_pv        int         default 0          null comment '历史pv',
                          total_uv        int         default 0          null comment '历史uv',
                          total_uip       int         default 0          null comment '历史uip',
                          create_time     datetime                       null comment '创建时间',
                          update_time     datetime                       null comment '更新时间',
                          del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                          del_time        bigint                         null comment '删除时间',
                          constraint idx_unique_full_short_url
                              unique (full_short_url, del_time) comment '短链接唯一索引'
                      );create table t_link_12
                        (
                            id              bigint auto_increment comment 'ID'
                                primary key,
                            domain          varchar(128)                   null comment '域名',
                            short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                            full_short_url  varchar(128)                   null comment '完整短链接',
                            origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                            click_num       int         default 0          null comment '点击量 默认是0',
                            gid             varchar(32) default 'default'  not null comment '分组标识',
                            favicon         varchar(256)                   null comment '网站图标',
                            enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                            created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                            valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                            valid_date      datetime                       null comment '有效期',
                            description     varchar(1024)                  null comment '短链接描述',
                            total_pv        int         default 0          null comment '历史pv',
                            total_uv        int         default 0          null comment '历史uv',
                            total_uip       int         default 0          null comment '历史uip',
                            create_time     datetime                       null comment '创建时间',
                            update_time     datetime                       null comment '更新时间',
                            del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                            del_time        bigint                         null comment '删除时间',
                            constraint idx_unique_full_short_url
                                unique (full_short_url, del_time) comment '短链接唯一索引'
                        );create table t_link_13
                          (
                              id              bigint auto_increment comment 'ID'
                                  primary key,
                              domain          varchar(128)                   null comment '域名',
                              short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                              full_short_url  varchar(128)                   null comment '完整短链接',
                              origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                              click_num       int         default 0          null comment '点击量 默认是0',
                              gid             varchar(32) default 'default'  not null comment '分组标识',
                              favicon         varchar(256)                   null comment '网站图标',
                              enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                              created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                              valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                              valid_date      datetime                       null comment '有效期',
                              description     varchar(1024)                  null comment '短链接描述',
                              total_pv        int         default 0          null comment '历史pv',
                              total_uv        int         default 0          null comment '历史uv',
                              total_uip       int         default 0          null comment '历史uip',
                              create_time     datetime                       null comment '创建时间',
                              update_time     datetime                       null comment '更新时间',
                              del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                              del_time        bigint                         null comment '删除时间',
                              constraint idx_unique_full_short_url
                                  unique (full_short_url, del_time) comment '短链接唯一索引'
                          );create table t_link_14
                            (
                                id              bigint auto_increment comment 'ID'
                                    primary key,
                                domain          varchar(128)                   null comment '域名',
                                short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                                full_short_url  varchar(128)                   null comment '完整短链接',
                                origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                                click_num       int         default 0          null comment '点击量 默认是0',
                                gid             varchar(32) default 'default'  not null comment '分组标识',
                                favicon         varchar(256)                   null comment '网站图标',
                                enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                                created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                                valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                                valid_date      datetime                       null comment '有效期',
                                description     varchar(1024)                  null comment '短链接描述',
                                total_pv        int         default 0          null comment '历史pv',
                                total_uv        int         default 0          null comment '历史uv',
                                total_uip       int         default 0          null comment '历史uip',
                                create_time     datetime                       null comment '创建时间',
                                update_time     datetime                       null comment '更新时间',
                                del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                                del_time        bigint                         null comment '删除时间',
                                constraint idx_unique_full_short_url
                                    unique (full_short_url, del_time) comment '短链接唯一索引'
                            );create table t_link_15
                              (
                                  id              bigint auto_increment comment 'ID'
                                      primary key,
                                  domain          varchar(128)                   null comment '域名',
                                  short_uri       varchar(8) collate utf8mb4_bin null comment '短链接',
                                  full_short_url  varchar(128)                   null comment '完整短链接',
                                  origin_url      varchar(1024)                  null comment '原始链接（短链接跳转的目标链接）',
                                  click_num       int         default 0          null comment '点击量 默认是0',
                                  gid             varchar(32) default 'default'  not null comment '分组标识',
                                  favicon         varchar(256)                   null comment '网站图标',
                                  enable          tinyint(1)  default 1          null comment '短链接启用标识 0 未启用 1 已启用 默认启用',
                                  created_type    tinyint(1)                     null comment '创建类型 0 接口创建 1 平台创建',
                                  valid_date_type tinyint(1)                     null comment '有效期类型 0 永久有效 1 临时有效',
                                  valid_date      datetime                       null comment '有效期',
                                  description     varchar(1024)                  null comment '短链接描述',
                                  total_pv        int         default 0          null comment '历史pv',
                                  total_uv        int         default 0          null comment '历史uv',
                                  total_uip       int         default 0          null comment '历史uip',
                                  create_time     datetime                       null comment '创建时间',
                                  update_time     datetime                       null comment '更新时间',
                                  del_flag        tinyint(1)  default 0          null comment '逻辑删除标识 0 未删除 1 已删除',
                                  del_time        bigint                         null comment '删除时间',
                                  constraint idx_unique_full_short_url
                                      unique (full_short_url, del_time) comment '短链接唯一索引'
                              );
create table t_access_logs
(
    id             bigint auto_increment comment 'ID'
        primary key,
    full_short_url varchar(128)                  null comment '完整短链接',
    gid            varchar(32) default 'default' null comment '分组标识',
    date           date                          null comment '日期',
    user           varchar(64)                   null comment '用户信息',
    browser        varchar(64)                   null comment '浏览器',
    device         varchar(64)                   null comment '访问设备',
    network        varchar(64)                   null comment '访问网络',
    locale         varchar(512)                  null comment '访问地区',
    os             varchar(64)                   null comment '操作系统',
    ip             varchar(64)                   null comment 'IP',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '更新时间',
    del_flag       tinyint(1)  default 0         null comment '删除标识'
)
    comment '短链接访问日志表';
create table t_browser_statistics
(
    id             bigint auto_increment comment 'ID'
        primary key,
    full_short_url varchar(128)                  null comment '完整短链接',
    gid            varchar(32) default 'default' null comment '分组标识',
    date           date                          null comment '日期',
    cnt            int                           null comment '访问量',
    browser        varchar(64)                   null comment '浏览器',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '更新时间',
    del_flag       tinyint(1)  default 0         null comment '删除标识',
    constraint idx_unique_locale_statistics
        unique (full_short_url, gid, date, browser) comment '统计条目日期唯一索引'
)
    comment '短链接访问浏览器统计表';
create table t_device_statistics
(
    id             bigint auto_increment comment 'ID'
        primary key,
    full_short_url varchar(128)                  null comment '完整短链接',
    gid            varchar(32) default 'default' null comment '分组标识',
    date           date                          null comment '日期',
    cnt            int                           null comment '访问量',
    device         varchar(64)                   null comment '设备',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '更新时间',
    del_flag       tinyint(1)  default 0         null comment '删除标识',
    constraint idx_unique_device_statistics
        unique (full_short_url, gid, date, device) comment '统计条目日期唯一索引'
)
    comment '短链接访问设备统计表';
create table t_locale_statistics
(
    id             bigint auto_increment comment 'ID'
        primary key,
    full_short_url varchar(128)                  null comment '完整短链接',
    gid            varchar(32) default 'default' null comment '分组标识',
    date           date                          null comment '日期',
    cnt            int                           null comment '访问量',
    province       varchar(64)                   null comment ' 省份名称',
    city           varchar(64)                   null comment '地级市名称',
    adcode         varchar(64)                   null comment '城市编码',
    country        varchar(64)                   null comment '国家标识',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '更新时间',
    del_flag       tinyint(1)  default 0         null comment '删除标识',
    constraint idx_unique_locale_statistics
        unique (full_short_url, gid, date, adcode, province) comment '统计条目日期唯一索引'
)
    comment '短链接访问统计表';
create table t_network_statistics
(
    id             bigint auto_increment comment 'ID'
        primary key,
    full_short_url varchar(128)                  null comment '完整短链接',
    gid            varchar(32) default 'default' null comment '分组标识',
    date           date                          null comment '日期',
    cnt            int                           null comment '访问量',
    network        varchar(64)                   null comment '网络',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '更新时间',
    del_flag       tinyint(1)  default 0         null comment '删除标识',
    constraint idx_unique_network_statistics
        unique (full_short_url, gid, date, network) comment '统计条目日期唯一索引'
)
    comment '短链接访问网络统计表';
create table t_os_statistics
(
    id             bigint auto_increment comment 'ID'
        primary key,
    full_short_url varchar(128)                  null comment '完整短链接',
    gid            varchar(32) default 'default' null comment '分组标识',
    date           date                          null comment '日期',
    cnt            int                           null comment '访问量',
    os             varchar(64)                   null comment ' 操作系统名称',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '更新时间',
    del_flag       tinyint(1)  default 0         null comment '删除标识',
    constraint idx_unique_locale_statistics
        unique (full_short_url, gid, date, os) comment '统计条目日期唯一索引'
)
    comment '短链接访问操作系统统计表';
create table t_statistics
(
    id             bigint auto_increment comment 'ID'
        primary key,
    full_short_url varchar(128)                  null comment '完整短链接',
    gid            varchar(32) default 'default' null comment '分组标识',
    date           date                          null comment '日期',
    pv             int         default 0         null comment '页面访问量（Page View）',
    uv             int         default 0         null comment '独立访客数量（User View）',
    uip            int         default 0         null comment '独立IP数（User IP）',
    hour           int                           null comment '小时',
    weekday        int                           null comment '星期',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '更新时间',
    del_flag       tinyint(1)  default 0         null comment '删除标识',
    constraint idx_unique_statistics
        unique (full_short_url, gid, weekday, hour)
)
    comment '短链接访问统计表';
create table t_link_stats_today_0
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_1
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_2
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_3
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_4
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_5
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_6
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_7
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_8
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_9
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_10
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_11
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_12
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_13
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_14
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;
create table t_link_stats_today_15
(
    id             bigint auto_increment comment 'ID'
        primary key,
    gid            varchar(32) default 'default' null comment '分组标识',
    full_short_url varchar(128)                  null comment '短链接',
    date           date                          null comment '日期',
    today_pv       int         default 0         null comment '今日PV',
    today_uv       int         default 0         null comment '今日UV',
    today_uip      int         default 0         null comment '今日IP数',
    create_time    datetime                      null comment '创建时间',
    update_time    datetime                      null comment '修改时间',
    del_flag       tinyint(1)                    null comment '删除标识 0：未删除 1：已删除',
    constraint `idx_unique_full-short-url`
        unique (full_short_url, gid, date)
)
    charset = utf8mb4;

