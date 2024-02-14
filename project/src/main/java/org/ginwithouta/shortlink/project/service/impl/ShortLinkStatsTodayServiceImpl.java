package org.ginwithouta.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkTodayStatsDO;
import org.ginwithouta.shortlink.project.dao.mapper.ShortLinkTodayStatsMapper;
import org.ginwithouta.shortlink.project.service.ShortLinkStatsTodayService;
import org.springframework.stereotype.Service;

/**
 * @author Ginwithouta
 * Generate at 2024/2/13
 * 短链接每日数据监控服务实现层
 */
@Service
public class ShortLinkStatsTodayServiceImpl extends ServiceImpl<ShortLinkTodayStatsMapper, ShortLinkTodayStatsDO> implements ShortLinkStatsTodayService {
    @Override
    public void shortLinkTodayStats(ShortLinkTodayStatsDO statsTodayDO) {
        baseMapper.shortLinkTodayStats(statsTodayDO);
    }
}
