package com.lv.vehicle.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthUser implements Serializable {

    private Long userId;

    private String username;

    private String dingId;

    private String realName;

    private Collection<? extends GrantedAuthority> authorities;
}
