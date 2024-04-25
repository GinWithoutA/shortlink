package org.ginwithouta.shortlink.admin.remote.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinPageReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.cloud.openfeign.SpringQueryMap;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
public interface RecycleBinRemoteService {

    /**
     * 远程调用分页查询回收站短链接
     * @param requestParam 分页查询入参，入参中没有包含 gidLists
     * @return 分页查询结果
     */
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinList(@SpringQueryMap RecycleBinPageReqDTO requestParam);

}
