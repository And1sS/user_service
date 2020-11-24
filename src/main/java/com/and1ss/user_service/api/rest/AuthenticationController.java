package com.and1ss.user_service.api.rest;

import com.and1ss.user_service.api.rest.dto.AccessTokenRetrievalDTO;
import com.and1ss.user_service.api.rest.dto.AccountInfoRetrievalDTO;
import com.and1ss.user_service.service.model.LoginInfo;
import com.and1ss.user_service.service.model.RegisterInfo;
import com.and1ss.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService service) {
        userService = service;
    }

    @PostMapping("/register")
    private AccountInfoRetrievalDTO
    registerUser(@RequestBody RegisterInfo registerInfo) {
        return AccountInfoRetrievalDTO
                .fromAccountInfo(userService.registerUser(registerInfo));
    }

    @GetMapping("/login")
    private AccessTokenRetrievalDTO
    loginUser(@RequestBody LoginInfo credentials) {
        return new AccessTokenRetrievalDTO(userService.loginUser(credentials));
    }

    @GetMapping("/identify")
    private AccountInfoRetrievalDTO identifyByToken(@RequestBody String token) {
        return AccountInfoRetrievalDTO.fromAccountInfo(
                userService.authorizeUserByAccessToken(token));
    }
}