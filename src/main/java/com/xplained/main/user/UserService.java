package com.xplained.main.user;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.dto.user.UserRequestBody;
import com.xplained.main.user.bio.UserBio;
import com.xplained.main.user.bio.UserBioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final UserBioRepository userBioRepository;


    public Boolean checkUserStatus() {
        return true;
    }

    public UserDTO userDetails() {
        UserDTO userDTO = authService.getCurrentUser();


        UserBio userBio = null;

        Optional<UserBio> userBio1 = userBioRepository.findByUserId(userDTO.getId());

        userBio = userBio1.orElseGet(() -> userBioRepository.saveAndFlush(UserBio.builder().userId(userDTO.getId()).build()));

        userDTO.setImage(userBio.getImage());
        userDTO.setDescription(userBio.getDescription());
        userDTO.setHeading(userBio.getHeading());
        userDTO.setLinkedin(userBio.getLinkedin());
        userDTO.setYoutube(userBio.getYoutube());
        userDTO.setWeb(userBio.getWeb());
        userDTO.setOtherUrl(userBio.getOtherUrl());

        return userDTO;
    }

    public Boolean checkUserEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void updateUser(UserRequestBody requestBody) {
        UserDTO user = authService.getCurrentUser();

        UserBio userBio = null;

        Optional<UserBio> _userBio = userBioRepository.findByUserId(user.getId());

        userBio = _userBio.orElseGet(() -> userBioRepository.saveAndFlush(UserBio.builder().userId(user.getId()).build()));

        if (requestBody.getDescription() != null) userBio.setDescription(requestBody.getDescription());
        if (requestBody.getHeading() != null) userBio.setHeading(requestBody.getHeading());
        if (requestBody.getImage() != null) userBio.setImage(requestBody.getImage());
        if (requestBody.getLinkedin() != null) userBio.setLinkedin(requestBody.getLinkedin());
        if (requestBody.getYoutube() != null) userBio.setYoutube(requestBody.getYoutube());
        if (requestBody.getWeb() != null) userBio.setWeb(requestBody.getWeb());
        if (requestBody.getOtherUrl() != null) userBio.setOtherUrl(requestBody.getOtherUrl());

        userBioRepository.save(userBio);
    }
}
