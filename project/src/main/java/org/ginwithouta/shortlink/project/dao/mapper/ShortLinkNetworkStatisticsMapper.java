package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkNetworkStatisticsDO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接访问网络统计访问持久层
 */
public interface ShortLinkNetworkStatisticsMapper extends BaseMapper<ShortLinkNetworkStatisticsDO> {

    /**
     * 记录网络访问监控数据
     * @param networkStatisticsDO 短链接访问网络监控入参
     */
    @Insert("INSERT INTO" +
            "  t_network_statistics (full_short_url, gid, date, cnt, network, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{networkStatisticsDO.fullShortUrl}, #{networkStatisticsDO.gid}, #{networkStatisticsDO.date}, #{networkStatisticsDO.cnt}, #{networkStatisticsDO.network}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{networkStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkNetworkStatistics(@Param("networkStatisticsDO") ShortLinkNetworkStatisticsDO networkStatisticsDO);
}
