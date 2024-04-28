package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkNetworkStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接访问网络统计访问持久层
 */
public interface ShortLinkNetworkStatsMapper extends BaseMapper<ShortLinkNetworkStatsDO> {

    /**
     * 记录网络访问监控数据
     * @param networkStatsDO 短链接访问网络监控入参
     */
    @Insert("INSERT INTO" +
            "  t_link_network_stats (full_short_url, date, cnt, network, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{networkStatsDO.fullShortUrl}, #{networkStatsDO.date}, #{networkStatsDO.cnt}, #{networkStatsDO.network}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt + #{networkStatsDO.cnt}, update_time = NOW();")
    void shortLinkNetworkStats(@Param("networkStatsDO") ShortLinkNetworkStatsDO networkStatsDO);

    /**
     * 根据单个短链接获取网络类型访问监控数据
     */
    @Select("SELECT tlns.network, SUM(tlns.cnt) AS cnt " +
            "FROM t_link tl INNER JOIN t_link_network_stats tlns ON tl.full_short_url = tlns.full_short_url " +
            "WHERE tlns.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlns.date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY tlns.full_short_url, tl.gid, tlns.network;")
    List<ShortLinkNetworkStatsDO> listNetworkStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接分组获取网络类型访问监控数据
     */
    @Select("SELECT tlns.network, SUM(tlns.cnt) AS cnt " +
            "FROM t_link tl INNER JOIN t_link_network_stats tlns ON tl.full_short_url = tlns.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlns.date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY tl.gid, tlns.network;")
    List<ShortLinkNetworkStatsDO> listNetworkStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
