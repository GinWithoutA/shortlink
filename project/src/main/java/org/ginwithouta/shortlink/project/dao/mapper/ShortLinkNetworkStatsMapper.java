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
     * @param networkStatisticsDO 短链接访问网络监控入参
     */
    @Insert("INSERT INTO" +
            "  t_network_statistics (full_short_url, gid, date, cnt, network, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{networkStatisticsDO.fullShortUrl}, #{networkStatisticsDO.gid}, #{networkStatisticsDO.date}, #{networkStatisticsDO.cnt}, " +
            "   #{networkStatisticsDO.network}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{networkStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkNetworkStatistics(@Param("networkStatisticsDO") ShortLinkNetworkStatsDO networkStatisticsDO);

    /**
     * 根据单个短链接获取网络类型访问监控数据
     */
    @Select("SELECT network, SUM(cnt) AS cnt FROM t_network_statistics WHERE " +
            "    full_short_url = #{requestParam.fullShortUrl} " +
            "    AND gid = #{requestParam.gid} " +
            "    AND date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, network;")
    List<ShortLinkNetworkStatsDO> listNetworkStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接分组获取网络类型访问监控数据
     */
    @Select("SELECT network, SUM(cnt) AS cnt FROM t_network_statistics WHERE " +
            "    gid = #{requestParam.gid} " +
            "    AND date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY gid, network;")
    List<ShortLinkNetworkStatsDO> listNetworkStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
