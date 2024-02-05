package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkAccessLogsDO;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.UvTypeMapperDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据短链接获取指定日期内的访客类型
     */
    @Select("SELECT SUM(old_user) AS oldUserCnt, SUM(new_user) AS newUserCnt FROM ( " +
            "   SELECT " +
            "       CASE WHEN COUNT(DISTINCT DATE(create_time)) > 1 THEN 1 ELSE 0 END AS old_user, " +
            "       CASE WHEN COUNT(DISTINCT DATE(create_time)) = 1 AND MAX(create_time) >= #{requestParam.startDate} AND MAX(create_time) <= #{requestParam.endDate} THEN 1 ELSE 0 END AS new_user " +
            "   FROM t_access_logs WHERE full_short_url = #{requestParam.fullShortUrl} AND gid = #{requestParam.gid} " +
            "   GROUP BY user ) " +
            "AS user_counts;")
    HashMap<String, Object> findUvTypeCntByShortLink(ShortLinkStatsReqDTO requestParam);

    /**
     * 查询用户列表中哪些是老用户，哪些是新用户
     */
    @Select("<script> " +
            "   SELECT user, " +
            "       CASE WHEN MIN(create_time) BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} THEN '新访客' ELSE '老访客' END AS uvType " +
            "   FROM t_access_logs WHERE" +
            "       full_short_url = #{requestParam.fullShortUrl} " +
            "       AND gid = #{requestParam.gid} AND user IN " +
            "           <foreach item=\"item\" index=\"index\" collection=\"userAccessLogsList\" open=\"(\" separator=\",\" close=\")\"> " +
            "               #{item}" +
            "           </foreach> " +
            "   GROUP BY user; " +
            "</script> ")
    List<Map<String, Object>> selectUvTypeByUsers(@Param("requestParam") UvTypeMapperDTO requestParam, @Param("userAccessLogsList") List<String> userAccessLogsList);

    /**
     * 根据短链接获取指定日期内基础数据
     */
    @Select("SELECT COUNT(user) AS pv, COUNT(DISTINCT user) AS uv, COUNT(DISTINCT ip) AS uip FROM t_access_logs " +
            "WHERE " +
            "   full_short_url = #{requestParam.fullShortUrl} " +
            "   AND gid = #{requestParam.gid} " +
            "   AND create_time BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid;")
    ShortLinkStatsDO getPvUvUipByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);
}
