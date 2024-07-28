package com.xplained.main.auth;

import com.xplained.main.config.auth.JwtService;
import com.xplained.main.dto.auth.LoginRequest;
import com.xplained.main.dto.auth.RegisterRequest;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.user.User;
import com.xplained.main.user.UserRepository;
import com.xplained.main.user.bio.UserBio;
import com.xplained.main.user.bio.UserBioRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserBioRepository userBioRepository;

    public void register(RegisterRequest registerRequest, HttpServletResponse response) {
        Optional<User> user = userRepository.findByEmail(registerRequest.getEmail());

        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exits");
        }

        User user1 = User.builder()
                .name(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        User savedUser = userRepository.saveAndFlush(user1);

        userBioRepository.save(UserBio.builder()
                        .userId(savedUser.getId())
                .build());

        String token = jwtService.generateToken(savedUser);

        Cookie cookie = new Cookie("sharp_token", token);
        cookie.setMaxAge(3600 * 24 * 50);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    public void login(LoginRequest loginRequest, HttpServletResponse response) {

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String token = jwtService.generateToken(user.get());

        Cookie cookie = new Cookie("sharp_token", token);
        cookie.setMaxAge(3600 * 24 * 50);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return UserDTO.builder()
                .id(user.getId())
                .type(user.getType())
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }
}
