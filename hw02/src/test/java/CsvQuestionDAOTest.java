import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.course.config.QuizFileNameProvider;
import ru.otus.course.dao.CsvQuestionDAO;
import ru.otus.course.entity.Question;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CsvQuestionDAOTest {

	@InjectMocks
	private CsvQuestionDAO questionDAO;

	@Mock
	private QuizFileNameProvider quizFileNameProvider;

	@Test
	public void getAllTest() {
		Mockito.when(quizFileNameProvider.getTestFileName()).thenReturn("questions.csv");
		List<Question> result = questionDAO.getAll();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(5, result.size());
	}
}
