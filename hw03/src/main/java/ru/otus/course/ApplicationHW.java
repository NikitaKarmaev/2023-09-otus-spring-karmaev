package ru.otus.course;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.course.config.AppConfig;
import ru.otus.course.service.api.QuizRunnerService;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationHW implements CommandLineRunner {

	private QuizRunnerService service;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationHW.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.run();
	}
}