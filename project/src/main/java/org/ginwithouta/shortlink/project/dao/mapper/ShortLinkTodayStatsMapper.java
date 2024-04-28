package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkTodayStatsDO;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 * 短链接当日访问 Mapper
 */
public interface ShortLinkTodayStatsMapper extends BaseMapper<ShortLinkTodayStatsDO> {

    /**
     * 记录短链接当日访问数据
     */
    @Insert("INSERT INTO t_link_stats_today(full_short_url, date, today_uv, today_pv, today_uip, create_time, update_time, del_flag) " +
            "VALUES (#{linkStatsTodayDO.fullShortUrl}, #{linkStatsTodayDO.date}, #{linkStatsTodayDO.todayUv}, #{linkStatsTodayDO.todayPv}, #{linkStatsTodayDO.todayUip}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  today_uv = today_uv + #{linkStatsTodayDO.todayUv}, " +
            "  today_pv = today_pv + #{linkStatsTodayDO.todayPv},  " +
            "  today_uip = today_uip + #{linkStatsTodayDO.todayUip}, " +
            "  update_time = NOW();")
    void shortLinkTodayStats(@Param("linkStatsTodayDO") ShortLinkTodayStatsDO linkStatsTodayDO);
}
