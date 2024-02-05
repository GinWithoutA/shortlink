package org.ginwithouta.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 短链接分组监控日志访问请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShortLinkGroupStatsAccessRecordReqDTO extends Page {

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
