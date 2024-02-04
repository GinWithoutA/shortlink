package org.ginwithouta.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 单个短链接指定时间内监控数据响应DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsDailyRespDTO {

    /**
     * 日期
     */
    private String date;

    /**
     * 独立用户访问量
     */
    private Integer uv;

    /**
     * 页面访问量
     */
    private Integer pv;

    /**
     * 独立 IP 数量
     */
    private Integer uip;
}
