package org.ginwithouta.shortlink.project.dto.req;

import lombok.Data;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 从回收站中删除短链接请求入参DTO
 */
@Data
public class RecycleBinRemoveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
