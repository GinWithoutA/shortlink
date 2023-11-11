package org.ginwithouta.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Package : org.ginwithouta.shortlink.admin.dao.entity
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接分组实体
 */
@Data
@TableName(value = "t_group")
public class GroupDO {
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标识位： 0 未删除； 1 已删除
     */
    private Integer delFlag;
}
