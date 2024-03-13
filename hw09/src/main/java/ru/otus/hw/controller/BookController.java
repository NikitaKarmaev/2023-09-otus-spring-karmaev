package ru.otus.hw.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.otus.hw.model.Book;
import ru.otus.hw.model.dto.NewBookDto;
import ru.otus.hw.model.dto.OldBookDto;
import ru.otus.hw.service.AuthorService;
import ru.otus.hw.service.BookService;
import ru.otus.hw.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping({"/", "/books"})
    public String getAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/book/{id}")
    public String get(@PathVariable long id, Model model) {
        var book = bookService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/book")
    public String get(@RequestParam("bookId") long id) {
        return "redirect:/book/" + id;
    }

    @GetMapping("/book/new")
    public String add(Model model) {
        var book = new NewBookDto("",null, null);
        model.addAttribute("book", book);
        model.addAttribute("refer", "/book/new");
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "editBook";
    }

    @PostMapping("/book/new")
    public String add(@Valid @ModelAttribute("book") NewBookDto newBookDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("refer", "/book/new");
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "editBook";
        }
        var title = newBookDto.getTitle();
        var authorId = newBookDto.getAuthorId();
        var genreId = newBookDto.getGenreId();
        bookService.insert(title, authorId, genreId);
        return "redirect:/books";
    }

    @GetMapping("/book/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        var book = bookService.findById(id)
                .map(b -> new OldBookDto(
                        b.getBookId(),
                        b.getTitle(),
                        b.getAuthor().getAuthorId(),
                        b.getGenre().getGenreId()))
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        model.addAttribute("book", book);
        model.addAttribute("refer", "/book/edit");
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "editBook";
    }

    @PostMapping("/book/edit")
    public String edit(@Valid @ModelAttribute("book") OldBookDto oldBookDto,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("refer", "/book/edit");
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "editBook";
        }
        var bookId = oldBookDto.getBookId();
        var title = oldBookDto.getTitle();
        var authorId = oldBookDto.getAuthorId();
        var genreId = oldBookDto.getGenreId();
        bookService.update(bookId, title, authorId, genreId);
        return "redirect:/books";
    }

    @PostMapping("/book/delete/{id}")
    public String delete(@PathVariable long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
