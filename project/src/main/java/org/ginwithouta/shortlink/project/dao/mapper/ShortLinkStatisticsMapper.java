package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatisticsDO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接基础访问持久层
 */
public interface ShortLinkStatisticsMapper extends BaseMapper<ShortLinkStatisticsDO> {

    @Insert("INSERT INTO" +
            "  t_statistics (full_short_url, gid, date, cnt, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{statisticsDO.fullShortUrl}, #{statisticsDO.gid}, #{statisticsDO.date}, #{statisticsDO.cnt}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = #{statisticsDO.cnt}, update_time = NOW();")
    void shortLinkStatistics(@Param("statisticsDO") ShortLinkStatisticsDO statisticsDO);
}
