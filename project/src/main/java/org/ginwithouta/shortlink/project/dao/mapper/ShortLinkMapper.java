package org.ginwithouta.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.req.StatsIncrementMapperDTO;

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
    @Update("UPDATE t_link SET " +
            "   total_pv = total_pv + #{requestParam.totalPv}, " +
            "   total_uv = total_uv + #{requestParam.totalUv}, " +
            "   total_uip = total_uip + #{requestParam.totalUip} " +
            "WHERE gid = #{requestParam.gid} AND full_short_url = #{requestParam.fullShortUrl} ")
    void incrementStats(@Param("requestParam") StatsIncrementMapperDTO requestParam);

    /**
     * 分页统计短链接
     */
    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);
}
