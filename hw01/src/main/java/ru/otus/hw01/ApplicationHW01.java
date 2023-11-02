package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.service.api.QuizRunnerService;

public class ApplicationHW01 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		QuizRunnerService service = context.getBean(QuizRunnerService.class);
		service.run();
	}
}