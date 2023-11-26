package org.ginwithouta.shortlink.admin.controller.remote;

import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.remote.RecycleBinRemoteService;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
@RestController
@RequestMapping(value = "/api/short/link/recycle/bin/v1/")
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
}
