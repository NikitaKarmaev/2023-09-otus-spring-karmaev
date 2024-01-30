package ru.otus.course.config;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocalizedMessages {

	private final AppConfig appConfig;

	private final MessageSource messageSource;

	@Getter
	private final String firstNameQuestion;

	@Getter
	private final String lastNameQuestion;

	@Getter
	private final String intro;

	@Getter
	private final String fileName;

	@Getter
	private final String promptMessage;

	@Getter
	private final String errorMessage;

	public LocalizedMessages(AppConfig appConfig, MessageSource messageSource) {
		this.appConfig = appConfig;
		this.messageSource = messageSource;

		System.out.println(messageSource.getClass().getSimpleName());
		this.firstNameQuestion = getLocalizedValue("user.firstname");
		this.lastNameQuestion = getLocalizedValue("user.lastname");
		this.intro = getLocalizedValue("quiz.intro");
		this.fileName = getLocalizedValue("quiz.fileName");
		this.promptMessage = getLocalizedValue("answer.promptMessage");
		this.errorMessage = getLocalizedValue("answer.errorMessage");
	}

	private String getLocalizedValue(String code) {
		return messageSource.getMessage(code, null, appConfig.getLocale());
	}
}
