package org.ginwithouta.shortlink.admin.remote.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2024/2/2
 * 批量创建短链接请求
 */
@Data
public class ShortLinkCreateBatchReqDTO {
    /**
     * 域名
     */
    private String domain;

    /**
     * 原始链接（短链接跳转的目标链接）
     */
    private List<String> originUrls;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 创建类型 0 接口创建 1 平台创建
     */
    private Integer createdType;

    /**
     * 有效期类型 0 永久有效 1 临时有效
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime validDate;

    /**
     * 短链接描述
     */
    private List<String> describes;
}
