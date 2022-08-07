package com.vbs.vbs.security.service;

import com.vbs.vbs.dto.SignInRequest;
import com.vbs.vbs.security.user.User;

public interface AuthenticationService {
   User signInAndReturnJWT(SignInRequest signInRequest);
}
