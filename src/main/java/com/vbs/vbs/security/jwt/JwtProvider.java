package com.vbs.vbs.security.jwt;

import com.vbs.vbs.security.user.ApplicationUser;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(ApplicationUser auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);

}
