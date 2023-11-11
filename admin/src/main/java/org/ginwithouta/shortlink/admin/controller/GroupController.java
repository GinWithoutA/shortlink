package org.ginwithouta.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
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
@RequiredArgsConstructor
@RequestMapping(value = "/api/short/link/admin/v1/")
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

    /**
     * 查询短链接分组集合
     */
    @GetMapping(value = "group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组名
     */
    @PutMapping(value = "group")
    public Result<Void> update(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * 删除短链接分组
     */
    @DeleteMapping(value = "group")
    public Result<Void> delete(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 删除短链接分组
     */
    @PostMapping(value = "group/sort")
    public Result<Void> sort(@RequestBody List<ShortLinkGroupSortReqDTO> requestParams) {
        groupService.sortGroup(requestParams);
        return Results.success();
    }
}
