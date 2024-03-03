package ru.otus.hw.mongo;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;
import ru.otus.hw.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ChangeLog
public class TestInitDB {

    List<Author> authors = new ArrayList<>();
    List<Genre> genres = new ArrayList<>();
    List<Book> books = new ArrayList<>();

    @ChangeSet(order = "001", id = "DropDB", author = "karmaev_ns", runAlways = true)
    public void dropDB(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "InsertAuthors", author = "karmaev_ns", runAlways = true)
    public void insertAuthors(AuthorRepository authorRepository) {
        Author author1 = authorRepository.save(new Author(null, "Author_1"));
        Author author2 = authorRepository.save(new Author(null, "Author_2"));
        Author author3 = authorRepository.save(new Author(null, "Author_3"));
        authors.addAll(Arrays.asList(author1, author2, author3));
    }
    @ChangeSet(order = "003", id = "InsertGenres", author = "karmaev_ns", runAlways = true)
    public void insertGenres(GenreRepository genreRepository) {
        Genre genre1 = genreRepository.save(new Genre(null, "Genre_1"));
        Genre genre2 = genreRepository.save(new Genre(null, "Genre_2"));
        Genre genre3 = genreRepository.save(new Genre(null, "Genre_3"));
        genres.addAll(Arrays.asList(genre1, genre2, genre3));
    }

    @ChangeSet(order = "004", id = "InsertBooks", author = "karmaev_ns", runAlways = true)
    public void insertBooks(BookRepository bookRepository) {
        Book book1 = bookRepository.save(new Book(null, "Book_1", authors.get(0), genres.get(0)));
        Book book2 = bookRepository.save(new Book(null, "Book_2", authors.get(1), genres.get(1)));
        Book book3 = bookRepository.save(new Book(null, "Book_3", authors.get(2), genres.get(2)));
        books.addAll(Arrays.asList(book1, book2, book3));
    }

    @ChangeSet(order = "005", id = "InsertComments", author = "karmaev_ns", runAlways = true)
    public void insertComments(CommentRepository commentRepository) {
        commentRepository.save(new Comment(null, "Comment_1", books.get(0)));
        commentRepository.save(new Comment(null, "Comment_2", books.get(1)));
        commentRepository.save(new Comment(null, "Comment_3", books.get(2)));
    }
}
