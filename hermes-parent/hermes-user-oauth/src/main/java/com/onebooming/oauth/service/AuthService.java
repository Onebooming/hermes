package com.onebooming.oauth.service;

import com.onebooming.oauth.util.AuthToken;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-15 18:20
 * @ApiNote
 */
public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
