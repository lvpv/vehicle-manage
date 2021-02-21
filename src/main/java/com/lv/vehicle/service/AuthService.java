package com.lv.vehicle.service;

import com.lv.vehicle.security.vo.AuthData;

public interface AuthService {

    String login(AuthData authData);

    String loginByCode(AuthData code);
}
