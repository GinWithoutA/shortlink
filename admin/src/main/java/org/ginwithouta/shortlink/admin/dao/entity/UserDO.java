package org.ginwithouta.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ginwithouta.shortlink.admin.common.database.BaseDO;

/**
 * @Package : org.ginwithouta.shortlink.admin.dao.entity
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 用户持久层实体
 */
@Data
@TableName("t_user")
@EqualsAndHashCode(callSuper = true)
public class UserDO extends BaseDO {

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

}
