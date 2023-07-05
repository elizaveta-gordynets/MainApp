package com.example.mainapp.service;

import com.example.mainapp.dto.AuthRequest;
import com.example.mainapp.dto.CustomUserDetails;
import com.example.mainapp.dto.UserDto;
import com.example.mainapp.entity.UserInfo;
import com.example.mainapp.repository.UserInfoRepository;
import com.example.mainapp.service.security.CustomUserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;
    private final CustomUserDetailsService service;

    public UserService (PasswordEncoder encoder,
                        UserInfoRepository userRepo,
                        CustomUserDetailsService service) {
        this.passwordEncoder = encoder;
        this.userInfoRepository = userRepo;
        this.service = service;
    }

    public void addUser(UserDto user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(passwordEncoder.encode(user.getPassword()));
        userInfo.setId(UUID.randomUUID().toString());
        userInfo.setUsername(user.getUsername());
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfoRepository.save(userInfo);
    }

    public CustomUserDetails auth(AuthRequest user) {
        Optional<UserInfo> retrievedUser = userInfoRepository.findUserInfoByUsername(user.getUsername());
        if(retrievedUser.isPresent()) {
            UserInfo userInfo = retrievedUser.get();
            if(passwordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
              return service.loadUserByUsername(user.getUsername());
            } else {
                throw new BadCredentialsException("Bad credentials");
            }
        }
        else {
            throw new BadCredentialsException("Bad Creds");
        }
     }
}
