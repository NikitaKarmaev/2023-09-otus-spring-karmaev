package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.models.Comment;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByBookId(String bookId) {
        if (bookRepository.findById(bookId).isEmpty()) {
            throw new EntityNotFoundException("Sorry, but this book is not in the database");
        }

        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(String commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    @Transactional
    public Comment insert(String text, String bookId) {
        return save(null, text, bookId);
    }

    @Override
    @Transactional
    public Comment update(String id, String text) {
        return save(id, text, null);
    }

    @Override
    @Transactional
    public void delete(String id) {
        commentRepository.deleteById(id);
    }

    private Comment save(String id, String text, String bookId) {
        Comment comment;
        if (bookId != null) {
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
