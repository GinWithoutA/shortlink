package org.ginwithouta.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;
import org.ginwithouta.shortlink.project.dto.req.RecycleBinSaveReqDTO;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 回收站管理服务层接口
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 回收站保存
     * @param requestParam 请求参数
     */
    void save(RecycleBinSaveReqDTO requestParam);
}
