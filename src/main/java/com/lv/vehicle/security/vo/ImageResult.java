package com.lv.vehicle.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.vo
 * FileName: ImageResult
 * Author: lv
 * Date: 2021/2/7 17:29
 * Description: 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResult {

    private String uuid;

    private String image;

    private String content;
}
