package org.ginwithouta.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 分页查询回收站请求入参DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecycleBinPageReqDTO extends Page {

    /**
     * 分组标识列表
     */
    private List<String> gidList;
}
