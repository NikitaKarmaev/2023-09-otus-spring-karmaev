package ru.otus.hw.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.model.Author;
import ru.otus.hw.model.Book;
import ru.otus.hw.model.Genre;
import ru.otus.hw.model.dto.NewBookDto;
import ru.otus.hw.model.dto.OldBookDto;
import ru.otus.hw.service.AuthorService;
import ru.otus.hw.service.BookService;
import ru.otus.hw.service.GenreService;

import static org.hamcrest.Matchers.containsString;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

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

        this.mvc.perform(get("/api/books/1")).andDo(print())
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
        this.mvc.perform(get("/api/books")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book_1")))
                .andExpect(content().string(containsString("Book_2")))
                .andExpect(content().string(containsString("Book_3")));
    }

    @Test
    @DisplayName("Обновление книги")
    public void updateBook() throws Exception {
        var oldBookDto = new OldBookDto(1, "Book_1", 1L, 1L);

        when(authorService.findAll()).thenReturn(dbAuthors);
        when(genreService.findAll()).thenReturn(dbGenres);

        this.mvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(oldBookDto)))
                        .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Удаление книги")
    public void deleteBook() throws Exception {
        this.mvc.perform(delete("/api/books/1"))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Сохранение книги")
    public void saveBook() throws Exception {
        var newBookDto = new NewBookDto("Test book", 1L, 1L);
        this.mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBookDto)))
                        .andExpect(status().is(200));
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
