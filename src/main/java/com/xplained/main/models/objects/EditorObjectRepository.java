package com.xplained.main.models.objects;

import com.xplained.main.dto.models.objects.EditorObjectResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditorObjectRepository extends JpaRepository<EditorObject, Long> {

    @Query("SELECT new com.xplained.main.dto.models.objects.EditorObjectResponse(o.id, o.name, o.shape, o.fill, o.width, o.height, o.yAxis, o.xAxis, o.angle, o.radius, o.isFrozen, o.velocityX, o.velocityY, o.friction, o.frictionAir, o.restitution, o.density, o.mass, o.inertia, o.createdAt) FROM EditorObject o WHERE o.modelId = :modelId ORDER BY o.createdAt DESC")
    List<EditorObjectResponse> findAllByModelIdOrderByCreatedAt(@Param("modelId") Long modelId);
    Optional<EditorObject> findByIdAndUserId(Long id, Long userId);
}
