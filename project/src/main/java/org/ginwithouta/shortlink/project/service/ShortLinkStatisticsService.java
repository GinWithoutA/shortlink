package org.ginwithouta.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 短链接监控接口层
 */
public interface ShortLinkStatisticsService extends IService<ShortLinkStatsDO> {
    /**
     * 获取指定时间内单个短链接的监控数据
     * @param requestParam 请求参数（完整短链接，分组标识，开始时间，结束时间）
     * @return  单个短链接指定时间内的监控数据
     */
    ShortLinkStatsRespDTO oneShortLinkStatistics(ShortLinkStatsReqDTO requestParam);
}
