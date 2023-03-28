
package com.example.mainapp.service.security;

import com.example.mainapp.dto.CustomUserDetails;
import com.example.mainapp.entity.UserInfo;
import com.example.mainapp.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    public CustomUserDetailsService (UserInfoRepository repository) {
        this.userInfoRepository = repository;
    }


    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Error during authentication!");

        UserInfo user = userInfoRepository.findUserInfoByUsername(username)
                .orElseThrow(s);
        return new CustomUserDetails(user);
    }

}

