package org.ginwithouta.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package : org.ginwithouta.shortlink.admin.remote.dto
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 短链接中台远程调用服务
 */
public interface ShortLinkRemoteService {

    /**
     * 远程调用创建短链接接口
     * @param requestParam 创建短链接入参
     * @return 短链接
     */
    default Result<ShortLinkCreateRespDTO> createShorLink(ShortLinkCreateReqDTO requestParam) {
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/short/link/project/v1/link", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {
        });
    }

    /**
     * 分页查询短链接远程调用
     * @param requestParam 请求参数
     * @return 短链接分页返回
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLinkList(ShortLinkPageReqDTO requestParam) {
        Map<String, Object> requestMap = new HashMap<>(3);
        requestMap.put("gid", requestParam.getGid());
        requestMap.put("current", requestParam.getCurrent());
        requestMap.put("size", requestParam.getSize());
        String resultPage = HttpUtil.get("http://127.0.0.1:8001/api/short/link/project/v1/page", requestMap);
        return JSON.parseObject(resultPage, new TypeReference<>() {
        });
    }

}
