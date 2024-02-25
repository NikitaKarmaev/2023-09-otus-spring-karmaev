package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class JpaCommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    private List<Comment> firstBookComments;


    @BeforeEach
    public void init() {
        firstBookComments = commentsByBookId();
    }

    @Test
    @DisplayName("Поиск комментария по id")
    public void findCommentById() {
        var expectingComment = em.find(Comment.class, 1);
        var actualComment = commentRepository.findById(1L);
        assertThat(actualComment)
                .isPresent()
                .get()
                .isEqualTo(expectingComment);
    }

    @Test
    @DisplayName("Поиск комментариев по id книги")
    public void findCommentsByBookId() {
        List<Comment> expectingComments = firstBookComments;
        List<Comment> actualComments = commentRepository.findByBookId(1);
        assertThat(actualComments).containsExactlyElementsOf(expectingComments);
    }

    @Test
    @DisplayName("Удаление комментария")
    public void deleteComment() {
        var deletedComment = em.find(Comment.class, 1);
        assertThat(deletedComment).isNotNull();

        em.remove(deletedComment);
        var actualComment = em.find(Comment.class, 1);
        assertThat(actualComment).isNull();
    }

    @Test
    @DisplayName("Обновление комментария")
    public void updateComment() {
        var book = em.find(Book.class, 1);
        var expectingComment = new Comment(3, "Updated comment", book);

        assertThat(em.find(Comment.class, expectingComment.getId()))
                .isNotNull()
                .isNotEqualTo(expectingComment);
        var updatedComment = em.merge(expectingComment);

        assertThat(updatedComment).isNotNull()
                .matches(comment -> comment.getId() > 0 )
                .usingRecursiveComparison()
                .ignoringExpectedNullFields().isEqualTo(expectingComment);

        assertThat(em.find(Comment.class, updatedComment.getId()))
                .isNotNull()
                .isEqualTo(expectingComment);

    }

    @Test
    @DisplayName("Создание комментария")
    public void createComment() {
        var book = em.find(Book.class, 1);
        var expectingComment = new Comment(0, "Test comment", book);
        var savedComment = em.persist(expectingComment);

        assertThat(savedComment)
                .isNotNull()
                .matches(comment -> comment.getId() > 0)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields().isEqualTo(expectingComment);

        assertThat(em.find(Comment.class, savedComment.getId()))
                .isNotNull()
                .isEqualTo(savedComment);


    }

    public List<Comment> commentsByBookId() {
        List<Comment> comments = new ArrayList<>();
        var comment = em.find(Comment.class, 1);
        comments.add(comment);
        return comments;
    }
}
