package org.ginwithouta.shortlink.project.dto.req;

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
public class UvTypeGroupMapperDTO {
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
