package org.ginwithouta.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 从回收站移除短链接请求DTO
 */
@Data
public class RecycleBinRestoreReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
