package org.ginwithouta.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 单个短链接监控之访客类型响应 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsUvRespDTO {
    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 访客类型名称
     */
    private String uvType;

    /**
     * 占比
     */
    private Double ratio;
}
