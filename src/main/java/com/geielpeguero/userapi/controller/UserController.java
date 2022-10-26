package com.geielpeguero.userapi.controller;

import com.geielpeguero.userapi.model.User;
import com.geielpeguero.userapi.model.dto.UserDto;
import com.geielpeguero.userapi.model.response.UserRegisterResponse;
import com.geielpeguero.userapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> createUser(@RequestBody UserDto userDto) {
        UserRegisterResponse response = userService.saveUser(userDto);

        return ResponseEntity.ok(response);
    }

}
