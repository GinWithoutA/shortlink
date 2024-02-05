package org.ginwithouta.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 短链接监控之网络类型响应 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsNetworkRespDTO {
    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 网络类型
     */
    private String network;

    /**
     * 占比
     */
    private Double ratio;
}
