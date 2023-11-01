package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.entity.Question;
import ru.otus.hw01.service.api.EntityService;
import ru.otus.hw01.service.QuestionService;
import ru.otus.hw01.service.api.RunnerService;

public class ApplicationHW01 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		RunnerService service = context.getBean(RunnerService.class);
		service.run();
	}
}