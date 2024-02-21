package ru.otus.hw.repositories.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.GenreRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public List<Genre> findAll() {
        List<Genre> genres = namedJdbc.query(
                "SELECT " +
                        "g.id," +
                        "g.name " +
                    "FROM genres g",
                new GnreRowMapper()
        );
        return genres;
    }

    @Override
    public Optional<Genre> findById(long id) {
        try {
            Genre genre = namedJdbc.queryForObject(
                    "SELECT " +
                            "g.id," +
                            "g.name " +
                        "FROM genres g " +
                        "WHERE g.id = :id",
                    Map.of("id", id), new GnreRowMapper()
            );
            return Optional.of(genre);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class GnreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }
}
