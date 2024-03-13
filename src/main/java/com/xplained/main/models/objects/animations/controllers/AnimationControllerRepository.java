package com.xplained.main.models.objects.animations.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimationControllerRepository extends JpaRepository<AnimationController, Long> {
    List<AnimationController> findAllByAnimationId(Long animationId);
    List<AnimationController> findAllByModelId(Long modelId);
    void deleteAllByModelId(Long modelId);
    void deleteAllByAnimationId(Long animationId);
}
