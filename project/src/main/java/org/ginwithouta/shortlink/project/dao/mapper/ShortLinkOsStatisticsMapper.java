package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkOsStatisticsDO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接操作系统统计访问持久层
 */
public interface ShortLinkOsStatisticsMapper extends BaseMapper<ShortLinkOsStatisticsDO> {

    /**
     * 记录操作系统访问监控数据
     * @param osStatisticsDO 短链接操作系统统计入参
     */
    @Insert("INSERT INTO" +
            "  t_os_statistics (full_short_url, gid, date, cnt, os, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{osStatisticsDO.fullShortUrl}, #{osStatisticsDO.gid}, #{osStatisticsDO.date}, #{osStatisticsDO.cnt}, #{osStatisticsDO.os}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{osStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkOsStatistics(@Param("osStatisticsDO") ShortLinkOsStatisticsDO osStatisticsDO);
}
