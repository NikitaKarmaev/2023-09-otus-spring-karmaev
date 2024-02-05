package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.course.config.QuizLocaleProvider;
import ru.otus.course.service.api.LocalizedMessagesService;

@RequiredArgsConstructor
@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

	private final MessageSource messageSource;

	private final QuizLocaleProvider localeProvider;

	@Override
	public String getMessage(String code, Object... args) {
		return messageSource.getMessage(code, args, localeProvider.getLocale());
	}
}
