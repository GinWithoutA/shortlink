package org.ginwithouta.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkTodayStatsDO;

/**
 * @author Ginwithouta
 * Generate at 2024/2/13
 * 短链接每日基础信息监控服务层
 */
public interface ShortLinkStatsTodayService extends IService<ShortLinkTodayStatsDO> {
    /**
     * 记录短链接当日访问数据
     * @param statsTodayDO 入参
     */
    void shortLinkTodayStats(ShortLinkTodayStatsDO statsTodayDO);
}
