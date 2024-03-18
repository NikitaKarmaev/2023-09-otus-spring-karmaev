package ru.otus.hw.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.model.Author;
import ru.otus.hw.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {

	private final AuthorService authorService;

	@GetMapping("/api/authors")
	public List<Author> getAll() {
		return authorService.findAll();
	}
}
