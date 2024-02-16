package org.ginwithouta.shortlink.project.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateBatchReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.handler.CustomBlockHandler;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.ginwithouta.shortlink.project.common.constant.SentinelConstant.SENTINEL_CREATE_RULE_NAME;

/**
 * @Package : org.ginwithouta.shortlink.project.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/short/link/project/v1/")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping(value = "create")
    @SentinelResource(
            value = SENTINEL_CREATE_RULE_NAME,
            blockHandler = "createShortLinkBlockHandlerMethod",
            blockHandlerClass = CustomBlockHandler.class
    )
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShorLink(requestParam));
    }

    /**
     * 批量创建短链接
     */
    @PostMapping(value = "create/batch")
    public Result<ShortLinkCreateBatchRespDTO> createBatchShorLink(@RequestBody ShortLinkCreateBatchReqDTO requestParam) {
        return Results.success(shortLinkService.createBatchShortLink(requestParam));
    }

    /**
     * 修改短链接
     */
    @PostMapping(value = "update")
    public Result<Void> update(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 分页查询短链接
     */
    @GetMapping(value = "page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLinkList(requestParam));
    }

    /**
     * 短链接分组数量查询
     */
    @GetMapping(value = "count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParams") List<String> requestParams) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParams));
    }

    /**
     * 根据 URL 获取网站标题
     */
    @GetMapping(value = "title")
    public Result<String> getTitleByUrl(@RequestParam(name = "url") String url) {
        return Results.success(shortLinkService.getTitleByUrl(url));
    }
}
