package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkBrowserStatsDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接浏览器统计访问持久层
 */
public interface ShortLinkBrowserStatsMapper extends BaseMapper<ShortLinkBrowserStatsDO> {

    /**
     * 记录浏览器访问监控数据
     * @param browserStatsDO 短链接操作系统统计入参
     */
    @Insert("INSERT INTO" +
            "  t_link_browser_stats (full_short_url, date, cnt, browser, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{browserStatsDO.fullShortUrl}, #{browserStatsDO.date}, #{browserStatsDO.cnt}, #{browserStatsDO.browser}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt + #{browserStatsDO.cnt}, update_time = NOW(); ")
    void shortLinkStatsBrowser(@Param("browserStatsDO") ShortLinkBrowserStatsDO browserStatsDO);

    /**
     * 短链接监控之获取短链接的浏览器访问记录
     */
    @Select("SELECT tlbs.browser, SUM(tlbs.cnt) AS count " +
            "FROM t_link tl INNER JOIN t_link_browser_stats tlbs ON tl.full_short_url = tlbs.full_short_url " +
            "WHERE tlbs.full_short_url = #{requestParam.fullShortUrl} " +
            "   AND tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = #{requestParam.enableStatus} " +
            "   AND tlbs.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tlbs.full_short_url, tl.gid, tlbs.browser; ")
    List<HashMap<String, Object>> listBrowserStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 短链接分组监控之获取短链接分组的浏览器访问记录
     */
    @Select("SELECT tlbs.browser, SUM(tlbs.cnt) AS count " +
            "FROM t_link tl INNER JOIN t_link_browser_stats tlbs ON tl.full_short_url = tlbs.full_short_url " +
            "WHERE tl.gid = #{requestParam.gid} " +
            "   AND tl.enable_status = '1' " +
            "   AND tlbs.date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY tl.gid, tlbs.browser; ")
    List<HashMap<String, Object>> listBrowserStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);
}
