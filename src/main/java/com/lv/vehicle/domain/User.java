package com.lv.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lv.vehicle.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("sys_user")
@JsonIgnoreProperties(value = {"password"})
public class User extends BaseEntity implements UserDetails {

    @TableId(value="user_id",type = IdType.ASSIGN_ID)
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("ding_id")
    private String dingId;

    @TableField("password")
    private String password;

    @TableField("real_name")
    private String realName;

    @TableField("organization_id")
    private Long organizationId;

    @TableField("nickname")
    private String nickname;

    @TableField("signature")
    private String signature;

    @TableField("account_expired")
    private Boolean accountExpired;

    @TableField("account_locked")
    private Boolean accountLocked;

    @TableField("credentials_expired")
    private Boolean credentialsExpired;

//    @TableField(exist = false)
//    private List<Role> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return AuthorityUtils.commaSeparatedStringToAuthorityList("admin,");
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

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
        return !this.getDelFlag();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDingId() {
        return dingId;
    }

    public void setDingId(String dingId) {
        this.dingId = dingId;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
