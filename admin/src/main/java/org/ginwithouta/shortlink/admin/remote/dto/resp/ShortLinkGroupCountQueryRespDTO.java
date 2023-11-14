package org.ginwithouta.shortlink.admin.remote.dto.resp;

import lombok.Data;

/**
 * @Package : org.ginwithouta.shortlink.project.dto.resp
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周二
 * @Desc : 短链接分组查询返回
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接数量
     */
    private Integer shortLinkCount;
}
