package com.xplained.main.user;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;


    public UserDTO userDetails() {
        return authService.getCurrentUser();
    }
}
