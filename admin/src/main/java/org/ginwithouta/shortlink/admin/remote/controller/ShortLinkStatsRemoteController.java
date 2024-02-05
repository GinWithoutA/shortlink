package org.ginwithouta.shortlink.admin.remote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkGroupStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkGroupStatsRespDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;
import org.ginwithouta.shortlink.admin.remote.service.ShortLinkStatsRemoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 远程调用短链接监控控制层
 */
@RestController
@RequestMapping(value = "/api/short/link/admin/v1/stats/")
public class ShortLinkStatsRemoteController {

    private final ShortLinkStatsRemoteService linkStatsRemoteService = new ShortLinkStatsRemoteService() {};

    /**
     * 远程调用单个短链接详细监控数据访问
     */
    @GetMapping(value = "")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return linkStatsRemoteService.oneShortLinkStatistics(requestParam);
    }

    /**
     * 分组短链接详细监控数据访问
     */
    @GetMapping(value = "group")
    public Result<ShortLinkGroupStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return linkStatsRemoteService.groupShortLinkStatistics(requestParam);
    }

    /**
     * 远程调用单个短链接访问日志监控数据
     */
    @GetMapping(value = "access/record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return linkStatsRemoteService.shortLinkStatsAccessRecord(requestParam);
    }

    /**
     * 分组短链接访问日志监控数据
     */
    @GetMapping(value = "access/record/group")
    public Result<IPage<ShortLinkGroupStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return linkStatsRemoteService.shortLinkGroupStatsAccessRecord(requestParam);
    }
}
