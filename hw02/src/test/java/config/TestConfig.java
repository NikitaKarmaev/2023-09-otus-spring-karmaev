package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import ru.otus.course.service.StreamsIOService;
import ru.otus.course.service.api.IOService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
@ComponentScan("ru.otus.course")
@TestPropertySource("test-application.properties")
public class TestConfig {

	@Bean
	public IOService ioService() {
		String quizAnswers = "1\n1\n1\n1\n1";
		InputStream in = new ByteArrayInputStream(quizAnswers.getBytes(StandardCharsets.UTF_8));
		return new StreamsIOService(System.out, in);
	}
}
