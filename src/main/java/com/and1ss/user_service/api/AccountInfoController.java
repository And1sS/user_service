package com.and1ss.user_service.api;

import com.and1ss.user_service.api.dto.AccountInfoRetrievalDTO;
import com.and1ss.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AccountInfoController {
    private final UserService userService;

    @Autowired
    public AccountInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    private AccountInfoRetrievalDTO getUserById(@PathVariable("id") UUID userId) {
        return AccountInfoRetrievalDTO.fromAccountInfo(userService.findUserById(userId));
    }
}
