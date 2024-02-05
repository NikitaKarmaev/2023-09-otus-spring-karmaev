package ru.otus.course.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.course.config.QuizFileNameProvider;
import ru.otus.course.entity.Question;

import java.util.List;

@SpringBootTest
public class CsvQuestionDAOTest {

	@Configuration
	public static class TestConfig {

		@Bean
		public CsvQuestionDAO csvQuestionDAO(QuizFileNameProvider fileNameProvider) {
			return new CsvQuestionDAO(fileNameProvider);
		}
	}

	@MockBean
	private QuizFileNameProvider fileNameProvider;

	@Autowired
	private CsvQuestionDAO questionDAO;

	@Test
	public void getAllTest() {
		Mockito.when(fileNameProvider.getQuizFileName()).thenReturn("test_questions_en.csv");
		List<Question> result = questionDAO.getAll();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(5, result.size());
	}
}
