package com.xplained.main.courses.points;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionPointRepository extends JpaRepository<DescriptionPoint, Long> {
}
