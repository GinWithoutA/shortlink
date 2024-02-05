package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDeviceStatisticsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接访问设备监控持久层
 */
public interface ShortLinkDeviceStatsMapper extends BaseMapper<ShortLinkDeviceStatisticsDO> {

    /**
     * 记录访问设备访问数据
     * @param deviceStatisticsDO 短链接访问设备监控入参
     */
    @Insert("INSERT INTO" +
            "  t_device_statistics (full_short_url, gid, date, cnt, device, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{deviceStatisticsDO.fullShortUrl}, #{deviceStatisticsDO.gid}, #{deviceStatisticsDO.date}, #{deviceStatisticsDO.cnt}, " +
            "   #{deviceStatisticsDO.device}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{deviceStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkDeviceStatistics(@Param("deviceStatisticsDO") ShortLinkDeviceStatisticsDO deviceStatisticsDO);

    /**
     * 短链接监控之访问设备响应 DTO
     */
    @Select("SELECT device, SUM(cnt) AS cnt FROM t_device_statistics WHERE " +
            "    full_short_url = #{requestParam.fullShortUrl} " +
            "    AND gid = #{requestParam.gid} " +
            "    AND date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, device;")
    List<ShortLinkDeviceStatisticsDO> listDeviceStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 短链接监控之访问设备响应 DTO
     */
    @Select("SELECT device, SUM(cnt) AS cnt FROM t_device_statistics WHERE " +
            "    gid = #{requestParam.gid} " +
            "    AND date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY gid, device;")
    List<ShortLinkDeviceStatisticsDO> listDeviceStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
