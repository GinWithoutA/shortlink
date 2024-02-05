package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkLocaleStatisticsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接地区统计访问持久层
 */
public interface ShortLinkStatsLocaleMapper extends BaseMapper<ShortLinkLocaleStatisticsDO> {

    /**
     * 新增h或更新短链接地区统计
     * @param localeStatisticsDO 短链接地区统计 DO
     */
    @Insert("INSERT INTO" +
            "  t_locale_statistics (full_short_url, gid, date, cnt, province, city, country, adcode, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{localeStatisticsDO.fullShortUrl}, #{localeStatisticsDO.gid}, #{localeStatisticsDO.date}, #{localeStatisticsDO.cnt}, " +
            "   #{localeStatisticsDO.province}, #{localeStatisticsDO.city}, #{localeStatisticsDO.country}, #{localeStatisticsDO.adcode}, " +
            "   NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{localeStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkLocaleStatistics(@Param("localeStatisticsDO") ShortLinkLocaleStatisticsDO localeStatisticsDO);

    /**
     * 根据短链接获取指定日期范围的地区监控记录
     * @param requestParam 请求入参
     * @return 指定日期的监控记录
     */
    @Select("SELECT province, SUM(cnt) AS cnt FROM t_locale_statistics " +
            "WHERE full_short_url = #{requestParam.fullShortUrl} " +
            "   AND gid = #{requestParam.gid} " +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, province;")
    List<ShortLinkLocaleStatisticsDO> listLocaleByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接分组获取指定日期范围的地区监控记录
     * @param requestParam 请求入参
     * @return 指定日期的监控记录
     */
    @Select("SELECT province, SUM(cnt) AS cnt FROM t_locale_statistics " +
            "WHERE gid = #{requestParam.gid} " +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY gid, province;")
    List<ShortLinkLocaleStatisticsDO> listLocaleByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
