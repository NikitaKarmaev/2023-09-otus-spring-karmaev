package ru.otus.hw01.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw01.entity.Answer;
import ru.otus.hw01.entity.Question;
import ru.otus.hw01.service.api.IOService;
import ru.otus.hw01.service.api.QuestionService;
import ru.otus.hw01.service.api.QuizRunnerService;

import java.util.List;

@RequiredArgsConstructor
public class QuizRunnerServiceImpl implements QuizRunnerService {

	private final QuestionService questionService;

	private final IOService ioService;

	@Override
	public void run() {
		List<Question> result = questionService.getAll();
		ioService.printLine("Please answer the questions below\n\n");

		for (Question question : result) {
			List<String> formattedAnswers = question.getAnswers().stream().map(Answer::getValue).toList();
			String joinedAnswers = String.join("\n\t- ", formattedAnswers);
			ioService.printFormattedLine("%s:\n\t- %s\n\n", question.getValue(), joinedAnswers);
		}
	}
}
