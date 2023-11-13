package org.ginwithouta.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * @Package : org.ginwithouta.shortlink.project.service
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接接口
     * @param requestParam 创建短链接入参
     * @return 短链接
     */
    ShortLinkCreateRespDTO createShorLink(ShortLinkCreateReqDTO requestParam);
}
