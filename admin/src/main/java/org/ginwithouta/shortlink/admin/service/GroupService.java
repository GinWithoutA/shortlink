package org.ginwithouta.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.admin.dao.entity.GroupDO;

/**
 * @Package : org.ginwithouta.shortlink.admin.service
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     * @param groupName 短链接分组名称
     */
    void saveGroup(String groupName);
}
