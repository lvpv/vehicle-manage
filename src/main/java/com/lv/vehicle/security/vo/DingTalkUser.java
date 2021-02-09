package com.lv.vehicle.security.vo;

import com.dingtalk.api.response.OapiV2UserGetResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DingTalkUser implements Serializable {

    private String userid;
    private String unionid;
    private String name;
    private String avatar;
    private String mobile;
    private String job_number; // 工号
    private String title; //职位
    private String email;
    private List<Long> dept_id_list;
    private Long hired_date; // 入职时间
    private Boolean admin;
    private Boolean boss;
    private List<OapiV2UserGetResponse.DeptLeader> leader_in_dept;
    private List<OapiV2UserGetResponse.UserRole> role_list;

}
