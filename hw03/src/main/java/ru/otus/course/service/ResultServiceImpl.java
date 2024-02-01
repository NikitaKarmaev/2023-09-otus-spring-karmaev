package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.config.AppConfig;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.service.api.LocalizedIOService;
import ru.otus.course.service.api.ResultService;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

	private final AppConfig appConfig;

	private final LocalizedIOService ioService;

	@Override
	public void showResult(QuizResult quizResult) {
		ioService.printLine("");
		ioService.printLineLocalized("result.description");

		String fullName = quizResult.getStudent().getFullName();
		ioService.printFormattedLineLocalized("result.student", fullName);

		int awrCount = quizResult.getAnsweredQuestions().size();
		ioService.printFormattedLineLocalized("result.answerCount", awrCount);

		int rightAwrCount = quizResult.getRightAnswersCount();
		ioService.printFormattedLineLocalized("result.rightAnswerCount", rightAwrCount);

		if (quizResult.getRightAnswersCount() >= appConfig.getRightAnswersCountToPass()) {
			ioService.printLineLocalized("result.success");
			return;
		}
		ioService.printLineLocalized("result.poor");
	}
}
