import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.entity.Student;
import ru.otus.course.service.QuizServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class QuizServiceTest {

	@Autowired
	private QuizServiceImpl quizService;

	@Test
	public void executeQuizForTest() {
		Student student = new Student("Ivan", "Ivanov");
		QuizResult result = quizService.executeQuizFor(student);
		Assertions.assertEquals(3, result.getRightAnswersCount());
	}
}
