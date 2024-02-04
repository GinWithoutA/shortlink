package org.ginwithouta.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.common.convention.result.Results;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinRemoveReqDTO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinRestoreReqDTO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.project.service.RecycleBinService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 回收站控制层
 */
@RestController
@RequestMapping(value = "/api/short/link/project/v1/recycle/bin/")
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
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLinkList(RecycleBinPageReqDTO requestParam) {
        return Results.success(recycleBinService.pageRecycleBinList(requestParam));
    }

    /**
     * 恢复短链接，将短链接从回收站中移除
     */
    @PostMapping(value = "restore")
    public Result<Void> restoreRecycleBin(@RequestBody RecycleBinRestoreReqDTO requestParam) {
        recycleBinService.restore(requestParam);
        return Results.success();
    }

    /**
     * 移除回收站中的短链接
     */
    @PostMapping(value = "remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        recycleBinService.remove(requestParam);
        return Results.success();
    }
}
