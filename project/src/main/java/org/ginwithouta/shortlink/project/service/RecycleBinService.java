package org.ginwithouta.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 回收站管理服务层接口
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 将短链接移动到回收站中
     * @param requestParam 请求参数
     */
    void save(RecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询回收站中的短链接
     * @param requestParam 请求参数
     * @return 短链接分页返回
     */
    IPage<ShortLinkPageRespDTO> pageRecycleBinList(ShortLinkPageReqDTO requestParam);
}
