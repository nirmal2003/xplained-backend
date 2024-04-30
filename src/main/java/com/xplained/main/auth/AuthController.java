package com.xplained.main.auth;

import com.xplained.main.dto.auth.LoginRequest;
import com.xplained.main.dto.auth.RegisterRequest;
import com.xplained.main.dto.user.UserRequestBody;
import com.xplained.main.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;


    @GetMapping("/email")
    public Boolean checkUserEmail(@RequestParam("email") String email ) {
        return userService.checkUserEmail(email);
    }

    @PostMapping(path = "/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest, HttpServletResponse response) {
        authService.register(registerRequest, response);
    }

    @PostMapping(path = "/login")
    public void login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        authService.login(loginRequest, response);
    }
}
