package org.ginwithouta.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Package : org.ginwithouta.shortlink.project.dto.req
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周一
 * @Desc : 短链接分页请求参数分组标识
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShortLinkPageReqDTO extends Page {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 排序标识（是按照 IP UV PV 的今日还是累积排序）
     */
    private String orderTag;
}
