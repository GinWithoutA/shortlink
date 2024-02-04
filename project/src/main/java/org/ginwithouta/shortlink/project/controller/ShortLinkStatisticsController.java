package org.ginwithouta.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;
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
@RequestMapping(value = "/api/short/link/project/v1/")
public class ShortLinkStatisticsController {

    private final ShortLinkStatisticsService shortLinkStatisticsService;

    @GetMapping(value = "stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStatistics(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatisticsService.oneShortLinkStatistics(requestParam));
    }

}
