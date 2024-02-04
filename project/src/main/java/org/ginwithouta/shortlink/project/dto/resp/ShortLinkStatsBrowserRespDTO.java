package org.ginwithouta.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2023/12/3
 * 短链接浏览器监控响应参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsBrowserRespDTO {

    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 浏览器标识
     */
    private String browser;

    /**
     * 占比
     */
    private Double ratio;
}
