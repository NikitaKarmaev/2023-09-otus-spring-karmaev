import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw01.dao.QuestionDAOImpl;
import ru.otus.hw01.entity.Answer;
import ru.otus.hw01.entity.Question;
import ru.otus.hw01.service.QuestionServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

	@InjectMocks
	private QuestionServiceImpl service;

	@Mock
	private QuestionDAOImpl questionDAO;

	@Test
	public void runTest() {
		Answer answer1 = new Answer("Yes", true);
		Answer answer2 = new Answer("No", false);
		Question question = new Question("Is test running?", List.of(answer1, answer2));
		List<Question> expectedQuestions = List.of(question);
		Mockito.when(questionDAO.getAll()).thenReturn(expectedQuestions);

		List<Question> realQuestions = service.getAll();

		Assertions.assertEquals(expectedQuestions, realQuestions);
	}
}
