package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkLocaleStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接地区统计访问持久层
 */
public interface ShortLinkLocaleStatsMapper extends BaseMapper<ShortLinkLocaleStatsDO> {

    /**
     * 新增h或更新短链接地区统计
     * @param localeStatisticsDO 短链接地区统计 DO
     */
    @Insert("INSERT INTO" +
            "  t_link_locale_stats (full_short_url, date, cnt, province, city, country, adcode, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{localeStatisticsDO.fullShortUrl}, #{localeStatisticsDO.date}, #{localeStatisticsDO.cnt}, #{localeStatisticsDO.province}, " +
            "   #{localeStatisticsDO.city}, #{localeStatisticsDO.country}, #{localeStatisticsDO.adcode}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt + #{localeStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkLocaleStats(@Param("localeStatisticsDO") ShortLinkLocaleStatsDO localeStatisticsDO);

    /**
     * 根据短链接获取指定日期范围的地区监控记录
     * @param requestParam 请求入参
     * @return 指定日期的监控记录
     */
    @Select("SELECT tlls.province, SUM(tlls.cnt) AS cnt " +
            "FROM t_link tl INNER JOIN t_link_locale_stats tlls ON tl.full_short_url = tlls.full_short_url " +
            "WHERE tlls.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlls.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tlls.full_short_url, tl.gid, tlls.province;")
    List<ShortLinkLocaleStatsDO> listLocaleStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接分组获取指定日期范围的地区监控记录
     * @param requestParam 请求入参
     * @return 指定日期的监控记录
     */
    @Select("SELECT tlls.province, SUM(tlls.cnt) AS cnt " +
            "FROM t_link tl INNER JOIN t_link_locale_stats tlls ON tl.full_short_url = tlls.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = '1' " +
            "   AND tlls.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tl.gid, tlls.province;")
    List<ShortLinkLocaleStatsDO> listLocaleStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
