package org.ginwithouta.shortlink.admin.remote.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Package : org.ginwithouta.shortlink.project.dto.resp
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 分页返回参数
 */
@Data
public class ShortLinkPageRespDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接（短链接跳转的目标链接）
     */
    private String originUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 网站图标
     */
    private String favicon;

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
    private String describe;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 历史 PV
     */
    private Integer totalPv;

    /**
     * 历史 UV
     */
    private Integer totalUv;

    /**
     * 历史 UIP
     */
    private Integer totalUip;

    /**
     * 启用标识 1：启用 0：未启用
     */
    private Integer enableStatus;

    /**
     * 今日 PV
     */
    private Integer todayPv;

    /**
     * 今日 UV
     */
    private Integer todayUv;

    /**
     * 今日 UIP
     */
    private Integer todayUip;
}
