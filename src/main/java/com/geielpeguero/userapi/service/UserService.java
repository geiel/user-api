package com.geielpeguero.userapi.service;

import com.geielpeguero.userapi.authorization.JwtTokenUtil;
import com.geielpeguero.userapi.exception.UserAPIException;
import com.geielpeguero.userapi.model.ExceptionMessage;
import com.geielpeguero.userapi.model.User;
import com.geielpeguero.userapi.model.dto.UserDto;
import com.geielpeguero.userapi.model.response.UserRegisterResponse;
import com.geielpeguero.userapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponse saveUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAPIException(ExceptionMessage.exist);
        }

        Date date = new Date();
        User user = new ModelMapper().map(userDto, User.class);
        user.setActive(true);
        user.setCreated(date);
        user.setModified(date);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        final String token = jwtTokenUtil.generateToken(userDto.getEmail());

        return new UserRegisterResponse(user.getId().toString(), date, date, date, token, true);
    }

}
