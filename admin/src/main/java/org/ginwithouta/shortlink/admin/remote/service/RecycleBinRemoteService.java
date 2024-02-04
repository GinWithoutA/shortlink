package org.ginwithouta.shortlink.admin.remote.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinPageReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinRemoveReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinRestoreReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
public interface RecycleBinRemoteService {

    String URL_PREFIX = "http://127.0.0.1:8001/api/short/link/project/v1/recycle/bin/";

    /**
     * 远程调用保存回收站功能
     * @param requestParam 保存回收站入参
     */
    default void saveRecycleBin(RecycleBinSaveReqDTO requestParam) {
        HttpUtil.post(URL_PREFIX + "save", JSON.toJSONString(requestParam));
    }

    /**
     * 远程调用分页查询回收站短链接
     * @param requestParam 分页查询入参，入参中没有包含 gidLists
     * @return 分页查询结果
     */
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinList(RecycleBinPageReqDTO requestParam);

    /**
     * 恢复短链接，将短链接从回收站中移除
     * @param requestParam 恢复短链接请求入参
     */
    default void restoreRecycleBin(@RequestBody RecycleBinRestoreReqDTO requestParam) {
        HttpUtil.post(URL_PREFIX + "recover", JSON.toJSONString(requestParam));
    }

    /**
     * 移除回收站中的短链接
     * @param requestParam 移除短链接请求入参
     */
    default void removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        HttpUtil.post(URL_PREFIX + "remove", JSON.toJSONString(requestParam));
    }
}
