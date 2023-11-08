package org.ginwithouta.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.admin.dao.entity.UserDO;
import org.ginwithouta.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @Package : org.ginwithouta.shortlink.admin.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc :
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     * @param username  用户名
     * @return  用户返回实体
     */
    UserRespDTO getUserByUsername(String username);
}
