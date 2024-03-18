package ru.otus.hw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.model.Author;
import ru.otus.hw.model.Book;
import ru.otus.hw.model.Genre;
import ru.otus.hw.model.dto.OldBookDto;
import ru.otus.hw.service.AuthorService;
import ru.otus.hw.service.BookService;
import ru.otus.hw.service.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    private List<Book> dbBooks;

    private List<Author> dbAuthors;

    private List<Genre> dbGenres;

    private String[] authorsName;

    private String[] genresTitle;

    private String[] booksTitle;

    @BeforeEach
    public void init() {
        authorsName = getAuthors();
        genresTitle = getGenres();
        booksTitle = getBooks();
        dbAuthors = getDbAuthors();
        dbGenres = getDbGenres();
        dbBooks = getDbBooks(dbAuthors, dbGenres);
    }

    @Test
    @DisplayName("Поиск книги по id")
    public void findByid() throws Exception {
        var book = new Book(1, "Book_1",
                new Author(1, "Author_1"),
                new Genre(1, "Genre_1"));
        when(bookService.findById(1L)).thenReturn(Optional.of(book));

        this.mvc.perform(get("/book/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book_1")))
                .andExpect(content().string(containsString("Author_1")))
                .andExpect(content().string(containsString("Genre_1")));
    }

    @Test
    @DisplayName("Поиск всех книг")
    public void findBooks() throws Exception {
        List<Book> books = dbBooks;
        when(bookService.findAll()).thenReturn(books);
        this.mvc.perform(get("/books")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book_1")))
                .andExpect(content().string(containsString("Book_2")))
                .andExpect(content().string(containsString("Book_3")));
    }

    @Test
    @DisplayName("Получение формы для обновления книги")
    public void getUpdateBookForm() throws Exception {
        var book = new Book(1, "Book_1",
                new Author(1, "Author_1"),
                new Genre(1, "Genre_1"));

        when(bookService.findById(1)).thenReturn(Optional.of(book));
        when(authorService.findAll()).thenReturn(dbAuthors);
        when(genreService.findAll()).thenReturn(dbGenres);

        this.mvc.perform(get("/book/edit/1")).andDo(print())
                .andExpect(content().string(containsString(
                        "name=\"bookId\" readonly=\"readonly\" value=\"1\"/>")))
                .andExpect(content().string(containsString(
                        "name=\"title\" value=\"Book_1\"")))
                .andExpect(content().string(containsString(
                        "<option value=\"1\" selected=\"selected\">Author_1</option>")))
                .andExpect(content().string(containsString(
                        "<option value=\"1\" selected=\"selected\">Genre_1</option>")));
    }

    @Test
    @DisplayName("Обновление книги")
    public void updateBook() throws Exception {
        var bookUpdateDto = new OldBookDto(1, "Book_1", 1L, 1L);

        when(authorService.findAll()).thenReturn(dbAuthors);
        when(genreService.findAll()).thenReturn(dbGenres);

        this.mvc.perform(post("/book/edit")
                        .param("bookId", String.valueOf(bookUpdateDto.getBookId()))
                        .param("title", bookUpdateDto.getTitle())
                        .param("authorId", String.valueOf(bookUpdateDto.getAuthorId()))
                        .param("genreId", String.valueOf(bookUpdateDto.getGenreId())))
                .andExpect(status().is(302));
    }

    @Test
    @DisplayName("Удаление книги")
    public void deleteBook() throws Exception {
        this.mvc.perform(post("/book/delete/1"))
                .andExpect(status().is(302));
    }

    @Test
    @DisplayName("Получение формы для сохранения кнмгм")
    public void getSaveBookForm() throws Exception {
        when(authorService.findAll()).thenReturn(dbAuthors);
        when(genreService.findAll()).thenReturn(dbGenres);
        this.mvc.perform(get("/book/new")).andDo(print())
                .andExpect(content().string(containsString(
                        "<input id=\"input-title\" type=\"text\" name=\"title\" value=\"\"/>")))
                .andExpect(content().string(containsString(
                        "<option value=\"\">Choose an author</option>")))
                .andExpect(content().string(containsString(
                        "<option value=\"\">Choose a genre</option>")));
    }

    @Test
    @DisplayName("Сохранение книги")
    public void saveBook() throws Exception {
        this.mvc.perform(post("/book/new")
                        .param("title", "Test book")
                        .param("authorId", "1")
                        .param("genreId", "1"))
                .andExpect(status().is(302));
    }


    private List<Author> getDbAuthors() {
        return IntStream.range(1,4).boxed()
                .map(id -> new Author(id, authorsName[id-1]))
                .toList();
    }

    private List<Genre> getDbGenres() {
        return IntStream.range(1,4).boxed()
                .map(id -> new Genre(id, genresTitle[id-1]))
                .toList();
    }

    private List<Book> getDbBooks(List<Author> authors, List<Genre> genres) {
        return IntStream.range(1,4).boxed()
                .map(id -> new Book(id, booksTitle[id-1], authors.get(id-1), genres.get(id-1)))
                .toList();
    }

    private String[] getAuthors() {
        return new String[]{"Author_1", "Author_2", "Author_3"};
    }

    private String[] getGenres() {
        return new String[]{"Genre_1", "Genre_2", "Genre_3"};
    }

    private String[] getBooks() {
        return new String[]{"Book_1", "Book_2", "Book_3"};
    }
}
