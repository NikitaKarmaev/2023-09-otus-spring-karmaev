package ru.otus.hw.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.model.Author;
import ru.otus.hw.service.AuthorService;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorRestController.class)
public class AuthorRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AuthorService authorService;

	private List<Author> dbAuthors;

	private String[] authorsName;

	@BeforeEach
	public void init() {
		authorsName = getAuthorsName();
		dbAuthors = getDbAuthors();
	}


	@Test
	@DisplayName("Поиск всех авторов")
	public void getAuthors() throws Exception {
		List<Author> authors = dbAuthors;

		when(authorService.findAll()).thenReturn(dbAuthors);

		this.mvc.perform(get("/api/authors")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Author_1")))
				.andExpect(content().string(containsString("Author_2")))
				.andExpect(content().string(containsString("Author_3")));
	}


	private List<Author> getDbAuthors() {
		return IntStream.range(1,4).boxed()
				.map(id -> new Author(id, authorsName[id-1]))
				.toList();
	}

	private String[] getAuthorsName() {
		return new String[]{"Author_1", "Author_2", "Author_3"};
	}
}
