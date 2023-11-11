package org.ginwithouta.shortlink.admin.controller;

import lombok.AllArgsConstructor;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.ginwithouta.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.ginwithouta.shortlink.admin.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package : org.ginwithouta.shortlink.admin.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组控制层
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/short/link/v1/")
public class GroupController {
    private final GroupService groupService;

    /**
     * 新增短链接分组
     */
    @PostMapping(value = "group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping(value = "group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

}
