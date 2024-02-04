package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkStatsBrowserDO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/29
 * 短链接浏览器统计访问持久层
 */
public interface ShortLinkStatsBrowserMapper extends BaseMapper<ShortLinkStatsBrowserDO> {

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
