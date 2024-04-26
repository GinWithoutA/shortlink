# 创建数据库，同时创建一个新用户，这个数据库的所有修改都由当前用户完成
CREATE DATABASE IF NOT EXISTS `shortlink` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE USER 'link'@'%' IDENTIFIED BY '你的密码';
GRANT ALL PRIVILEGES ON shortlink.* TO 'link'@'%';
FLUSH PRIVILEGES;

# 可有可无
INSERT INTO `t_group_15` (`id`, `gid`, `name`, `username`, `sort_order`, `create_time`, `update_time`, `del_flag`)
VALUES (1752265619253805057, 'tSUBMP', '默认分组', 'admin', 0, '2024-01-31 21:00:00', '2024-01-31 21:00:00', 0);


INSERT INTO `t_user_15` (`id`, `username`, `password`, `real_name`, `phone`, `mail`, `deletion_time`, `create_time`,
                         `update_time`, `del_flag`)
VALUES (1752265616481370113, 'admin', 'admin123456', 'admin', 'yKZz0xLyjNb9LSCOCfJD4w==', '02/9oF/nWTBK0cM8UPtCOw==',
        NULL, '2024-01-31 21:00:00', '2024-01-31 21:00:00', 0);