package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.dao.QuestionDAO;
import ru.otus.course.entity.Question;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.QuizService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final IOService ioService;

	private final QuestionDAO questionDAO;

	@Override
	public QuizResult executeQuizFor(Student student) {
		ioService.printLine("");
		ioService.printFormattedLine("Please answer the questions below:\n");
		List<Question> questions = questionDAO.getAll();
		QuizResult testResult = new QuizResult(student);

		final AtomicInteger counter = new AtomicInteger(1);
		boolean isAnswerValid;
		for (Question question: questions) {
			List<String> formattedAnswers = question.getAnswers().stream()
					.map(awr -> counter.getAndIncrement() + ")" + awr.getValue())
					.toList();
			String joinedAnswers = String.join("\n\t", formattedAnswers);
			ioService.printFormattedLine("%s:\n\t%s\n", question.getValue(), joinedAnswers);

			int specifiedAnswer = ioService.readIntForRangeWithPrompt(1, formattedAnswers.size(),
					"Your answer:", "Specified answer does not exist! Try again"
			);
			isAnswerValid = question.getAnswers().get(specifiedAnswer - 1).isCorrect();
			testResult.applyAnswer(question, isAnswerValid);
			counter.set(1);
		}
		return testResult;
	}
}