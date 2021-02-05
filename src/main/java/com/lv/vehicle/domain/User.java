package com.lv.vehicle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
public class User {

    private String name;

    private Integer age;
}
