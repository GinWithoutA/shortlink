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
     * @param osStatisticsDO 短链接操作系统统计入参
     */
    @Insert("INSERT INTO" +
            "  t_os_statistics (full_short_url, gid, date, cnt, os, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{osStatisticsDO.fullShortUrl}, #{osStatisticsDO.gid}, #{osStatisticsDO.date}, #{osStatisticsDO.cnt}, " +
            "   #{osStatisticsDO.os}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{osStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkOsStatistics(@Param("osStatisticsDO") ShortLinkOsStatsDO osStatisticsDO);

    /**
     * 根据短链接获取操作系统监控记录
     */
    @Select("SELECT os, SUM(cnt) AS count FROM t_os_statistics WHERE " +
            "    full_short_url = #{requestParam.fullShortUrl} " +
            "    AND gid = #{requestParam.gid} " +
            "    AND date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, os;")
    List<HashMap<String, Object>> listOsStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接获取操作系统监控记录
     */
    @Select("SELECT os, SUM(cnt) AS count FROM t_os_statistics WHERE gid = #{requestParam.gid} " +
            "    AND date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY gid, os;")
    List<HashMap<String, Object>> listOsStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
