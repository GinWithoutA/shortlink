package org.ginwithouta.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Package : org.ginwithouta.shortlink.admin.dto.req
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组返回实体
 */
@Data
public class ShortLinkGroupRespDTO {
    /**
     * 短链接分组标识
     */
    private String gid;

    /**
     * 短链接分组名称
     */
    private String name;

    /**
     * 创建短链接分组的创建人
     */
    private String username;

    /**
     * 短链接分组排序
     */
    private Integer sortOrder;
}
