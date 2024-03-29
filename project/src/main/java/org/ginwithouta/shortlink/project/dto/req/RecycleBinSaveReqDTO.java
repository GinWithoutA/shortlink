package org.ginwithouta.shortlink.project.dto.req;

import lombok.Data;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 将短链接移动至回收站请求DTO
 */
@Data
public class RecycleBinSaveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
