package org.ginwithouta.shortlink.project.dto.biz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 根据用户列表查询用户类别 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UvTypeMapperDTO {
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
     * 启用状态 0：未启用 1：启用
     */
    private Integer enableStatus;
}
