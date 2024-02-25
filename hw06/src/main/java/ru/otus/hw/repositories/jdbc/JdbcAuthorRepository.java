package ru.otus.hw.repositories.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Author;
import ru.otus.hw.repositories.AuthorRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public List<Author> findAll() {
        List<Author> authors = namedJdbc.query(
                "SELECT " +
                        "a.id," +
                        "a.full_name " +
                    "FROM authors a",
                new AuthorRowMapper()
        );
        return authors;
    }

    @Override
    public Optional<Author> findById(long id) {
        try {
            Author author = namedJdbc.queryForObject(
                    "SELECT " +
                            "a.id," +
                            "a.full_name " +
                        "FROM authors a " +
                        "WHERE a.id = :id",
                    Map.of("id", id), new AuthorRowMapper()
            );
            return Optional.of(author);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            int id = rs.getInt("id");
            String fullname = rs.getString("full_name");
            return new Author(id, fullname);
        }
    }
}