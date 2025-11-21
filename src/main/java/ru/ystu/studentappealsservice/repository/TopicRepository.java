package ru.ystu.studentappealsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.ystu.studentappealsservice.model.Topic;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer>, QuerydslPredicateExecutor<Topic> {

    List<Topic> findAllByDeletedAtIsNullOrderByIdAsc();

    Boolean existsByName(String name);
}
