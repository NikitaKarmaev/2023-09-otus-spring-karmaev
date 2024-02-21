package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.models.Comment;
import ru.otus.hw.repositories.jpa.JpaBookRepository;
import ru.otus.hw.repositories.jpa.JpaCommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final JpaCommentRepository commentRepository;

    private final JpaBookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByBookId(long bookId) {
        if (bookRepository.findById(bookId).isEmpty()) {
            throw new EntityNotFoundException("Sorry, but this book is not in the database");
        }

        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    @Transactional
    public Comment insert(String text, long bookId) {
        return save(0, text, bookId);
    }

    @Override
    @Transactional
    public Comment update(long id, String text) {
        return save(id, text, 0);
    }

    @Override
    @Transactional
    public void delete(long id) {
        commentRepository.delete(id);
    }

    private Comment save(long id, String text, long bookId) {
        Comment comment;
        if (bookId != 0) {
            var book = bookRepository.findById(bookId).orElseThrow(() ->
                    new EntityNotFoundException("Book not found"));
            comment = new Comment(id, text, book);
        } else {
            comment = commentRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException("Comment not found"));
            comment.setDescription(text);
        }
        return commentRepository.save(comment);
    }
}
