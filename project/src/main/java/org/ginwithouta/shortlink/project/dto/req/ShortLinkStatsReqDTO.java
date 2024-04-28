package org.ginwithouta.shortlink.project.dto.req;

import lombok.Data;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 单个短链接指定时间内监控数据请求DTO
 */
@Data
public class ShortLinkStatsReqDTO {

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 监控数据的开始日期
     */
    private String startDate;

    /**
     * 监控数据的结束日期
     */
    private String endDate;

    /**
     * 启用标识 1: 启用 2：未启用
     */
    private Integer enableStatus;
}
