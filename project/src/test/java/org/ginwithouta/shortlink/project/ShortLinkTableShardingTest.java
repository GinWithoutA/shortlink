package org.ginwithouta.shortlink.project;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Package : org.nageoffer.shorlink.admin.test
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周五
 * @Desc :
 */
public class ShortLinkTableShardingTest {
    private static final String SQL = """
        create table t_link_%d
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
        );""";

    private static final String GOTO_SQL = """
            create table t_link_goto_%d
            (
                id             bigint auto_increment comment 'ID'
                    primary key,
                gid            varchar(32) default 'default' null comment '短链接分组标识',
                full_short_url varchar(128)                  null comment '完整短链接'
            )
                comment '短链接和分组标识路由表';
            """;

    private static final String STATS_TODAY = """
            create table t_link_stats_today_%d
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
            """;

    private static final String GTOUP = """
            create table t_group_%d
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
                constraint idx_unique_username_gid unique (gid, username) using BTREE
            )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            """;

    public static void main(String[] args) {
        // for (int i = 0; i < 16; ++i) {
        //     System.out.printf(GTOUP, i);
        // }
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16]; // 16 字节 = 128 位
        random.nextBytes(bytes);
        String password = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Generated password: " + password);
    }
}
