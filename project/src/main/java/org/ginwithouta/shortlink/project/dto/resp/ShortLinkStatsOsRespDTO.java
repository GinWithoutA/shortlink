package org.ginwithouta.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 短链接监控之操作系统响应 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsOsRespDTO {
    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 操作系统名称
     */
    private String os;

    /**
     * 占比
     */
    private Double ratio;
}
