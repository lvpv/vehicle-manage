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
 * Description: 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseEntity implements Serializable {

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUserId;

    @TableField("create_user_name")
    private String createUserName;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUserId;

    @TableField("update_user_name")
    private String updateUserName;

    @TableField("del_flag")
    @TableLogic(value = "0", delval = "1")
    private Boolean delFlag;
}