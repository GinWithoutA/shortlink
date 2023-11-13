package org.ginwithouta.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package : org.ginwithouta.shortlink.project.dto.req
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接创建请求返回对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkCreateRespDTO {

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接（短链接跳转的目标链接）
     */
    private String originUrl;

    /**
     * 分组标识
     */
    private String gid;

}
