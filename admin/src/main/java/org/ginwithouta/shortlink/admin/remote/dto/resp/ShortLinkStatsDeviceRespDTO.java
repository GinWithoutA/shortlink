package org.ginwithouta.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 单个短链接监控之访问设备响应 DTO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsDeviceRespDTO {
    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 访问设备
     */
    private String device;

    /**
     * 占比
     */
    private Double ratio;
}
