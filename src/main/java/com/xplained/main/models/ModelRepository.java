package com.xplained.main.models;

import com.xplained.main.dto.models.ModelResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByIdAndUserId(Long id, Long userId);

    @Query("SELECT new com.xplained.main.dto.models.ModelResponse(m.id, m.title, m.createdAt) FROM Model m WHERE m.userId = :userId ORDER BY m.createdAt DESC")
    List<ModelResponse> findAllByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);
}
