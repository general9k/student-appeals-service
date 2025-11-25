package ru.ystu.studentappealsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.ystu.studentappealsservice.model.Comment;
import ru.ystu.studentappealsservice.model.Note;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>, QuerydslPredicateExecutor<Comment> {

    List<Comment> findByNoteOrderByIdAsc(Note note);
}
