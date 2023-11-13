import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.course.config.AppConfig;
import ru.otus.course.dao.CsvQuestionDAO;
import ru.otus.course.entity.Question;

import java.lang.reflect.Field;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CsvQuestionDAOTest {

	private CsvQuestionDAO questionDAO;

	private AppConfig appConfig;

	@BeforeEach
	public void init() throws IllegalAccessException, NoSuchFieldException {
		appConfig = new AppConfig();
		Field fileNameField = AppConfig.class.getDeclaredField("testFileName");
		fileNameField.setAccessible(true);
		fileNameField.set(appConfig, "questions.csv");

		questionDAO = new CsvQuestionDAO(appConfig);
	}

	@Test
	public void getAllTest() {
		List<Question> result = questionDAO.getAll();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(5, result.size());
	}
}
