package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.bean.Question;
import ru.otus.hw01.service.IService;
import ru.otus.hw01.service.QuestionService;

public class ApplicationHW01 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		IService<Question> service = context.getBean(QuestionService.class);
		service.printData();
	}
}