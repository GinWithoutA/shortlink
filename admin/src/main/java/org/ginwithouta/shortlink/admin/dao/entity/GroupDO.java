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
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {
    /**
     * 短链接分组标识
     */
    private String gid;

    /**
     * 短链接名称
     */
    private String name;

    /**
     * 创建分组的创建人
     */
    private String username;
}
