package ru.otus.course.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements QuizConfig, QuizFileNameProvider {

	@Value("${quiz.rightAnswersCountToPass}")
	private int rightAnswersCountToPass;

	@Value("${quiz.fileName}")
	private String testFileName;

	@Override
	public int getRightAnswersCountToPass() {
		return rightAnswersCountToPass;
	}

	@Override
	public String getTestFileName() {
		return testFileName;
	}
}
