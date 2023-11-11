package org.ginwithouta.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ginwithouta.shortlink.admin.common.database.BaseDO;

/**
 * @Package : org.ginwithouta.shortlink.admin.dao.entity
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_group")
@EqualsAndHashCode(callSuper = true)
public class GroupDO extends BaseDO {
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
