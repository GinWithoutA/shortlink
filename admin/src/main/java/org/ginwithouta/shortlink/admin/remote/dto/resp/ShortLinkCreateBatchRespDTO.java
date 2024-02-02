package org.ginwithouta.shortlink.admin.remote.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2024/2/2
 */
@Data
@Builder
public class ShortLinkCreateBatchRespDTO {
    /**
     * 完整短链接
     */
    private List<String> fullShortUrls;

    /**
     * 原始链接（短链接跳转的目标链接）
     */
    private List<String> originUrls;

    /**
     * 分组标识
     */
    private String gid;
}
