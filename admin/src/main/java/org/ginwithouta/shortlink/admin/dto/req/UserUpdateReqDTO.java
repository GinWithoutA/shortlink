package org.ginwithouta.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Package : org.ginwithouta.shortlink.admin.dto.req
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周五
 * @Desc :
 */
@Data
public class UserUpdateReqDTO {

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
}
