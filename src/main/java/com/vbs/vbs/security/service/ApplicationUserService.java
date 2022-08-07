package com.vbs.vbs.security.service;

import com.vbs.vbs.security.user.ApplicationUser;
import com.vbs.vbs.security.user.User;
import com.vbs.vbs.security.user.UserRepo;
import com.vbs.vbs.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(SecurityUtils.convertToAuthority(user.getApplicationUserRole().name()));

        return ApplicationUser.builder()
                .user(user)
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();

    }

}