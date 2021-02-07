package com.lv.vehicle.security.vo;

import lombok.*;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.vo
 * FileName: AuthData
 * Author: lv
 * Date: 2021/2/7 17:56
 * Description: 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class AuthData extends ImageResult{

    private String username;

    private String password;

    private String code;
}
