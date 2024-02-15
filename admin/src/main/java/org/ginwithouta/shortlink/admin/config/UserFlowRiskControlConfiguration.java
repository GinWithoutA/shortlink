package org.ginwithouta.shortlink.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ginwithouta
 * Generate at 2024/2/14
 * 用户操作流量风控配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "short-link.flow-limit")
public class UserFlowRiskControlConfiguration {
    /**
     * 是否开启流量控制
     */
    private Boolean enable;

    /**
     * 需要监测的时间窗口大小
     */
    private String timeWindow;

    /**
     * 最大请求次数
     */
    private Long maxAccessCount;
}
