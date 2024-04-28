package org.ginwithouta.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ginwithouta.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * @author Ginwithouta
 * Generate at 2023/12/1
 * 短链接监控之操作系统实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_link_os_stats")
@EqualsAndHashCode(callSuper = true)
public class ShortLinkOsStatsDO extends BaseDO {
    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 日期
     */
    private Date date;

    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 操作系统名称
     */
    private String os;
}
