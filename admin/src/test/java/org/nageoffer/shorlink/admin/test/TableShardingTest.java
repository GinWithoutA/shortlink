package org.nageoffer.shorlink.admin.test;

/**
 * @Package : org.nageoffer.shorlink.admin.test
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周五
 * @Desc :
 */
public class TableShardingTest {
    private static final String USER_SQL = """
            create table t_user_%d
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
                constraint idx_unique_username unique (username) comment 'username数据库唯一性兜底策略'
            );""";

    private static final String GROUP_SQL = """            
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
                constraint idx_unique_username_gid
                    unique (gid, username)
            );
            """;

    public static void main(String[] args) {
        for (int i = 0; i < 16; ++i) {
            System.out.printf(GROUP_SQL, i);
        }
    }
}
