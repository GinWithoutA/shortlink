package org.ginwithouta.shortlink.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/4/24
 * 网关错误信息返回实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayErrorResult {
    /**
     * HTTP 状态码
     */
    private Integer status;

    /**
     * 返回信息
     */
    private String message;
}
