package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDeviceStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接访问设备监控持久层
 */
public interface ShortLinkDeviceStatsMapper extends BaseMapper<ShortLinkDeviceStatsDO> {

    /**
     * 短链接访问设备监控信息录入
     * @param deviceStatsDO 实体
     */
    @Insert("INSERT INTO" +
            "  t_link_device_stats (full_short_url, date, cnt, device, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{deviceStatsDO.fullShortUrl}, #{deviceStatsDO.date}, #{deviceStatsDO.cnt}, #{deviceStatsDO.device}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt + #{deviceStatsDO.cnt}, update_time = NOW(); ")
    void shortLinkDeviceStats(@Param("deviceStatsDO") ShortLinkDeviceStatsDO deviceStatsDO);

    /**
     * 短链接访问设备监控信息获取
     * @param requestParam  请求参数
     * @return  详细信息
     */
    @Select("SELECT tlds.device, SUM(tlds.cnt) AS cnt " +
            "FROM t_link tl INNER JOIN t_link_device_stats tlds ON tl.full_short_url = tlds.full_short_url " +
            "WHERE tlds.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlds.date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY tlds.full_short_url, tl.gid, tlds.device;")
    List<ShortLinkDeviceStatsDO> listDeviceStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 短链接分组监控之访问设备监控信息
     * @param requestParam  请求入参
     * @return  响应
     */
    @Select("SELECT tlds.device, SUM(tlds.cnt) AS cnt " +
            "FROM t_link tl INNER JOIN t_link_device_stats tlds ON tl.full_short_url = tlds.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = '1' " +
            "   AND tlds.date BETWEEN #{requestParam.startDate} and #{requestParam.endDate} " +
            "GROUP BY tl.gid, tlds.device;")
    List<ShortLinkDeviceStatsDO> listDeviceStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
