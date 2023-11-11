package org.ginwithouta.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Package : org.ginwithouta.shortlink.admin.dto.req
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组排序入参DTO
 */
@Data
public class ShortLinkGroupSortReqDTO {

    /**
     * 短链接分组标识
     */
    private String gid;

    /**
     * 排序标识
     */
    private Integer sortOrder;
}
