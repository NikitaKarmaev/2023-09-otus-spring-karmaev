package ru.otus.course;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.course.service.api.QuizRunnerService;

public class ApplicationHW {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.otus.course");
		QuizRunnerService service = context.getBean(QuizRunnerService.class);
		service.run();
	}
}