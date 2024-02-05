package org.ginwithouta.shortlink.project.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 分组短链接监控数据查询请求 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkGroupStatsReqDTO {
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
}
