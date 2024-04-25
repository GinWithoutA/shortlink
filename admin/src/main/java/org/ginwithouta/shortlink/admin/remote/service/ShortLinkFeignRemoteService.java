package org.ginwithouta.shortlink.admin.remote.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.remote.dto.req.*;
import org.ginwithouta.shortlink.admin.remote.dto.resp.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2024/4/22
 * 短链接中台远程调用服务
 */
@FeignClient(value = "short-link-project-service", url = "${aggregation.remote-url:}")
public interface ShortLinkFeignRemoteService {

    /**
     * 远程调用创建短链接接口
     * @param requestParam 创建短链接入参
     * @return 短链接
     */
    @PostMapping("/api/short/link/project/v1/create")
    Result<ShortLinkCreateRespDTO> createShorLink(@RequestBody ShortLinkCreateReqDTO requestParam);

    /**
     * 远程调用批量创建短链接
     */
    @PostMapping("/api/short/link/project/v1/create/batch")
    Result<ShortLinkCreateBatchRespDTO> createBatchShortLink(@RequestBody ShortLinkCreateBatchReqDTO requestParam);

    /**
     * 远程调用修改短链接
     * @param requestParam  入参
     */
    @PostMapping("/api/short/link/project/v1/update")
    void updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam);

    /**
     * 远程调用短链接分页查询
     * @param gid       分组
     * @param orderTag  排序类型
     * @param current   当前页
     * @param size      当前数据量
     * @return          分页信息
     */
    @GetMapping("/api/short/link/project/v1/page")
    Result<Page<ShortLinkPageRespDTO>> pageShortLinkList(@RequestParam("gid") String gid,
                                                         @ RequestParam("orderTag") String orderTag,
                                                         @RequestParam("current") Long current,
                                                         @RequestParam("size") Long size);

    /**
     * 远程调用短链接分组数量查询
     * @param requestParams 入参
     * @return  短链接分组数量
     */
    @GetMapping("/api/short/link/project/v1/count")
    Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParams") List<String> requestParams);

    /**
     * 远程调用根据 URL 获取网站标题
     * @param url   原始 URL
     * @return      网站标题
     */
    @GetMapping("/api/short/link/project/v1/title")
    Result<String> getTitleByUrl(@RequestParam("url") String url);

    /**
     * 远程调用保存回收站功能
     * @param requestParam 保存回收站入参
     */
    @PostMapping("/api/short/link/project/v1/recycle/bin/save")
    void saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam);

    /**
     * 远程调用回收站短链接分页查询
     * @param gidList   分组标识列表
     * @param current   当前页
     * @param size      当前数据量
     * @return    回收站分页信息
     */
    @GetMapping("/api/short/link/project/v1/recycle/bin/page")
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinList(@RequestParam("gidList") List<String> gidList,
                                                          @RequestParam("current") Long current,
                                                          @RequestParam("size") Long size);

    /**
     * 恢复短链接，将短链接从回收站中移除
     * @param requestParam 恢复短链接请求入参
     */
    @PostMapping("/api/short/link/project/v1/recycle/bin/recover")
    void restoreRecycleBin(@RequestBody RecycleBinRestoreReqDTO requestParam);

    /**
     * 移除回收站中的短链接
     * @param requestParam 移除短链接请求入参
     */
    @PostMapping("/api/short/link/project/v1/recycle/bin/remove")
    void removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam);

    /**
     * 远程调用单个短链接详细监控信息
     * @param requestParam  入参
     * @return  详细监控信息
     */
    @GetMapping("/api/short/link/project/v1/stats/")
    Result<ShortLinkStatsRespDTO> oneShortLinkStats(@SpringQueryMap ShortLinkStatsReqDTO requestParam);

    /**
     * 远程调用短链接分组监控信息
     * @param requestParam  入参
     * @return  分组详细监控信息
     */
    @GetMapping("/api/short/link/project/v1/stats/group")
    Result<ShortLinkGroupStatsRespDTO> groupShortLinkStats(@SpringQueryMap ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 远程调用单个短链接指定日期内的访问日志
     * @param requestParam  入参
     * @return  详细访问日志
     */
    @GetMapping("/api/short/link/project/v1/stats/access/record")
    Result<Page<ShortLinkStatsAccessRecordRespDTO>> oneShortLinkStatsAccessRecord(@SpringQueryMap ShortLinkStatsAccessRecordReqDTO requestParam);

    /**
     * 远程调用分组短链接指定日期内的访问日志
     * @param requestParam 入参
     * @return  详细日志信息
     */
    @GetMapping("/api/short/link/project/v1/stats/access/record/group")
    Result<Page<ShortLinkGroupStatsAccessRecordRespDTO>> shortLinkGroupStatsAccessRecord(@SpringQueryMap ShortLinkGroupStatsAccessRecordReqDTO requestParam);
}
