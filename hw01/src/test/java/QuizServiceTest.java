import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.hw01.bean.Answer;
import ru.otus.hw01.bean.Question;
import ru.otus.hw01.dao.impl.QuestionCsvDAO;
import ru.otus.hw01.service.QuizService;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {

	@InjectMocks
	private QuizService service = new QuizService();

	@Mock
	private QuestionCsvDAO dao;

	@Test
	public void runTest() {
		Answer answer1 = new Answer("Yes", true);
		Answer answer2 = new Answer("No", false);
		Question question = new Question("Is test running?", List.of(answer1, answer2));
		Mockito.when(dao.getQuestions()).thenReturn(List.of(question));

		service.printQuestions();
	}
}
