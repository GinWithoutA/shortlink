package org.ginwithouta.shortlink.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Package : org.ginwithouta.shortlink.project.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周四
 * @Desc : 短链接不存在跳转控制器
 */
@Controller
public class ShortLinkNotFoundController {
    /**
     * 短链接跳转不存在页面
     */
    @RequestMapping(value = "/page/notfound")
    public String notFound() {
        return "not-found";
    }
}
