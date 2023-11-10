package org.ginwithouta.shortlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package : org.ginwithouta.shortlink.admin.dto.resp
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周五
 * @Desc : 用户登录接口返回响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRespDTO {

    /**
     * 用户Token
     */
    private String token;
}
