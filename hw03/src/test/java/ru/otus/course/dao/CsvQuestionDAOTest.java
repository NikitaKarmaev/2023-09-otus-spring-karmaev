package ru.otus.course.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.course.config.QuizFileNameProvider;
import ru.otus.course.entity.Question;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CsvQuestionDAOTest {

	@Mock
	private QuizFileNameProvider fileNameProvider;

	@InjectMocks
	private CsvQuestionDAO questionDAO;

	@Test
	public void getAllTest() {
		Mockito.when(fileNameProvider.getQuizFileName()).thenReturn("questions_en.csv");
		List<Question> result = questionDAO.getAll();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(5, result.size());
	}
}
