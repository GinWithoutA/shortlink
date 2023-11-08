package org.ginwithouta.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Package : org.ginwithouta.shortlink.admin.dao.entity
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 用户持久层实体
 */
@Data
@TableName("t_user")
public class UserDO {

    /**
     * ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private String deletionTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 删除标识：0：未删除：1：已删除
     */
    private String delFlag;

}
