package com.vbs.vbs.security.service;


import com.vbs.vbs.security.user.ApplicationUser;
import com.vbs.vbs.dto.SignInRequest;
import com.vbs.vbs.security.user.User;
import com.vbs.vbs.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User signInAndReturnJWT(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        signInRequest.getPassword())

        );
       ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();

        String jwt = jwtProvider.generateToken(applicationUser);

        User signInUser = applicationUser.getUser();
        signInUser.setToken(jwt);

        return signInUser;
    }
}
