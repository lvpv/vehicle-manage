package com.lv.vehicle.base;


import com.lv.vehicle.common.ResultCode;

public class BaseException extends RuntimeException {

    private BaseException(){}

    public ResultCode resultCode;

    public BaseException(String message){
        super(message);
    }

    public BaseException(ResultCode resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
