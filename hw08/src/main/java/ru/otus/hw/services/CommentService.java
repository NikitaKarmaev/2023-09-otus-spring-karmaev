package ru.otus.hw.services;

import ru.otus.hw.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> findByBookId(String bookId);

    Optional<Comment> findById(String commentId);

    Comment insert(String text, String bookId);

    Comment update(String id, String text);

    void delete(String id);
}
