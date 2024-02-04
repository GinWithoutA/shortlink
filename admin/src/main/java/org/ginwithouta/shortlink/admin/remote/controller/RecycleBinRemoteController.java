package org.ginwithouta.shortlink.admin.remote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinPageReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinRemoveReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinRestoreReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.admin.remote.service.RecycleBinRemoteService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/short/link/admin/v1/recycle/bin/")
public class RecycleBinRemoteController {

    /**
     * TODO 后续重构为 Spring Cloud
     * 远程调用接口
     */
    private final RecycleBinRemoteService recycleBinRemoteService;

    /**
     * 远程调用保存回收站接口
     */
    @PostMapping(value = "save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * HTTP请求远程调用分页查询回收站短链接
     */
    @GetMapping(value = "page")
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinList(RecycleBinPageReqDTO requestParam) {
        return recycleBinRemoteService.pageRecycleBinList(requestParam);
    }

    /**
     * 恢复短链接，将短链接从回收站中移除
     */
    @PostMapping(value = "restore")
    public Result<Void> restoreRecycleBin(@RequestBody RecycleBinRestoreReqDTO requestParam) {
        recycleBinRemoteService.restoreRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * 移除回收站中的短链接
     */
    @PostMapping(value = "remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        recycleBinRemoteService.removeRecycleBin(requestParam);
        return Results.success();
    }
}
