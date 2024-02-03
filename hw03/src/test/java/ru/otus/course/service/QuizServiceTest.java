package ru.otus.course.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.course.dao.QuestionDAO;
import ru.otus.course.entity.Answer;
import ru.otus.course.entity.Question;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.LocalizedIOService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

	@Mock
	private LocalizedIOService ioService;

	@Mock
	private QuestionDAO questionDAO;

	@InjectMocks
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
