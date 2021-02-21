package com.lv.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle
 * FileName: VehicleApplication
 * Author: lv
 * Date: 2021/2/4 21:06
 * Description: 描述信息
 */
@SpringBootApplication
public class VehicleApplication {
    public static void main(String[] args) {
        SpringApplication.run(VehicleApplication.class,args);

    }

    @Bean("keyProperties")
    public KeyProperties keyProperties(){
        return new KeyProperties();
    }
}
