package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkAccessLogsDO;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsDO;
import org.ginwithouta.shortlink.project.dto.biz.UvTypeGroupMapperDTO;
import org.ginwithouta.shortlink.project.dto.biz.UvTypeMapperDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

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
     * 根据短链接获取指定日期内高频访问 IP（TOP 5）
     * @param requestParam 短链接请求入参
     * @return  高频访问 IP
     */
    @Select("SELECT tlal.ip, COUNT(tlal.ip) AS count FROM t_link tl INNER JOIN t_link_access_logs tlal " +
            "ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "   tlal.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlal.create_time BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tlal.full_short_url, tl.gid, tlal.ip " +
            "ORDER BY count DESC " +
            "LIMIT 5;")
    List<HashMap<String, Object>> listTopIpByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据短链接分组获取指定日期内高频访问 IP （TOP 5）
     * @param requestParam 短链接请求入参
     * @return  高频访问 IP
     */
    @Select("SELECT ip, COUNT(ip) AS count FROM t_link tl INNER JOIN t_link_access_logs tlal " +
            "ON tl.full_short_url = tlal.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = '1' " +
            "   AND tlal.create_time BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tl.gid, tlal.ip ORDER BY count DESC LIMIT 5;")
    List<HashMap<String, Object>> listTopIpStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 根据短链接获取指定日期内的访客类型
     */
    @Select("SELECT SUM(old_user) AS oldUserCnt, SUM(new_user) AS newUserCnt FROM ( " +
            "   SELECT " +
            "       CASE WHEN COUNT(DISTINCT DATE(tlal.create_time)) > 1 THEN 1 ELSE 0 END AS old_user, " +
            "       CASE WHEN COUNT(DISTINCT DATE(tlal.create_time)) = 1 " +
            "               AND MAX(tlal.create_time) >= #{requestParam.startDate} " +
            "               AND MAX(tlal.create_time) <= #{requestParam.endDate} " +
            "            THEN 1 ELSE 0 " +
            "       END AS new_user " +
            "   FROM t_link tl INNER JOIN t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "   WHERE tlal.full_short_url = #{requestParam.fullShortUrl} " +
            "       AND tl.gid = #{requestParam.gid} " +
            "       AND tl.enable_status = #{requestParam.enableStatus} " +
            "   GROUP BY tlal.user ) " +
            "AS user_counts;")
    HashMap<String, Object> findUvTypeCntByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 查询用户列表中哪些是老用户，哪些是新用户
     */
    @Select("<script> " +
            "   SELECT tlal.user, " +
            "       CASE WHEN MIN(tlal.create_time) BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} THEN '新访客' ELSE '老访客' END AS uvType " +
            "   FROM t_link tl INNER JOIN t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "   WHERE tlal.full_short_url = #{requestParam.fullShortUrl} " +
            "       AND tl.gid = #{requestParam.gid} " +
            "       AND tl.enable_status = #{requestParam.enableStatus} " +
            "       AND tlal.user IN " +
            "           <foreach item='item' index='index' collection='userAccessLogsList' open='(' separator=',' close=')'> " +
            "               #{item} " +
            "           </foreach> " +
            "   GROUP BY tlal.user; " +
            "</script> ")
    List<Map<String, Object>> selectUvTypeByUsers(@Param("requestParam") UvTypeMapperDTO requestParam, @Param("userAccessLogsList") List<String> userAccessLogsList);

    /**
     * 查询分组用户列表中哪些是老用户，哪些是新用户
     */
    @Select("<script> " +
            "   SELECT tlal.user, " +
            "       CASE WHEN MIN(tlal.create_time) BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} THEN '新访客' ELSE '老访客' END AS uvType " +
            "   FROM t_link tl INNER JOIN t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "   WHERE tl.gid = #{requestParam.gid} AND tl.enable_status = '1' AND tlal.user IN " +
            "           <foreach item='item' index='index' collection='userAccessLogsList' open='(' separator=',' close=')'> " +
            "               #{item} " +
            "           </foreach> " +
            "   GROUP BY tlal.user; " +
            "</script> ")
    List<Map<String, Object>> selectGroupUvTypeByUsers(@Param("requestParam") UvTypeGroupMapperDTO bean, @Param("userAccessLogsList") List<String> userAccessLogsList);

    /**
     * 根据短链接获取指定日期内基础数据
     */
    @Select("SELECT COUNT(tlal.user) AS pv, COUNT(DISTINCT tlal.user) AS uv, COUNT(DISTINCT tlal.ip) AS uip " +
            "FROM t_link tl INNER JOIN t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE tlal.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlal.create_time BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tlal.full_short_url, tl.gid;")
    ShortLinkStatsDO getPvUvUipByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据分组获取指定日期内所有短链接基础数据
     */
    @Select("SELECT COUNT(tlal.user) AS pv, COUNT(DISTINCT tlal.user) AS uv, COUNT(DISTINCT tlal.ip) AS uip " +
            "FROM t_link tl INNER JOIN t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} AND tl.enable_status = '1' " +
            "   AND tlal.create_time BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tl.gid;")
    ShortLinkStatsDO getPvUvUipByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);

    @Select("SELECT tlal.* FROM t_link tl INNER JOIN t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE tl.gid = #{param.gid} AND tl.enable_status = '1' AND tlal.create_time BETWEEN #{param.startDate} AND #{param.endDate} " +
            "ORDER BY tlal.create_time DESC")
    IPage<ShortLinkAccessLogsDO> selectGroupPage(@Param("param")ShortLinkGroupStatsAccessRecordReqDTO requestParam);
}
