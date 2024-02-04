package org.ginwithouta.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 短链接地区访问响应 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsLocaleCNRespDTO {

    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 访问地区
     */
    private String locale;

    /**
     * 占比
     */
    private Double ratio;
}
