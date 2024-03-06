package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoOperations operations;

    private List<Comment> comments;
    private List<Book> books;


    @BeforeEach
    public void init() {
        comments = getComments();
        books = getBooks();
    }

    @Test
    @DisplayName("Поиск комментария по id")
    public void findCommentById() {
        var expectingComment = comments.get(0);
        var commentId = expectingComment.getId();
        var actualComment = commentRepository.findById(commentId);
        assertThat(actualComment)
                .isPresent()
                .get()
                .isEqualTo(expectingComment);
    }

    @Test
    @DisplayName("Поиск комментариев по id книги")
    public void findCommentsByBookId() {
        var book = books.get(0);
        List<Comment> expectingComments = List.of(comments.get(0));
        List<Comment> actualComments = commentRepository.findByBookId(book.getId());
        assertThat(actualComments).containsExactlyElementsOf(expectingComments);
    }

    @Test
    @DisplayName("Удаление комментария")
    public void deleteComment() {
        var deletedComment = comments.get(2);
        assertThat(deletedComment).isNotNull();

        commentRepository.deleteById(deletedComment.getId());
        var actualComment = commentRepository.findById(deletedComment.getId());
        assertThat(actualComment).isEmpty();
    }

    @Test
    @DisplayName("Обновление комментария")
    public void updateComment() {
        var book = books.get(2);
        var comment = comments.get(2);
        var expectingComment = new Comment(comment.getId(), "Updated comment", book);

        assertThat(commentRepository.findById(expectingComment.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectingComment);
        var updatedComment = commentRepository.save(expectingComment);

        assertThat(updatedComment)
                .isNotNull()
                .matches(com -> com.getId() != null )
                .usingRecursiveComparison()
                .ignoringExpectedNullFields().isEqualTo(expectingComment);

        assertThat(commentRepository.findById(updatedComment.getId()))
                .isPresent()
                .get()
                .isEqualTo(expectingComment);

    }

    @Test
    @DisplayName("Создание комментария")
    public void createComment() {
        var book = books.get(2);
        var expectingComment = new Comment(null, "Test comment", book);
        var savedComment = commentRepository.save(expectingComment);

        assertThat(savedComment)
                .isNotNull()
                .matches(comment -> comment.getId() != null)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields().isEqualTo(expectingComment);

        assertThat(commentRepository.findById(savedComment.getId()))
                .isPresent()
                .get()
                .isEqualTo(savedComment);
    }

    public List<Comment> getComments() {
        return operations.findAll(Comment.class);
    }

    public List<Book> getBooks() {
        return operations.findAll(Book.class);
    }
}
