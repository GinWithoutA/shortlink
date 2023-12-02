package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDeviceStatisticsDO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接访问设备监控持久层
 */
public interface ShortLinkDeviceStatisticsMapper extends BaseMapper<ShortLinkDeviceStatisticsDO> {

    /**
     * 记录访问设备访问数据
     * @param deviceStatisticsDO 短链接访问设备监控入参
     */
    @Insert("INSERT INTO" +
            "  t_device_statistics (full_short_url, gid, date, cnt, device, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{deviceStatisticsDO.fullShortUrl}, #{deviceStatisticsDO.gid}, #{deviceStatisticsDO.date}, #{deviceStatisticsDO.cnt}, #{deviceStatisticsDO.device}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{deviceStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkDeviceStatistics(@Param("deviceStatisticsDO") ShortLinkDeviceStatisticsDO deviceStatisticsDO);
}
