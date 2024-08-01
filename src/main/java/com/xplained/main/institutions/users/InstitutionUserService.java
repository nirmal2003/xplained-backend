package com.xplained.main.institutions.users;

import com.xplained.main.dto.institutions.users.InstitutionUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstitutionUserService {
    private final InstitutionUserRepository institutionUserRepository;


    public void createUser() {}
}
