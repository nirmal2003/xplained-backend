package com.xplained.main.user;


import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.dto.user.UserRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDTO getDetails() {
        return userService.userDetails();
    }

    @GetMapping("/status")
    public Boolean checkUserStatus() {
        return userService.checkUserStatus();
    }

    @PutMapping
    public void updateUser(@RequestBody UserRequestBody requestBody) {
        userService.updateUser(requestBody);
    }

    @PutMapping("/type")
    public void setUserType(@RequestBody UserRequestBody requestBody) {
        userService.setUserType(requestBody);
    }
}
