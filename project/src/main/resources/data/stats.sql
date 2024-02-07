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