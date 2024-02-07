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