package ru.otus.course.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.course.config.QuizFileNameProvider;
import ru.otus.course.dao.CsvQuestionDAO;
import ru.otus.course.dao.QuestionDAO;
import ru.otus.course.entity.Answer;
import ru.otus.course.entity.Question;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.LocalizedIOService;

import java.util.List;

@SpringBootTest
public class QuizServiceTest {

	@Configuration
	public static class TestConfig {

		@Bean
		public QuizServiceImpl quizService(LocalizedIOService ioService, QuestionDAO questionDAO) {
			return new QuizServiceImpl(ioService, questionDAO);
		}
	}

	@MockBean
	private LocalizedIOService ioService;

	@MockBean
	private QuestionDAO questionDAO;

	@Autowired
	private QuizServiceImpl quizService;

	@Test
	public void executeQuizForTest() {
		Answer answer1 = new Answer("Yes", true);
		Answer answer2 = new Answer("No", false);
		Question question = new Question("Is test running?", List.of(answer1, answer2));
		List<Question> expectedQuestions = List.of(question);

		Mockito.when(questionDAO.getAll()).thenReturn(expectedQuestions);
		Mockito.when(ioService.readIntForRangeWithPromptLocalized(1, 2,
				"answer.promptMessage",
				"answer.errorMessage"
		)).thenReturn(1);

		Student student = new Student("Ivan", "Ivanov");
		QuizResult result = quizService.executeQuizFor(student);
		Assertions.assertEquals(1, result.getRightAnswersCount());
	}
}
