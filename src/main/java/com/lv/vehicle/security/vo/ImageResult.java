package com.lv.vehicle.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class ImageResult implements Serializable {

    private String codeId;

    private String content;

    private String image;


}
