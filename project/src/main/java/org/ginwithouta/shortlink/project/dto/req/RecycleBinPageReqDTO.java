package org.ginwithouta.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ginwithouta.shortlink.project.dao.entity.ShortLinkDO;

import java.util.List;

/**
 * @author Ginwithouta
 * Generate at 2023/11/26
 * 分页查询回收站请求入参DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecycleBinPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识列表
     */
    private List<String> gidList;
}
