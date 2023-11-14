package org.ginwithouta.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(value = "link")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShorLink(requestParam));
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
}
