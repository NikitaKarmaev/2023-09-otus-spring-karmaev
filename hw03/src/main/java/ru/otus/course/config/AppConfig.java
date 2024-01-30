package ru.otus.course.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@PropertySource("application.properties")
public class AppConfig implements QuizConfig, QuizLocaleProvider {

	@Value("${app.rightAnswersCountToPass}")
	private int rightAnswersCountToPass;

	@Value("${app.locale}")
	private Locale locale;

	@Override
	public int getRightAnswersCountToPass() {
		return rightAnswersCountToPass;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}
}
