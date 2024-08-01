package com.xplained.main.institutions.users.requests;

import com.xplained.main.dto.institutions.users.requests.InstitutionUserRequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionUserRequestRepository extends JpaRepository<InstitutionUserRequest, Long> {

    @Query("SELECT new com.xplained.main.dto.institutions.users.requests.InstitutionUserRequestResponse(ur.id, ur.chars, 0, ur.createdAt) FROM InstitutionUserRequest ur WHERE ur.institutionId = :institutionId ORDER BY ur.createdAt DESC")
    List<InstitutionUserRequestResponse> findAllByInstitutionId(@Param("institutionId") Long institutionId);

    Optional<InstitutionUserRequest> findByIdAndInstitutionId(Long id, Long institutionId);
}
