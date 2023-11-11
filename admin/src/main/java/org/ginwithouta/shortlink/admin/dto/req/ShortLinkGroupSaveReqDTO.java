package org.ginwithouta.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Package : org.ginwithouta.shortlink.admin.dto.req
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组创建参数
 */
@Data
public class ShortLinkGroupSaveReqDTO {
    /**
     * 分组名称
     */
    private String name;
}
