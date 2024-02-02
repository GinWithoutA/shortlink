package org.ginwithouta.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ginwithouta.shortlink.project.common.database.BaseDO;

import java.time.LocalDateTime;

/**
 * @Package : org.ginwithouta.shortlink.project.dao.entity
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_link")
@EqualsAndHashCode(callSuper = true)
public class ShortLinkDO extends BaseDO {

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
     * 点击量
     */
    private Integer clickNum;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 网站图标
     */
    private String favicon;

    /**
     * 短链接启用标识 0 未启用 1 已启用
     */
    private Integer enable;

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
    private LocalDateTime validDate;

    /**
     * 短链接描述
     */
    private String describe;

}
