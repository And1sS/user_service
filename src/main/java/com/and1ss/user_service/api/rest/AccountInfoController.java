package com.and1ss.user_service.api.rest;

import com.and1ss.user_service.api.rest.dto.AccountInfoRetrievalDTO;
import com.and1ss.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping("/list")
    private List<AccountInfoRetrievalDTO> getUsersByListOfIds(@RequestBody List<UUID> ids) {
        return userService.findUsersByListOfIds(ids)
                .stream()
                .map(AccountInfoRetrievalDTO::fromAccountInfo)
                .collect(Collectors.toList());
    }
}
