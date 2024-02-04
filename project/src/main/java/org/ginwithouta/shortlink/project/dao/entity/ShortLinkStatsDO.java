package org.ginwithouta.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ginwithouta.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 基础访问实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_statistics")
@EqualsAndHashCode(callSuper = true)
public class ShortLinkStatsDO extends BaseDO {

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
     * 页面访问量（Page View）
     */
    private Integer pv;

    /**
     * 独立访客数量（User View）
     */
    private Integer uv;

    /**
     * 独立IP数（User IP）
     */
    private Integer uip;

    /**
     * 小时
     */
    private Integer hour;

    /**
     * 星期
     */
    private Integer weekday;

}
