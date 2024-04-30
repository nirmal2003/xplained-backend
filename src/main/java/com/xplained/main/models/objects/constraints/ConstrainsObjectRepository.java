package com.xplained.main.models.objects.constraints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstrainsObjectRepository extends JpaRepository<ConstraintsObject, Long> {
    List<ConstraintsObject> findAllByObjectId(Long objectId);
    List<ConstraintsObject> findAllByConstraintId(Long constraintId);
    void deleteByObjectId(Long objectId);
    void deleteByObjectIdAndConstraintId(Long objectId, Long constraintId);
    void deleteByConstraintId(Long constraintId);
    Boolean existsByObjectIdAndConstraintId(Long objectId, Long constraintId);
}
