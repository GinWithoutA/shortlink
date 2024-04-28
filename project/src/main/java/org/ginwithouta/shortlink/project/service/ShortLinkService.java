package org.ginwithouta.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateBatchReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

/**
 * @Package : org.ginwithouta.shortlink.project.service
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接接口
     * @param requestParam 创建短链接入参
     * @return 短链接
     */
    ShortLinkCreateRespDTO createShorLink(ShortLinkCreateReqDTO requestParam);

    /**
     * 分页查询短链接
     * @param requestParam 请求参数
     * @return 短链接分页返回
     */
    IPage<ShortLinkPageRespDTO> pageShortLinkList(ShortLinkPageReqDTO requestParam);

    /**
     * 短链接分组数量查询
     * @param requestParams 短链接分组数量查询入参
     * @return 短链接分组数量
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParams);

    /**
     * 短链接修改
     * @param requestParam 短链接修改入参
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * 短链接跳转
     * @param shortUri 短链接后缀
     * @param request 短链接请求
     * @param response 短链接相应
     */
    void redirectUrl(String shortUri, ServletRequest request, ServletResponse response);

    /**
     * 根据 URL 获取对应网站标题
     */
    String getTitleByUrl(String url);

    /**
     * 批量创建短链接接口
     * @param requestParams 需要创建的短链接
     * @return 创建好的短链接
     */
    ShortLinkCreateBatchRespDTO createBatchShortLink(ShortLinkCreateBatchReqDTO requestParams);

    /**
     * 短链接统计
     * @param shortLinkStatsRecord  短链接统计信息
     * @param gid                   短链接分组标识
     * @param fullShortUrl          短链接
     */
    void shortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO shortLinkStatsRecord);
}
