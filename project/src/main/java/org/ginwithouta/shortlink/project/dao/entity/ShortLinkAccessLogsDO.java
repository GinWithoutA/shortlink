package org.ginwithouta.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ginwithouta.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * @author Ginwithouta
 * Generate at 2023/12/1
 * 短链接访问日志监控实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_access_logs")
@EqualsAndHashCode(callSuper = true)
public class ShortLinkAccessLogsDO extends BaseDO {
    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 日期
     */
    private Date date;

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
