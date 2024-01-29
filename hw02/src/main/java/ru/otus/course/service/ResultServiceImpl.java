package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.config.AppConfig;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.ResultService;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

	private final AppConfig appConfig;

	private final IOService ioService;

	@Override
	public void showResult(QuizResult quizResult) {
		ioService.printLine("");
		ioService.printLine("Test results: ");
		ioService.printFormattedLine("Student: %s", quizResult.getStudent().getFullName());
		ioService.printFormattedLine("Answered questions count: %d", quizResult.getAnsweredQuestions().size());
		ioService.printFormattedLine("Right answers count: %d", quizResult.getRightAnswersCount());

		if (quizResult.getRightAnswersCount() >= appConfig.getRightAnswersCountToPass()) {
			ioService.printLine("Congratulations! You passed test!");
			return;
		}
		ioService.printLine("Sorry. You fail test.");
	}
}
