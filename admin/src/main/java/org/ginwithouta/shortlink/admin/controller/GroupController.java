package org.ginwithouta.shortlink.admin.controller;

import lombok.AllArgsConstructor;
import org.ginwithouta.shortlink.admin.service.GroupService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package : org.ginwithouta.shortlink.admin.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组控制层
 */
@RestController
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;
}
