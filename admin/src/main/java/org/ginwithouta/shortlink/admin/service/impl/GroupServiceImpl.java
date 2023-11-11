package org.ginwithouta.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.admin.dao.entity.GroupDO;
import org.ginwithouta.shortlink.admin.dao.mapper.GroupMapper;
import org.ginwithouta.shortlink.admin.service.GroupService;
import org.springframework.stereotype.Service;

/**
 * @Package : org.ginwithouta.shortlink.admin.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
}
