package com.geielpeguero.userapi.authorization;

import com.geielpeguero.userapi.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.geielpeguero.userapi.model.User user = userRepository.findByEmail(username);
        if (user != null) {
            return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
