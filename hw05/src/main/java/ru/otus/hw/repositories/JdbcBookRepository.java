package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Optional<Book> findById(long id) {
        try {
            Book book = namedJdbc.queryForObject(
                    "SELECT " +
                            "b.id," +
                            "b.title," +
                            "a.id AS author_id," +
                            "a.full_name AS author_fullname," +
                            "g.id AS genre_id," +
                            "g.name AS genre_name " +
                        "FROM books b " +
                        "LEFT JOIN authors a " +
                            "ON b.author_id = a.id " +
                        "LEFT JOIN genres g " +
                            "ON b.genre_id = g.id " +
                        "WHERE b.id = :id",
                    Map.of("id", id), new BookRowMapper()
            );
            return Optional.of(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = namedJdbc.query(
                "SELECT " +
                        "b.id," +
                        "b.title," +
                        "a.id AS author_id," +
                        "a.full_name AS author_fullname," +
                        "g.id AS genre_id," +
                        "g.name AS genre_name " +
                    "FROM books b " +
                    "LEFT JOIN authors a " +
                        "ON b.author_id = a.id " +
                    "LEFT JOIN genres g " +
                        "ON b.genre_id = g.id ",
                new BookRowMapper()
        );
        return books;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        namedJdbc.update(
                "DELETE " +
                    "FROM books b " +
                    "WHERE b.id = :id",
                Map.of("id", id)
        );
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());

        namedJdbc.update(
                "INSERT INTO books (title, author_id, genre_id) " +
                        "VALUES (:title, :author_id, :genre_id)",
                params, keyHolder, new String[]{"id"}
        );

        //noinspection DataFlowIssue
        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(book.getId()));
        params.put("title", book.getTitle());
        params.put("author_id", String.valueOf(book.getAuthor().getId()));
        params.put("genre_id", String.valueOf(book.getGenre().getId()));

        int updCount = namedJdbc.update(
                "UPDATE books " +
                    "SET " +
                        "title = :title," +
                        "author_id = :author_id," +
                        "genre_id = :genre_id " +
                    "WHERE id = :id",
                params
        );

        if (updCount == 0) {
            throw new EntityNotFoundException("Не найдена запись с указанным id!");
        }
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String title = rs.getString("title");

            int authorId = rs.getInt("author_id");
            String authorFullName = rs.getString("author_fullname");
            Author author = new Author(authorId, authorFullName);

            int genreId = rs.getInt("genre_id");
            String genreName = rs.getString("genre_name");
            Genre genre = new Genre(genreId, genreName);

            return new Book(id, title, author, genre);
        }
    }
}
