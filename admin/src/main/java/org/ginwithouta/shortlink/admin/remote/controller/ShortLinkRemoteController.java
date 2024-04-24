package org.ginwithouta.shortlink.admin.remote.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.remote.dto.req.*;
import org.ginwithouta.shortlink.admin.remote.dto.resp.*;
import org.ginwithouta.shortlink.admin.remote.service.ShortLinkFeignRemoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package : org.ginwithouta.shortlink.admin.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 短链接后管控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/short/link/admin/v1/")
public class ShortLinkRemoteController {

    /**
     * 重构为 Spring Cloud 远程调用接口
     */
    private final ShortLinkFeignRemoteService shortLinkFeignRemoteService;

    /**
     * 远程调用创建短链接
     */
    @PostMapping(value = "create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkFeignRemoteService.createShorLink(requestParam);
    }

    /**
     * 远程调用批量创建短链接
     */
    @PostMapping(value = "create/batch")
    public Result<ShortLinkCreateBatchRespDTO> createBatchShortLink(@RequestBody ShortLinkCreateBatchReqDTO requestParam) {
        return shortLinkFeignRemoteService.createBatchShortLink(requestParam);
    }

    /**
     * 远程调用修改短链接
     */
    @PostMapping(value = "update")
    public Result<Void> update(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkFeignRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * HTTP请求远程调用分页查询短链接
     */
    @GetMapping(value = "page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        /*
         * 这里需要使用 Page 充当返回类型，而不是 IPage，因为 Feign 调用获得结果反序列化的时候 IPage 是接口无法进行反序列化，需要使用具体实现类
         */
        return shortLinkFeignRemoteService.pageShortLinkList(requestParam.getGid(), requestParam.getOrderTag(), requestParam.getCurrent(), requestParam.getSize());
    }

    /**
     * 短链接分组数量查询
     */
    @GetMapping(value = "count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParams) {
        return shortLinkFeignRemoteService.listGroupShortLinkCount(requestParams);
    }

    /**
     * 远程调用根据短链接获取网站标题
     */
    @GetMapping(value = "title")
    public Result<String> getTitleByUrl(@RequestParam(name = "url") String url) {
        return shortLinkFeignRemoteService.getTitleByUrl(url);
    }

    /**
     * 远程调用单个短链接详细监控数据访问
     */
    @GetMapping(value = "stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return shortLinkFeignRemoteService.oneShortLinkStats(requestParam);
    }

    /**
     * 分组短链接详细监控数据访问
     */
    @GetMapping(value = "stats/group")
    public Result<ShortLinkGroupStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return shortLinkFeignRemoteService.groupShortLinkStats(requestParam);
    }

    /**
     * 远程调用单个短链接访问日志监控数据
     */
    @GetMapping(value = "stats/access/record")
    public Result<Page<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return shortLinkFeignRemoteService.oneShortLinkStatsAccessRecord(requestParam);
    }

    /**
     * 分组短链接访问日志监控数据
     */
    @GetMapping(value = "stats/access/record/group")
    public Result<Page<ShortLinkGroupStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return shortLinkFeignRemoteService.shortLinkGroupStatsAccessRecord(requestParam);
    }

}
