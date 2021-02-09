package com.lv.vehicle.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.base
 * FileName: BaseEntity
 * Author: lv
 * Date: 2021/2/6 15:12
 * Description: 数据库实体类共有字段父类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseEntity implements Serializable {

    //是否删除(0：未删除，1：已删除)
    @TableField("del_flag")
    @TableLogic(value = "0", delval = "1")
    private Boolean delFlag;

    // 创建时间
    @TableField("create_time")
    private Date createTime;

    //创建人ID
    @TableField("create_user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUserId;

    // 创建人姓名
    @TableField("create_user_name")
    private String createUserName;

    // 更新时间
    @TableField("update_time")
    private Date updateTime;

    //更新人ID
    @TableField("update_user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUserId;

    //更新人姓名
    @TableField("update_user_name")
    private String updateUserName;
}