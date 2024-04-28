package org.ginwithouta.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupStatsRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 短链接监控接口层
 */
public interface ShortLinkStatsService extends IService<ShortLinkStatsDO> {
    /**
     * 获取指定时间内单个短链接的监控数据
     * @param requestParam 请求参数（完整短链接，分组标识，开始时间，结束时间）
     * @return  单个短链接指定时间内的监控数据
     */
    ShortLinkStatsRespDTO shortLinkStats(ShortLinkStatsReqDTO requestParam);

    /**
     * 访问单个短链接指定时间内访问记录监控数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam);

    /**
     * 访问分组短链接指定时间内访问记录监控数据
     */
    IPage<ShortLinkGroupStatsAccessRecordRespDTO> shortLinkGroupStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam);

    /**
     * 获取指定时间内分组短链接的监控数据
     * @param requestParam 请求参数（分组标识，开始时间，结束时间）
     * @return  分组短链接指定时间内的监控数据
     */
    ShortLinkGroupStatsRespDTO shortLinkGroupStats(ShortLinkGroupStatsReqDTO requestParam);
}
