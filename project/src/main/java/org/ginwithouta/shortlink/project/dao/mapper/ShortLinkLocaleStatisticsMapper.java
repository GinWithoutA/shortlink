package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkLocaleStatisticsDO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接地区统计访问持久层
 */
public interface ShortLinkLocaleStatisticsMapper extends BaseMapper<ShortLinkLocaleStatisticsDO> {

    /**
     * 短链接地区统计插入
     * @param localeStatisticsDO 短链接地区统计 DO
     */
    @Insert("INSERT INTO" +
            "  t_locale_statistics (full_short_url, gid, date, cnt, province, city, country, adcode, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{localeStatisticsDO.fullShortUrl}, #{localeStatisticsDO.gid}, #{localeStatisticsDO.date}, #{localeStatisticsDO.cnt}, #{localeStatisticsDO.province}, #{localeStatisticsDO.city}, #{localeStatisticsDO.country}, #{localeStatisticsDO.adcode}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{localeStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkLocaleStatistics(@Param("localeStatisticsDO") ShortLinkLocaleStatisticsDO localeStatisticsDO);
}
