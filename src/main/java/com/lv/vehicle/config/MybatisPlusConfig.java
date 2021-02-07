package com.lv.vehicle.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.config
 * FileName: MybatisPlusConfig
 * Author: lv
 * Date: 2021/2/6 20:56
 * Description: 描述信息
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.lv.vehicle.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
