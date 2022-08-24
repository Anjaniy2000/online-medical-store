package com.anjaniy.onlinemedicalstore.controllers;

import com.anjaniy.onlinemedicalstore.dto.LoginRequest;
import com.anjaniy.onlinemedicalstore.dto.LoginResponse;
import com.anjaniy.onlinemedicalstore.dto.RefreshTokenRequest;
import com.anjaniy.onlinemedicalstore.dto.RegisterRequest;
import com.anjaniy.onlinemedicalstore.services.AuthService;
import com.anjaniy.onlinemedicalstore.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody RegisterRequest registerRequest){
        authService.register(registerRequest);
        return "User Registration Successfully Done.";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);

    }

    @PostMapping("/refresh/token")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return "Refresh Token Deleted Successfully!!";
    }


}
