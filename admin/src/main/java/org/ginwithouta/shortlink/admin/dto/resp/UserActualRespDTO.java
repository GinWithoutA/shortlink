package org.ginwithouta.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Package : org.ginwithouta.shortlink.admin.dto.resp
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
@Data
public class UserActualRespDTO {
    /**
     * ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

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
