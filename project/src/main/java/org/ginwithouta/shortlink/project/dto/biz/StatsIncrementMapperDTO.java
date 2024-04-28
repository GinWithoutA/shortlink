package org.ginwithouta.shortlink.project.dto.biz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ginwithouta
 * Generate at 2024/2/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsIncrementMapperDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 历史 PV
     */
    private Integer totalPv;

    /**
     * 历史 UV
     */
    private Integer totalUv;

    /**
     * 历史 UIP
     */
    private Integer totalUip;
}
