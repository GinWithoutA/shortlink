package org.ginwithouta.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ginwithouta.shortlink.admin.dao.entity.UserDO;
import org.ginwithouta.shortlink.admin.dto.req.UserLoginReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.ginwithouta.shortlink.admin.dto.resp.UserLoginRespDTO;
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

    /**
     * 查询用户名是否存在
     * @param username  用户名
     * @return          存在返回 true 不存在返回 false
     */
    Boolean hasUsername(String username);

    /**
     * 用户注册
     * @param requestParam 注册用户请求参数
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名修改用户信息
     * @param requestParam 用户修改请求参数
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     * @param requestParam 用户登录请求参数
     * @return 用户登录返回参数
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     * @param username 用户名
     * @param token 用户登录 Token
     * @return 用户是否登录标识
     */
    Boolean checkLogin(String username, String token);

    /**
     * 用户退出登录
     * @param username 用户名
     * @param token 用户登录 Token
     */
    void logout(String username, String token);
}
