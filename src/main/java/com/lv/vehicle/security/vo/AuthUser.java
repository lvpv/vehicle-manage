package com.lv.vehicle.security.vo;

import com.lv.vehicle.domain.User;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthUser extends User implements Serializable {

    /**
     * token
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;



}
