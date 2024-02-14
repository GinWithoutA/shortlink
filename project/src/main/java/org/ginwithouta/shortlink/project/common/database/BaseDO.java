package org.ginwithouta.shortlink.project.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.database
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 数据库持久层对象基础属性
 */
@Data
public class BaseDO {

    /**
     *  主键
     */
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标识：0：未删除：1：已删除
     */
    @TableLogic
    private Integer delFlag;
}
