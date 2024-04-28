package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkOsStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接操作系统统计访问持久层
 */
public interface ShortLinkOsStatsMapper extends BaseMapper<ShortLinkOsStatsDO> {

    /**
     * 记录操作系统访问监控数据
     * @param osStatsDO 短链接操作系统统计入参
     */
    @Insert("INSERT INTO t_link_os_stats(full_short_url, date, cnt, os, create_time, update_time, del_flag) " +
            "VALUES (#{osStatsDO.fullShortUrl}, #{osStatsDO.date}, #{osStatsDO.cnt}, #{osStatsDO.os}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt + #{osStatsDO.cnt}, update_time = NOW(); ")
    void shortLinkOsStats(@Param("osStatsDO") ShortLinkOsStatsDO osStatsDO);

    /**
     * 根据短链接获取操作系统监控记录
     */
    @Select("SELECT tlos.os, SUM(tlos.cnt) AS count " +
            "FROM t_link tl INNER JOIN t_link_os_stats tlos ON tl.full_short_url = tlos.full_short_url " +
            "WHERE tlos.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlos.date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY tlos.full_short_url, tl.gid, tlos.os;")
    List<HashMap<String, Object>> listOsStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接获取操作系统监控记录
     */
    @Select("SELECT tlos.os, SUM(tlos.cnt) AS count " +
            "FROM t_link tl INNER JOIN t_link_os_stats tlos ON tl.full_short_url = tlos.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlos.date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY tl.gid, tlos.os;")
    List<HashMap<String, Object>> listOsStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
