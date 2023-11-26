package org.ginwithouta.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
public interface RecycleBinRemoteService {

    String URL_PREFIX = "http://127.0.0.1:8001/api/short/link/recycle/bin/v1/";

    /**
     * 远程调用保存回收站功能
     * @param requestParam 保存回收站入参
     */
    default void saveRecycleBin(RecycleBinSaveReqDTO requestParam) {
        HttpUtil.post(URL_PREFIX + "save", JSON.toJSONString(requestParam));
    }

    /**
     * 远程调用分页查询回收站短链接
     * @param requestParam 分页查询入参
     * @return 分页查询结果
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinList(ShortLinkPageReqDTO requestParam) {
        Map<String, Object> requestMap = new HashMap<>(3);
        requestMap.put("gid", requestParam.getGid());
        requestMap.put("current", requestParam.getCurrent());
        requestMap.put("size", requestParam.getSize());
        String resultPage = HttpUtil.get(URL_PREFIX + "page", requestMap);
        return JSON.parseObject(resultPage, new TypeReference<>() {});
    }
}
