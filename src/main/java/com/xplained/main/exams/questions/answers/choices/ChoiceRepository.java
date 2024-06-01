package com.xplained.main.exams.questions.answers.choices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findAllByQuestionIdOrderByIndexAsc(Long questionId);
    Long countByQuestionId(Long questionId);
}
