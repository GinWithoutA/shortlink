package org.ginwithouta.shortlink.project.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 短链接监控日志访问请求响应 DTO
 */
@Data
public class ShortLinkGroupStatsAccessRecordRespDTO {
    /**
     * 访问类型
     */
    private String uvType;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 访问的浏览器
     */
    private String browser;

    /**
     * 访问的操作系统
     */
    private String os;

    /**
     * 访问的 IP
     */
    private String ip;

    /**
     * 用户信息
     */
    private String user;

    /**
     * 访问地区
     */
    private String locale;

    /**
     * 访问设备
     */
    private String device;

    /**
     * 访问网络类型
     */
    private String network;
}
