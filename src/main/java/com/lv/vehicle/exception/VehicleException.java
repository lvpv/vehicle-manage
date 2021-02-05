package com.lv.vehicle.exception;

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
public class VehicleException extends RuntimeException{
    private ResultCode resultCode;

    public VehicleException(){
        super();
    }

    public VehicleException(String message){
        super(message);
    }

    public VehicleException(ResultCode resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
