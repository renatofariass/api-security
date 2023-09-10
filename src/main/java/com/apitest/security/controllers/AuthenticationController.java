package com.apitest.security.controllers;

import com.apitest.security.domain.user.AuthenticationDto;
import com.apitest.security.domain.user.LoginResponseDto;
import com.apitest.security.domain.user.RegisterDto;
import com.apitest.security.domain.user.User;
import com.apitest.security.config.security.TokenService;
import com.apitest.security.services.UserService;
import com.apitest.security.services.exceptions.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService service;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticate = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authenticate.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDto data) {
        if (service.findByUserLogin(data.login()) != null) {
            throw new BadRequestException("bad request, you are already a user.");
        }
        service.insert(data);
        return ResponseEntity.ok().build();
    }
}
