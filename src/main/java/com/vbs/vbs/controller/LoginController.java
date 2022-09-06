package com.vbs.vbs.controller;

import com.vbs.vbs.dto.SignInRequest;
import com.vbs.vbs.security.user.User;
import com.vbs.vbs.security.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="login")
public class LoginController extends BaseController{

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping
    public ResponseEntity<?> login(@RequestBody SignInRequest signInRequest){
        User signInRequest1 = authenticationService.signInAndReturnJWT(signInRequest);
        if(signInRequest1 != null){
            return new ResponseEntity<>
                    (successResponse("Login Successful",signInRequest1), HttpStatus.OK);
        }
        return new ResponseEntity<>
                (errorResponse("login failed",signInRequest),HttpStatus.BAD_REQUEST);
    }
}
