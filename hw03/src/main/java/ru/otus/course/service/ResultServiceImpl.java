package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.course.config.AppConfig;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.ResultService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

	private final AppConfig appConfig;

	private final MessageSource messageSource;

	private final IOService ioService;

	@Override
	public void showResult(QuizResult quizResult) {
		Locale currLocal = appConfig.getLocale();

		ioService.printLine("");
		ioService.printLine(messageSource.getMessage("result.description", null, currLocal));

		String fullName = quizResult.getStudent().getFullName();
		String lzdStudent = messageSource.getMessage("result.student", new String[]{fullName}, currLocal);
		ioService.printFormattedLine(lzdStudent);

		int awrCount = quizResult.getAnsweredQuestions().size();
		String lzdAwrCount = messageSource.getMessage("result.answerCount", new Integer[]{awrCount}, currLocal);
		ioService.printFormattedLine(lzdAwrCount);

		int rightAwrCount = quizResult.getRightAnswersCount();
		String lzdRightAwrCount = messageSource.getMessage("result.rightAnswerCount", new Integer[]{rightAwrCount}, currLocal);
		ioService.printFormattedLine(lzdRightAwrCount);

		if (quizResult.getRightAnswersCount() >= appConfig.getRightAnswersCountToPass()) {
			ioService.printLine(messageSource.getMessage("result.success", null, currLocal));
			return;
		}
		ioService.printLine(messageSource.getMessage("result.poor", null, currLocal));
	}
}
