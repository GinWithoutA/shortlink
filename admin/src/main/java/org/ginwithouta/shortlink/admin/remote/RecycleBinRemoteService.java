package org.ginwithouta.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
public interface RecycleBinRemoteService {

    String URL_PREFIX = "http://127.0.0.1:8001/api/short/link/recycle/bin/v1/";

    /**
     * 远程调用保存回收站功能
     * @param requestParam 保存回收站入参
     * @return
     */
    default void saveRecycleBin(RecycleBinSaveReqDTO requestParam) {
        HttpUtil.post(URL_PREFIX + "save", JSON.toJSONString(requestParam));
    }
}
