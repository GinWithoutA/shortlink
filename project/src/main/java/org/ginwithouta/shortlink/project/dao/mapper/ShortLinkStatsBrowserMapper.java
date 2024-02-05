package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsBrowserDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接浏览器统计访问持久层
 */
public interface ShortLinkStatsBrowserMapper extends BaseMapper<ShortLinkStatsBrowserDO> {

    /**
     * 短链接监控之获取短链接的浏览器访问记录
     */
    @Select("SELECT browser, SUM(cnt) AS count FROM t_browser_statistics WHERE " +
            "   full_short_url = #{requestParam.fullShortUrl} " +
            "   AND gid = #{requestParam.gid} " +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY full_short_url, gid, browser;")
    List<HashMap<String, Object>> listBrowserStatsByShortLink(@Param("requestParam") ShortLinkStatsReqDTO requestParam);

    /**
     * 短链接分组监控之获取短链接分组的浏览器访问记录
     */
    @Select("SELECT browser, SUM(cnt) AS count FROM t_browser_statistics WHERE " +
            "   gid = #{requestParam.gid} " +
            "   AND date BETWEEN #{requestParam.startDate} AND #{requestParam.endDate} " +
            "GROUP BY gid, browser;")
    List<HashMap<String, Object>> listBrowserStatsByGroup(@Param("requestParam") ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 记录浏览器访问监控数据
     * @param browserStatisticsDO 短链接操作系统统计入参
     */
    @Insert("INSERT INTO" +
            "  t_browser_statistics (full_short_url, gid, date, cnt, browser, create_time, update_time, del_flag) " +
            "VALUES " +
            "  (#{browserStatisticsDO.fullShortUrl}, #{browserStatisticsDO.gid}, #{browserStatisticsDO.date}, #{browserStatisticsDO.cnt}, " +
            "   #{browserStatisticsDO.browser}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE" +
            "  cnt = cnt + #{browserStatisticsDO.cnt}, update_time = NOW();")
    void shortLinkBrowserStatistics(@Param("browserStatisticsDO") ShortLinkStatsBrowserDO browserStatisticsDO);
}
