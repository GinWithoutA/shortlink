package org.ginwithouta.shortlink.admin.remote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.remote.dto.req.*;
import org.ginwithouta.shortlink.admin.remote.dto.resp.*;
import org.ginwithouta.shortlink.admin.remote.service.ShortLinkRemoteService;
import org.ginwithouta.shortlink.admin.remote.service.ShortLinkStatsRemoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package : org.ginwithouta.shortlink.admin.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 短链接后管控制层
 */
@RestController
@RequestMapping(value = "/api/short/link/admin/v1/")
public class ShortLinkRemoteController {

    /**
     * TODO 后续重构为 Spring Cloud
     * 远程调用接口
     */
    private final ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {};

    /**
     * 远程调用统计接口
     */
    private final ShortLinkStatsRemoteService linkStatsRemoteService = new ShortLinkStatsRemoteService() {};

    /**
     * 远程调用创建短链接
     */
    @PostMapping(value = "create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShorLink(requestParam);
    }

    /**
     * 远程调用批量创建短链接
     */
    @PostMapping(value = "create/batch")
    public Result<ShortLinkCreateBatchRespDTO> createBatchShortLink(@RequestBody ShortLinkCreateBatchReqDTO requestParam) {
        return shortLinkRemoteService.createBatchShortLink(requestParam);
    }

    /**
     * 远程调用修改短链接
     */
    @PostMapping(value = "update")
    public Result<Void> update(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * HTTP请求远程调用分页查询短链接
     */
    @GetMapping(value = "page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLinkList(requestParam);
    }

    /**
     * 短链接分组数量查询
     */
    @GetMapping(value = "count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParams) {
        return shortLinkRemoteService.listGroupShortLinkCount(requestParams);
    }

    /**
     * 远程调用根据短链接获取网站标题
     */
    @GetMapping(value = "title")
    public Result<String> getTitleByUrl(@RequestParam(name = "url") String url) {
        return shortLinkRemoteService.getTitleByUrl(url);
    }

    /**
     * 远程调用单个短链接详细监控数据访问
     */
    @GetMapping(value = "stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return linkStatsRemoteService.oneShortLinkStatistics(requestParam);
    }

    /**
     * 分组短链接详细监控数据访问
     */
    @GetMapping(value = "stats/group")
    public Result<ShortLinkGroupStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return linkStatsRemoteService.groupShortLinkStatistics(requestParam);
    }

    /**
     * 远程调用单个短链接访问日志监控数据
     */
    @GetMapping(value = "stats/access/record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return linkStatsRemoteService.shortLinkStatsAccessRecord(requestParam);
    }

    /**
     * 分组短链接访问日志监控数据
     */
    @GetMapping(value = "stats/access/record/group")
    public Result<IPage<ShortLinkGroupStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return linkStatsRemoteService.shortLinkGroupStatsAccessRecord(requestParam);
    }

}
