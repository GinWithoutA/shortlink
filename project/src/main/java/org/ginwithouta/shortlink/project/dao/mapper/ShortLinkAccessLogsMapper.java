package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkAccessLogsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接访问日志监控持久层
 */
public interface ShortLinkAccessLogsMapper extends BaseMapper<ShortLinkAccessLogsDO> {

    /**
     * 根据短链接获取指定日期内高频访问 IP （TOP 5）
     * @param requestParam 短链接请求入参
     * @return  高频访问 IP
     */
    @Select("SELECT ip, COUNT(ip) AS count FROM t_access_logs " +
            "WHERE " +
            "   full_short_url = #{requestParam.fullShortUrl} " +
            "   AND gid = #{requestParam.gid} " +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, ip " +
            "ORDER BY count DESC " +
            "LIMIT 5;")
    List<HashMap<String, Object>> listTopIpByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);
}
