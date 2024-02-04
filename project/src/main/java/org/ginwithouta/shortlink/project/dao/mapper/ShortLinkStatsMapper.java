package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接基础访问持久层
 */
public interface ShortLinkStatsMapper extends BaseMapper<ShortLinkStatsDO> {

    /**
     * 短链接监控数据插入
     * @param statisticsDO 要保存的监控数据
     */
    @Insert("INSERT INTO" +
            "  t_statistics (full_short_url, gid, date, pv, uv, uip, hour, weekday, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{statisticsDO.fullShortUrl}, #{statisticsDO.gid}, #{statisticsDO.date}, #{statisticsDO.pv}, " +
            "   #{statisticsDO.uv}, #{statisticsDO.uip}, #{statisticsDO.hour}, #{statisticsDO.weekday}, " +
            "   NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  pv = pv + 1, uv = uv + #{statisticsDO.uv}, uip = uip + #{statisticsDO.uip}, update_time = NOW();")
    void shortLinkStatistics(@Param("statisticsDO") ShortLinkStatsDO statisticsDO);

    /**
     * 查询单个短链接的监控记录
     * @param requestParam 短链接请求入参
     * @return 短链接监控记录
     */
    @Select("SELECT date, SUM(pv) AS pv, SUM(uv) as uv, SUM(uip) as uip FROM t_statistics " +
            "WHERE full_short_url = #{requestParam.fullShortUrl} AND gid = ${requestParam.gid} AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate}" +
            "GROUP BY full_short_url, gid, date;")
    List<ShortLinkStatsDO> listStatisticsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接获取访问的小时分布情况
     * @param requestParam 短链接请求入参
     * @return 短链接小时监控数据
     */
    @Select("SELECT hour, SUM(pv) AS pv FROM t_statistics " +
            "WHERE full_short_url = #{requestParam.shortLinkUrl} " +
            "   AND gid = #{requestParam.gid} " +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, hour;")
    List<ShortLinkStatsDO> listHoursStatisticsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    @Select("SELECT weekday, SUM(pv) AS pv FROM t_statistics " +
            "WHERE full_short_url = #{requestParam.shortLinkUrl} " +
            "   AND gid = #{requestParam.gid} " +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, weekday;")
    List<ShortLinkStatsDO> listWeekStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);
}
