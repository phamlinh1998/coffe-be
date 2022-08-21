package com.example.coffeebe.domain.configs.jwt;

import com.example.coffeebe.domain.entities.CustomUserDetails;
import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService extends BaseAbtractService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return new CustomUserDetails(user);
    }
}
