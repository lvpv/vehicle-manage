package com.lv.vehicle.exception;

import com.lv.vehicle.common.CommonCode;
import com.lv.vehicle.common.Result;
import com.lv.vehicle.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.exception
 * FileName: VehicleExceptionHandler
 * Author: lv
 * Date: 2021/2/4 21:21
 * Description: 描述信息
 */
@RestControllerAdvice
@Slf4j
public class VehicleExceptionHandler {

    /**
     * 捕获并处理未知异常
     * @param exception 未知异常
     * @return 统一返回结果数据
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handlerRuntimeException(Exception exception){
        exception.printStackTrace();
        log.error(exception.getMessage());
        return Result.failed(CommonCode.SERVER_ERROR);
    }

    /**
     * 捕获并处理自定义异常
     * @param vehicleException 自定义异常
     * @return 统一返回结果数据
     */
    @ExceptionHandler(VehicleException.class)
    public Result<Object> handlerLineException(VehicleException vehicleException){
        log.error(vehicleException.getMessage());
        vehicleException.printStackTrace();
        ResultCode resultCode = vehicleException.getResultCode();
        if (resultCode != null){
            return Result.failed(resultCode);
        }
        return Result.failed();
    }
}
