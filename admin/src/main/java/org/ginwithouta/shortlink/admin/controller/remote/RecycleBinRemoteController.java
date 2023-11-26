package org.ginwithouta.shortlink.admin.controller.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.remote.RecycleBinRemoteService;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
@RestController
@RequestMapping(value = "/api/short/link/admin/recycle/bin/v1/")
public class RecycleBinRemoteController {

    /**
     * TODO 后续重构为 Spring Cloud
     * 远程调用接口
     */
    private final RecycleBinRemoteService recycleBinRemoteService = new RecycleBinRemoteService() {};

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
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinList(ShortLinkPageReqDTO requestParam) {
        return recycleBinRemoteService.pageRecycleBinList(requestParam);
    }
}
