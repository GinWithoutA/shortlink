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
    constraint idx_unique_full_short_url
        unique (full_short_url) comment '短链接唯一索引'
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
      constraint idx_unique_full_short_url
          unique (full_short_url) comment '短链接唯一索引'
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
        constraint idx_unique_full_short_url
            unique (full_short_url) comment '短链接唯一索引'
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
          constraint idx_unique_full_short_url
              unique (full_short_url) comment '短链接唯一索引'
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
            constraint idx_unique_full_short_url
                unique (full_short_url) comment '短链接唯一索引'
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
              constraint idx_unique_full_short_url
                  unique (full_short_url) comment '短链接唯一索引'
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
                constraint idx_unique_full_short_url
                    unique (full_short_url) comment '短链接唯一索引'
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
                  constraint idx_unique_full_short_url
                      unique (full_short_url) comment '短链接唯一索引'
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
                    constraint idx_unique_full_short_url
                        unique (full_short_url) comment '短链接唯一索引'
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
                      constraint idx_unique_full_short_url
                          unique (full_short_url) comment '短链接唯一索引'
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
                        constraint idx_unique_full_short_url
                            unique (full_short_url) comment '短链接唯一索引'
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
                          constraint idx_unique_full_short_url
                              unique (full_short_url) comment '短链接唯一索引'
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
                            constraint idx_unique_full_short_url
                                unique (full_short_url) comment '短链接唯一索引'
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
                              constraint idx_unique_full_short_url
                                  unique (full_short_url) comment '短链接唯一索引'
);
create table t_link_14(
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
    constraint idx_unique_full_short_url
        unique (full_short_url) comment '短链接唯一索引'
);
create table t_link_15(
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
  constraint idx_unique_full_short_url
      unique (full_short_url) comment '短链接唯一索引'
);