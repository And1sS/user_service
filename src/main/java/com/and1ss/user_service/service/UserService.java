package com.and1ss.user_service.service;

import com.and1ss.user_service.service.model.AccessToken;
import com.and1ss.user_service.service.model.AccountInfo;
import com.and1ss.user_service.service.model.LoginInfo;
import com.and1ss.user_service.service.model.RegisterInfo;

import java.util.List;
import java.util.UUID;

public interface UserService {
        AccountInfo registerUser(RegisterInfo registerInfo);
        AccessToken loginUser(LoginInfo credentials);
        AccountInfo authorizeUserByAccessToken(String accessToken);
        AccountInfo findUserByLogin(String login);
        AccountInfo findUserById(UUID id);
        List<AccountInfo> findUsersByListOfIds(List<UUID> ids);
}
