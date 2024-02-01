package ru.otus.course.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "app")
public class AppConfig implements QuizConfig, QuizLocaleProvider, QuizFileNameProvider {

	@Getter
	@Setter
	private int rightAnswersCountToPass;

	@Getter
	private Locale locale;

	@Setter
	private Map<String, String> fileNameByLocaleTag;

	public void setLocale(String locale) {
		this.locale = Locale.forLanguageTag(locale);
	}

	@Override
	public String getQuizFileName() {
		return fileNameByLocaleTag.get(locale.toLanguageTag());
	}
}
