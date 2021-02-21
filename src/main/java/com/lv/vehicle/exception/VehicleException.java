package com.lv.vehicle.exception;

import com.lv.vehicle.base.BaseException;
import com.lv.vehicle.common.ResultCode;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.exception
 * FileName: VehicleException
 * Author: lv
 * Date: 2021/2/4 21:20
 * Description: 描述信息
 */
public class VehicleException extends BaseException {


    public VehicleException(String message){
        super(message);
    }

    public VehicleException(ResultCode resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

}
