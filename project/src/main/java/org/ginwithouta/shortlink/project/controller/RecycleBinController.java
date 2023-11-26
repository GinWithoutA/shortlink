package org.ginwithouta.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.service.RecycleBinService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 回收站控制层
 */
@RestController
@RequestMapping(value = "/api/short/link/recycle/bin/v1/")
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    /**
     * 保存回收站
     */
    @PostMapping(value = "save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.save(requestParam);
        return Results.success();
    }

    /**
     * 分页查询回收站短链接
     */
    @GetMapping(value = "page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        return Results.success(recycleBinService.pageRecycleBinList(requestParam));
    }
}
