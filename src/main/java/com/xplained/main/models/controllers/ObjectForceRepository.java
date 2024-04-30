package com.xplained.main.models.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectForceRepository extends JpaRepository<ObjectForce, Long> {
    List<ObjectForce> findAllByModelIdOrderByCreatedAtAsc(Long objectId);
}
