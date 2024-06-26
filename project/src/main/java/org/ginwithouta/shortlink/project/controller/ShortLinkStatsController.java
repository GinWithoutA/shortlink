package org.ginwithouta.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupStatsRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkStatsRespDTO;
import org.ginwithouta.shortlink.project.service.ShortLinkStatsService;
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
public class ShortLinkStatsController {

    private final ShortLinkStatsService shortLinkStatsService;

    /**
     * 单个短链接的详细监控数据
     */
    @GetMapping(value = "")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkStats(requestParam));
    }

    /**
     * 分组短链接详细监控数据访问
     */
    @GetMapping(value = "group")
    public Result<ShortLinkGroupStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkGroupStats(requestParam));
    }

    /**
     * 单个短链接访问日志监控数据
     */
    @GetMapping(value = "access/record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkStatsAccessRecord(requestParam));
    }

    /**
     * 分组短链接访问日志监控数据
     */
    @GetMapping(value = "access/record/group")
    public Result<IPage<ShortLinkGroupStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkGroupStatsAccessRecord(requestParam));
    }

}
