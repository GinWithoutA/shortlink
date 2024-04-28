package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dto.biz.StatsIncrementMapperDTO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;

/**
 * @Package : org.ginwithouta.shortlink.project.dao.mapper
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接持久层
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {
    /**
     * 短链接访问统计自增
     */
    void incrementStats(StatsIncrementMapperDTO requestParam);

    /**
     * 分页统计短链接
     */
    IPage<ShortLinkDO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 分页统计回收站短链接
     */
    IPage<ShortLinkDO> pageRecycleBinShortLink(RecycleBinPageReqDTO requestParam);
}
