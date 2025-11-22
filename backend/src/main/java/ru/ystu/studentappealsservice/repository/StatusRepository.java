package ru.ystu.studentappealsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.ystu.studentappealsservice.model.Status;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer>, QuerydslPredicateExecutor<Status> {

    List<Status> findAllByOrderByIdAsc();
}
