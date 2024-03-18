package ru.otus.hw.service;

import ru.otus.hw.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> findByBookId(long bookId);

    Optional<Comment> findById(long commentId);

    Comment insert(String text, long bookId);

    Comment update(long id, String text);

    void delete(long id);
}
