package ru.ystu.studentappealsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.ystu.studentappealsservice.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>, QuerydslPredicateExecutor<Note> {
}
