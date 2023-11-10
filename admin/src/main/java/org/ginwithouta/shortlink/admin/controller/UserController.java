package org.ginwithouta.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.admin.common.convention.result.Result;
import org.ginwithouta.shortlink.admin.common.convention.result.Results;
import org.ginwithouta.shortlink.admin.dto.req.UserLoginReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.ginwithouta.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.ginwithouta.shortlink.admin.dto.resp.UserActualRespDTO;
import org.ginwithouta.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.ginwithouta.shortlink.admin.dto.resp.UserRespDTO;
import org.ginwithouta.shortlink.admin.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @Package : org.ginwithouta.shortlink.admin.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查询用户信息（包含敏感信息）
     */
    @GetMapping(value = "/api/short/link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名查询用户信息（无脱敏信息）
     */
    @GetMapping(value = "/api/short/link/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户名是否存在
     */
    @GetMapping(value = "/api/short/link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     */
    @PostMapping(value = "/api/short/link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 用户信息修改
     */
    @PutMapping(value = "/api/short/link/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * 用户登录接口
     */
    @PostMapping(value = "/api/short/link/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        UserLoginRespDTO result = userService.login(requestParam);
        return Results.success(result);
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping(value = "/api/short/link/v1/user/check/login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }

    /**
     * 用户退出登录
     */
    @DeleteMapping(value = "/api/short/link/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }

}
