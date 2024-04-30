package com.xplained.main.models.objects.groups.objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupObjectRepository extends JpaRepository<GroupObject, Long> {
    Optional<GroupObject> findByGroupIdAndObjectId(Long groupId, Long objectId);
    Optional<GroupObject> findByObjectId(Long objectId);
    List<GroupObject> findAllByGroupId(Long groupId);
    List<GroupObject> findAllByModelId(Long modelId);
}
