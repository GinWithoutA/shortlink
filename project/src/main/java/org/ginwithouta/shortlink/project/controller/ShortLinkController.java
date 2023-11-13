package org.ginwithouta.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "link")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShorLink(requestParam));
    }
}
