package com.xplained.main.institutions.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionUserRepository extends JpaRepository<InstitutionUser, Long> {
}
