package org.ginwithouta.shortlink.project.dto.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Package : org.ginwithouta.shortlink.project.dto.req
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 短链接修改请求对象
 */
@Data
public class ShortLinkUpdateReqDTO {

    /**
     * 原始链接（短链接跳转的目标链接）
     */
    private String originUrl;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接启用标识 0 未启用 1 已启用
     */
    private Integer enable;

    /**
     * 有效期类型 0 永久有效 1 临时有效
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    private LocalDateTime validDate;

    /**
     * 短链接描述
     */
    private String description;
}
