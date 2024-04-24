package org.ginwithouta.shortlink.admin.remote.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.admin.biz.user.UserContext;
import org.ginwithouta.shortlink.admin.common.convention.exception.ServiceException;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.dao.entity.GroupDO;
import org.ginwithouta.shortlink.admin.dao.mapper.GroupMapper;
import org.ginwithouta.shortlink.admin.remote.dto.req.RecycleBinPageReqDTO;
import org.ginwithouta.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.ginwithouta.shortlink.admin.remote.service.RecycleBinService;
import org.ginwithouta.shortlink.admin.remote.service.ShortLinkFeignRemoteService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ginwithouta.shortlink.admin.common.enums.UserErrorCodeEnums.USER_INFO_GROUP_EMPTY;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final ShortLinkFeignRemoteService recycleBinFeignRemoteService;
    private final GroupMapper groupMapper;

    @Override
    public Result<Page<ShortLinkPageRespDTO>> pageRecycleBinList(RecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException(USER_INFO_GROUP_EMPTY);
        }
        Map<String, Object> requestMap = new HashMap<>(3);
        requestMap.put("gidList", groupDOList.stream().map(GroupDO::getGid).toList());
        requestMap.put("current", requestParam.getCurrent());
        requestMap.put("size", requestParam.getSize());
        return recycleBinFeignRemoteService.pageRecycleBinList(groupDOList.stream().map(GroupDO::getGid).toList(), requestParam.getCurrent(), requestParam.getSize());
    }
}
