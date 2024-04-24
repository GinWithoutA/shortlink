package org.ginwithouta.shortlink.gateway.config;

import lombok.Data;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2024/4/24
 * 白名单配置类
 */
@Data
public class Config {
    /**
     * 白名单前置路径
     */
    private List<String> whitePathList;
}
