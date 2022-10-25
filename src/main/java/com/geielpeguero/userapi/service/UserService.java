package com.geielpeguero.userapi.service;

import com.geielpeguero.userapi.exeption.UserAPIException;
import com.geielpeguero.userapi.model.ExceptionMessage;
import com.geielpeguero.userapi.model.User;
import com.geielpeguero.userapi.model.dto.UserDto;
import com.geielpeguero.userapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAPIException(ExceptionMessage.exist);
        }

        return userRepository.save(new ModelMapper().map(userDto, User.class));
    }

}
