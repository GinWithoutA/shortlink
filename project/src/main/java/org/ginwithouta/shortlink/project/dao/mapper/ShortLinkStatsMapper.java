package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接基础访问持久层
 */
public interface ShortLinkStatsMapper extends BaseMapper<ShortLinkStatsDO> {

    /**
     * 短链接监控基本数据保存
     * @param statsDO 要保存的监控数据
     */
    @Insert("INSERT INTO" +
            "  t_link_stats (full_short_url, date, pv, uv, uip, hour, weekday, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{statsDO.fullShortUrl}, #{statsDO.date}, #{statsDO.pv}, #{statsDO.uv}, #{statsDO.uip}, #{statsDO.hour}, #{statsDO.weekday}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  pv = pv + 1, uv = uv + #{statsDO.uv}, uip = uip + #{statsDO.uip}, update_time = NOW();")
    void shortLinkStats(@Param("statsDO") ShortLinkStatsDO statsDO);

    /**
     * 查询单个短链接的监控记录
     * @param requestParam 短链接请求入参
     * @return 短链接监控记录
     */
    @Select("SELECT tls.date, SUM(tls.pv) AS pv, SUM(tls.uv) as uv, SUM(tls.uip) as uip " +
            "FROM t_link tl INNER JOIN t_link_stats tls ON tl.full_short_url = tls.full_short_url " +
            "WHERE tls.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus}" +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate}" +
            "GROUP BY tls.full_short_url, tl.gid, tls.date;")
    List<ShortLinkStatsDO> listStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 查询分组短链接的监控记录
     * @param requestParam 短链接请求入参
     * @return 短链接监控记录
     */
    @Select("SELECT tls.date, SUM(tls.pv) AS pv, SUM(tls.uv) as uv, SUM(tls.uip) as uip " +
            "FROM t_link tl INNER JOIN t_link_stats tls ON tl.full_short_url = tls.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = '1' " +
            "   AND tls.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tl.gid, tls.date;")
    List<ShortLinkStatsDO> listStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 根据短链接获取访问的小时分布情况
     * @param requestParam 短链接请求入参
     * @return 短链接小时监控数据
     */
    @Select("SELECT tls.hour, SUM(tls.pv) AS pv " +
            "FROM t_link tl INNER JOIN t_link_stats tls ON tl.full_short_url = tls.full_short_url " +
            "WHERE tls.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus}   " +
            "   AND tls.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tls.full_short_url, tl.gid, tls.hour;")
    List<ShortLinkStatsDO> listHoursStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接分组获取访问的小时分布情况
     * @param requestParam 短链接请求入参
     * @return 短链接分组小时监控数据
     */
    @Select("SELECT tls.hour, SUM(tls.pv) AS pv " +
            "FROM t_link tl INNER JOIN t_link_stats tls ON tl.full_short_url = tls.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = '1' " +
            "   AND tl.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tl.gid, tls.hour; ")
    List<ShortLinkStatsDO> listHoursStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 根据短链接获取访问的一周分布情况
     */
    @Select("SELECT tls.weekday, SUM(tls.pv) AS pv " +
            "FROM t_link tl INNER JOIN t_link_stats tls ON tl.full_short_url = tls.full_short_url " +
            "WHERE tls.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tls.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tls.full_short_url, tl.gid, tls.weekday;")
    List<ShortLinkStatsDO> listWeekStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接获取访问的一周分布情况
     */
    @Select("SELECT tls.weekday, SUM(tls.pv) AS pv " +
            "FROM t_link tl INNER JOIN t_link_stats ON tl.full_short_url = tls.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = '1' " +
            "   AND tls.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tl.gid, tls.weekday;")
    List<ShortLinkStatsDO> listWeekStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
