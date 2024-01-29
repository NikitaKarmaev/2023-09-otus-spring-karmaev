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
import ru.otus.course.service.QuizServiceImpl;
import ru.otus.course.service.api.IOService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

	@Mock
	private IOService ioService;

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
		Mockito.when(ioService.readIntForRangeWithPrompt(1, 2,
				"Your answer:",
				"Specified answer does not exist! Try again")
		).thenReturn(1);

		Student student = new Student("Ivan", "Ivanov");
		QuizResult result = quizService.executeQuizFor(student);
		Assertions.assertEquals(1, result.getRightAnswersCount());
	}
}
