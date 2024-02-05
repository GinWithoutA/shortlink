package org.ginwithouta.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2023/12/3
 * 短链接高频访问 IP 监控响应参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsTopIpRespDTO {

    /**
     * 访问量
     */
    private String ip;

    /**
     * 访问 IP
     */
    private Integer cnt;
}
