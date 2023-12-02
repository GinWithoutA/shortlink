package org.ginwithouta.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ginwithouta.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * @author Ginwithouta
 * Generate at 2023/12/1
 * 短链接访问设备统计实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_device_statistics")
@EqualsAndHashCode(callSuper = true)
public class ShortLinkDeviceStatisticsDO extends BaseDO {
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
     * 访问量
     */
    private Integer cnt;

    /**
     * 访问的设备
     */
    private String device;
}
