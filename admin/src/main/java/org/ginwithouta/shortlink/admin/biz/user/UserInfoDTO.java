package org.ginwithouta.shortlink.admin.biz.user;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package : org.ginwithouta.shortlink.admin.biz.user
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 用户信息实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    /**
     * 用户ID
     */
    @JSONField(name = "id")
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实名
     */
    private String realName;

    /**
     * 用户Token
     */
    private String token;

}
