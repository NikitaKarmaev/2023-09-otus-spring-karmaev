package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import ru.otus.course.entity.Answer;
import ru.otus.course.entity.Question;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.QuestionService;
import ru.otus.course.service.api.QuizRunnerService;

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
