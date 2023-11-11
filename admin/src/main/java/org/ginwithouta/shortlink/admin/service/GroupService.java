package org.ginwithouta.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.admin.dao.entity.GroupDO;
import org.ginwithouta.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.ginwithouta.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

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

    /**
     * 查询当前用户所创建的短链接分组集合
     * @return 短链接分组
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * 修改短链接分组名
     * @param requestParam 修改入参
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);
}
