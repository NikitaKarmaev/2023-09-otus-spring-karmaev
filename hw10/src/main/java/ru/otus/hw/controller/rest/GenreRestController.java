package ru.otus.hw.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.model.Genre;
import ru.otus.hw.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreRestController {

	private final GenreService genreService;

	@GetMapping("/api/genres")
	public List<Genre> getAll() {
		return genreService.findAll();
	}
}
