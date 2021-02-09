package com.lv.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lv.vehicle.base.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.domain
 * FileName: User
 * Author: lv
 * Date: 2021/2/4 23:01
 * Description: 描述信息
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@JsonIgnoreProperties(value = {"password"})
public class User extends BaseEntity implements UserDetails {

    @TableId(value="user_id",type = IdType.ASSIGN_ID)
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("real_name")
    private String realName;

    @TableField("password")
    private String password;

    @TableField("ding_id")
    private String dingId;

    @TableField("union_id")
    private String unionId;

    @TableField("organization_id")
    private Long organizationId;

    @TableField("avatar")
    private String avatar;

    @TableField("mobile")
    private String mobile;

    @TableField("sys_admin")
    private Boolean sysAdmin;

    @TableField("account_expired")
    private Boolean accountExpired;

    @TableField("account_locked")
    private Boolean accountLocked;

    @TableField("credentials_expired")
    private Boolean credentialsExpired;

    @TableField(exist = false)
    private  Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !super.getDelFlag();
    }
}
