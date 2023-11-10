package org.ginwithouta.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.ginwithouta.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;

/**
 * @Package : org.ginwithouta.shortlink.admin.dto.resp
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 用户返回参数响应
 */
@Data
public class UserRespDTO {

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
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
