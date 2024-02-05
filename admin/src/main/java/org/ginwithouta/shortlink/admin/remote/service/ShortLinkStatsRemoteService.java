package org.ginwithouta.shortlink.admin.remote.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkGroupStatsRespDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 短链接监控接口层
 */
public interface ShortLinkStatsRemoteService {
    String URL_PREFIX = "http://127.0.0.1:8001/api/short/link/project/v1/stats/";

    /**
     * 单个短链接的详细监控信息
     */
    default Result<ShortLinkStatsRespDTO> oneShortLinkStatistics(ShortLinkStatsReqDTO requestParam) {
        String resultBodyStr = HttpUtil.get(URL_PREFIX, BeanUtil.beanToMap(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {});
    }

    /**
     * 单个短链接指定时间内监控记录数据
     */
    default Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        String resultBodyStr = HttpUtil.get(URL_PREFIX + "access/record", BeanUtil.beanToMap(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {});
    }

    default Result<ShortLinkGroupStatsRespDTO> groupShortLinkStatistics(ShortLinkGroupStatsReqDTO requestParam) {
        String resultBodyStr = HttpUtil.get(URL_PREFIX + "group", BeanUtil.beanToMap(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {});
    }
}
