package ru.otus.hw.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw.exception.EntityNotFoundException;
import ru.otus.hw.model.Book;
import ru.otus.hw.model.dto.NewBookDto;
import ru.otus.hw.model.dto.OldBookDto;
import ru.otus.hw.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {

	private final BookService bookService;

	@GetMapping("/api/books")
	public List<Book> getAll() {
		return bookService.findAll();
	}

	@GetMapping("/api/books/{id}")
	public Book get(@PathVariable long id) {
		var book = bookService.findById(id);
		return book.orElseThrow(() -> new EntityNotFoundException("Book not found"));
	}

	@PostMapping("/api/books")
	public void add(@Valid @RequestBody NewBookDto newBookDto) {
		var bookTitle = newBookDto.getTitle();
		var authorId = newBookDto.getAuthorId();
		var genreId = newBookDto.getGenreId();
		bookService.insert(bookTitle, authorId, genreId);
	}

	@PutMapping("/api/books/{id}")
	public void edit(@Valid @RequestBody OldBookDto oldBookDto) {
		var bookId = oldBookDto.getBookId();
		var bookTitle = oldBookDto.getTitle();
		var authorId = oldBookDto.getAuthorId();
		var genreId = oldBookDto.getGenreId();
		bookService.update(bookId, bookTitle, authorId, genreId);
	}

	@DeleteMapping("/api/books/{id}")
	public void delete(@PathVariable long id) {
		bookService.deleteById(id);
	}
}
