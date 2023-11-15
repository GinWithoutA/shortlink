package org.ginwithouta.shortlink.project.controller;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.ginwithouta.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package : org.ginwithouta.shortlink.project.controller
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc :
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkRedirectController {

    private final ShortLinkService shortLinkService;

    @GetMapping(value = "/{short-uri}")
    public void redirectUrl(@PathVariable(name = "short-uri") String shortUri, ServletRequest request, ServletResponse response) {
        shortLinkService.redirectUrl(shortUri, request, response);
    }
}
