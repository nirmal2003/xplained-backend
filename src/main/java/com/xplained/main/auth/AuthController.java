package com.xplained.main.auth;

import com.xplained.main.dto.auth.LoginRequest;
import com.xplained.main.dto.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest, HttpServletResponse response) {
        authService.register(registerRequest, response);
    }

    @PostMapping(path = "/login")
    public void login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        authService.login(loginRequest, response);
    }
}
