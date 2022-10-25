package com.geielpeguero.userapi.service;

import com.geielpeguero.userapi.authorization.JwtTokenUtil;
import com.geielpeguero.userapi.exception.UserAPIException;
import com.geielpeguero.userapi.model.ExceptionMessage;
import com.geielpeguero.userapi.model.User;
import com.geielpeguero.userapi.model.dto.UserDto;
import com.geielpeguero.userapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;


    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User saveUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAPIException(ExceptionMessage.exist);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        final String token = jwtTokenUtil.generateToken(userDto.getEmail());


        return userRepository.save(new ModelMapper().map(userDto, User.class));
    }

}
