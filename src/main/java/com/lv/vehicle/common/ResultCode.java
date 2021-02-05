package com.lv.vehicle.common;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.common
 * FileName: ResultCode
 * Author: lv
 * Date: 2021/2/4 21:12
 * Description: 描述信息
 */
public interface ResultCode {

    int code();

    boolean success();

    String message();
}
