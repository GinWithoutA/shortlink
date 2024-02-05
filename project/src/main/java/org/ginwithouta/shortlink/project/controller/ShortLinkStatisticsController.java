package org.ginwithouta.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkStatsRespDTO;
import org.ginwithouta.shortlink.project.service.ShortLinkStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 短链接监控控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/short/link/project/v1/stats/")
public class ShortLinkStatisticsController {

    private final ShortLinkStatisticsService shortLinkStatisticsService;

    /**
     * 单个短链接详细监控数据访问
     */
    @GetMapping(value = "")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatisticsService.oneShortLinkStatistics(requestParam));
    }

    /**
     * 单个短链接访问日志监控数据
     */
    @GetMapping(value = "access/record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatisticsService.shortLinkStatsAccessRecord(requestParam));
    }


}
